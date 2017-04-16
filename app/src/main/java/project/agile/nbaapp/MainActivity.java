package project.agile.nbaapp;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Map;

import project.agile.DataAnalysis.ReadCSV;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private MyDatabaseHelper dbHelper;

    ArrayList<Map<String, String> > playerList;


    private Button player;
    private Button team;
    private Button coach;
    private Button arena;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new MyDatabaseHelper(this,"NBA.db",null,1);
        //创建或打开数据库
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        //向四个表插入数据
        ReadCSV readCSV = new ReadCSV();
        readCSV.Insert(db);

        player = (Button)findViewById(R.id.player);
        team = (Button)findViewById(R.id.team);
        coach = (Button)findViewById(R.id.coach);
        arena = (Button)findViewById(R.id.arena);
        player.setOnClickListener(this);
        team.setOnClickListener(this);
        coach.setOnClickListener(this);
        arena.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.player:
                intent = new Intent(this,PlayerActivity.class);
                startActivity(intent);
                break;
            case R.id.team:
                intent = new Intent(this,TeamActivity.class);
                startActivity(intent);
                break;
            case R.id.coach:
                intent = new Intent(this,CoachActivity.class);
                startActivity(intent);
                break;
            case R.id.arena:
                intent = new Intent(this,ArenaActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
