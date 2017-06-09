package project.agile.nbaapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import project.agile.Adapter.PlayerDetailAdapter;
import project.agile.Object.PlayerInOneSeason;
import project.agile.util.SQLdm;

public class Detail_PlayerActivity extends AppCompatActivity {
    private TextView nameTextView;
    private TextView birthTextView;
    private String name;
    private int birthYear;
    private List<PlayerInOneSeason> list = new ArrayList<>();
    private ListView mListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail__player);

        Bundle bundle = getIntent().getExtras();
        name = bundle.getString("Name");
        birthYear = bundle.getInt("BirthYear");

        nameTextView = (TextView)findViewById(R.id.detailPlayerName);
        birthTextView = (TextView)findViewById(R.id.detailPlayerBirth);
        nameTextView.setText(name);
        birthTextView.setText(birthYear+"");

        //初始化playerList
        SQLdm s = new SQLdm();
        final SQLiteDatabase db = s.openDatabase(getApplicationContext());
        Cursor cursor = db.rawQuery("select * from Player where Player = ? and Birth = ?", new String[] {name, birthYear+""});
        if(cursor.moveToFirst()){
            do {
                String season = cursor.getString(cursor.getColumnIndex("Season"));
                String league = cursor.getString(cursor.getColumnIndex("Lg"));
                String teamAbbr = cursor.getString(cursor.getColumnIndex("TeamAbbr"));
                int gameNum = cursor.getInt(cursor.getColumnIndex("G"));
                int points = cursor.getInt(cursor.getColumnIndex("PTS"));
                DecimalFormat df = new DecimalFormat("#.0");
                double ppg = Double.valueOf(df.format((double)points/gameNum));
                PlayerInOneSeason playerInOneSeason = new PlayerInOneSeason(season,league,
                        teamAbbr,gameNum,points,ppg);
                list.add(playerInOneSeason);

            }while(cursor.moveToNext());
            cursor.close();
        }
        mListView = (ListView)findViewById(R.id.playerDetailList);
        PlayerDetailAdapter playerDetailAdapter = new PlayerDetailAdapter(this,R.layout.player_detail_item,
                list);
        mListView.setAdapter(playerDetailAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(Detail_PlayerActivity.this, PlayersOfOneSeason.class);
                String teamAbbr = list.get(i).getTeamAbbr();
                String season = list.get(i).getSeason();

                Bundle bundle = new Bundle();
                bundle.putSerializable("TeamAbbr",teamAbbr);
                bundle.putSerializable("Season",season);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}
