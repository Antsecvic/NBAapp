package project.agile.nbaapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import project.agile.Adapter.PlayerAdapter;
import project.agile.Object.Player;
import project.agile.util.SQLdm;

public class PlayerActivity extends AppCompatActivity {


    private List<Player> playerList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);


        //初始化playerList
        SQLdm s = new SQLdm();
        final SQLiteDatabase db = s.openDatabase(getApplicationContext());
        Cursor cursor = db.rawQuery("select Player, Birth from Player", new String[] { });
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        int curYear = Integer.parseInt(formatter.format(curDate));

        if(cursor.moveToFirst()){
            do {
                Player player = new Player();
                player.setName(cursor.getString(cursor.getColumnIndex("Player")));
                player.setAge(String.valueOf(curYear - cursor.getInt(cursor.getColumnIndex("Birth"))));
                playerList.add(player);
            }while(cursor.moveToNext());
            cursor.close();
        }


        PlayerAdapter playerAdapter = new PlayerAdapter(this,R.layout.player_item,
                playerList);

        final ListView listView = (ListView) findViewById(R.id.playerList);
        listView.setAdapter(playerAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(PlayerActivity.this, Detail_PlayerActivity.class);
                String name = playerList.get(position).getName();
                String age = playerList.get(position).getAge();

                Bundle bundle = new Bundle();
                bundle.putSerializable("Name",name);
                bundle.putSerializable("Age",age);

                intent.putExtras(bundle);
                startActivity(intent);

            }
        });
    }
}
