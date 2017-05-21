package project.agile.nbaapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import project.agile.Adapter.PlayerAdapter;
import project.agile.Object.Player;
import project.agile.Object.PlayerInOneSeason;
import project.agile.Object.TeamList;
import project.agile.util.PlayerComparator;
import project.agile.util.SQLdm;

public class PlayerActivity extends AppCompatActivity {


    private List<Player> playerList = new ArrayList<>();

    private TeamList teamList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        teamList = TeamList.getInstance();

        //初始化playerList
        SQLdm s = new SQLdm();
        final SQLiteDatabase db = s.openDatabase(getApplicationContext());
        Cursor cursor = db.rawQuery("select * from Player order by Player", new String[] { });
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        int curYear = Integer.parseInt(formatter.format(curDate));

        Log.d("sibingshishabi", "shabi ");
        if(cursor.moveToFirst()){
            do {

                String name = cursor.getString(cursor.getColumnIndex("Player"));
                double birth = cursor.getDouble(cursor.getColumnIndex("Birth"));

                Player player = new Player(name, birth);

                PlayerInOneSeason playerInOneSeason = new PlayerInOneSeason();
                playerInOneSeason.setGames(cursor.getColumnIndex("Game"));
                playerInOneSeason.setPoints(cursor.getColumnIndex("Points"));
                playerInOneSeason.setSeason(cursor.getColumnIndex("Season"));
                String abbr = cursor.getString(cursor.getColumnIndex("TeamAbbr"));
                String league = cursor.getString(cursor.getColumnIndex("Lg"));
                playerInOneSeason.setTeam(teamList.getTeam(abbr, league));

                if (playerList.contains(player))
                    player = playerList.get(playerList.indexOf(player));

                playerInOneSeason.setPlayer(player);
                player.addOneSeason(playerInOneSeason);

            if (playerList.indexOf(player) < 0) {
                playerList.add(player);
            } else {
                playerList.set(playerList.indexOf(player), player);
            }
                Log.d("hhhh", player.getName() + " " + player.getBirthYear());

            }while(cursor.moveToNext());

            cursor.close();
        }

//        playerList.sort(new PlayerComparator());

        PlayerAdapter playerAdapter = new PlayerAdapter(this,R.layout.player_item,
                playerList);

        final ListView listView = (ListView) findViewById(R.id.playerList);
        listView.setAdapter(playerAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(PlayerActivity.this, Detail_PlayerActivity.class);
                String name = playerList.get(position).getName();
//                String age = playerList.get(position).getAge();

                Bundle bundle = new Bundle();
                bundle.putSerializable("Name",name);
//                bundle.putSerializable("Age",age);

                intent.putExtras(bundle);
                startActivity(intent);

            }
        });
    }
}
