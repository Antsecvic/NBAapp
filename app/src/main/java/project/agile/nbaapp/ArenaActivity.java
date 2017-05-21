package project.agile.nbaapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import project.agile.Adapter.ArenaAdapter;
import project.agile.Object.Arena;
import project.agile.util.SQLdm;

import static project.agile.nbaapp.R.id.playerList;

public class ArenaActivity extends AppCompatActivity {

    private List<Arena> arenaList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arena);

        //初始化arenaList
        SQLdm s = new SQLdm();
        final SQLiteDatabase db = s.openDatabase(getApplicationContext());
        Cursor cursor = db.rawQuery("select distinct Arena, ArenaLocation from Arena order by Arena", new String[] { });
        if(cursor.moveToFirst()){
            do {
                Arena arena = new Arena();
                arena.setArenaName(cursor.getString(cursor.getColumnIndex("Arena")));
                arena.setArenaLocation(cursor.getString(cursor.getColumnIndex("ArenaLocation")));
                arenaList.add(arena);
            }while(cursor.moveToNext());
            cursor.close();
        }


        ArenaAdapter arenaAdapter = new ArenaAdapter(this,R.layout.arena_item,
                arenaList);

        final ListView listView = (ListView) findViewById(playerList);
        listView.setAdapter(arenaAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ArenaActivity.this, Detail_ArenaActivity.class);
                String arenaName = arenaList.get(position).getArenaName();
                String arenaLocation = arenaList.get(position).getArenaLocation();

                Bundle bundle = new Bundle();
                bundle.putSerializable("ArenaName",arenaName);
                bundle.putSerializable("ArenaLocation",arenaLocation);

                intent.putExtras(bundle);
                startActivity(intent);

            }
        });
    }
}
