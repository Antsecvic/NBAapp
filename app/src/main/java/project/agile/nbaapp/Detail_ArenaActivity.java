package project.agile.nbaapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import project.agile.Object.PlayerInOneSeason;
import project.agile.util.SQLdm;

public class Detail_ArenaActivity extends AppCompatActivity {
    public List<Map<String,String>> list=new ArrayList<>();
    String ArenaName;
    String ArenaLocation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail__arena);
        ArenaName=getIntent().getStringExtra("ArenaName");
        ArenaLocation=getIntent().getStringExtra("ArenaLocation");
        readFromSQ();
        ListView listView=(ListView) findViewById(R.id.arenaDataList);
        TextView arena=(TextView)findViewById(R.id.arena) ;
        arena.setText(ArenaName);
        SimpleAdapter simpleAdapter=new SimpleAdapter(this,list,R.layout.arena_data_item,
                new String[]{"time","lg","team"},new int[]{R.id.time,R.id.lg,R.id.team});
        listView.setAdapter(simpleAdapter);
    }
    private void readFromSQ(){
        SQLdm s = new SQLdm();
        final SQLiteDatabase db = s.openDatabase(getApplicationContext());
        Cursor cursor = db.rawQuery("select * from Arena where Arena = ? and ArenaLocation = ?", new String[] {ArenaName, ArenaLocation});
        if(cursor.moveToFirst()){
            do {
                Map<String,String> map=new HashMap<>();
                String ArenaStart=cursor.getString(cursor.getColumnIndex("ArenaStart"));
                String ArenaEnd=cursor.getString(cursor.getColumnIndex("ArenaEnd"));
                String league = cursor.getString(cursor.getColumnIndex("Lg"));
                String teamAbbr = cursor.getString(cursor.getColumnIndex("TeamAbbr"));
                map.put("time",ArenaStart+"-"+ArenaEnd);
                map.put("lg",league);
                map.put("team",teamAbbr);
                list.add(map);

            }while(cursor.moveToNext());
            cursor.close();
        }
    }

}
