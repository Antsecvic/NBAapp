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

import project.agile.Adapter.TeamAdapter;
import project.agile.Object.Team;
import project.agile.util.SQLdm;

public class TeamActivity extends AppCompatActivity {

    private List<Team> teamList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team);

        //初始化teamList
        SQLdm s = new SQLdm();
        final SQLiteDatabase db = s.openDatabase(getApplicationContext());
        Cursor cursor = db.rawQuery("select distinct * from Team where TeamName != \"\" order by TeamName", new String[] { });
        if(cursor.moveToFirst()){
            do {
                Team team = new Team();
                team.setLeague(cursor.getString(cursor.getColumnIndex("Lg")));
                team.setAbbr(cursor.getString(cursor.getColumnIndex("TeamAbbr")));
                team.setChampions(cursor.getInt(cursor.getColumnIndex("TeamChamp")));
                team.setName(cursor.getString(cursor.getColumnIndex("TeamName")));
                team.setFrom(cursor.getInt(cursor.getColumnIndex("TeamFrom")));
                team.setTo(cursor.getInt(cursor.getColumnIndex("TeamTo")));
                team.setGames(cursor.getInt(cursor.getColumnIndex("TeamG")));
                team.setWins(cursor.getInt(cursor.getColumnIndex("TeamW")));
                team.setLoses(cursor.getInt(cursor.getColumnIndex("TeamL"))) ;
                teamList.add(team);
            }while(cursor.moveToNext());
            cursor.close();
        }

        TeamAdapter teamAdapter = new TeamAdapter(this,R.layout.team_item,
                teamList);

        final ListView listView = (ListView) findViewById(R.id.teamList);
        listView.setAdapter(teamAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(TeamActivity.this, Detail_TeamActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("team",teamList.get(position));
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });
    }
}
