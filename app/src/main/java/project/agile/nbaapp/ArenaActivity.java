package project.agile.nbaapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import project.agile.Adapter.ArenaAdapter;
import project.agile.Object.Arena;

import static project.agile.nbaapp.R.id.playerList;

public class ArenaActivity extends AppCompatActivity {

    private List<Arena> arenaList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arena);

        //初始化arenaList


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
