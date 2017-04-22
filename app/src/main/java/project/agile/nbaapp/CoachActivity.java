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

import project.agile.Adapter.CoachAdapter;
import project.agile.Object.Coach;
import project.agile.util.SQLdm;

public class CoachActivity extends AppCompatActivity {

    private List<Coach> coachList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coach);

        //初始化coachList
        SQLdm s = new SQLdm();
        final SQLiteDatabase db = s.openDatabase(getApplicationContext());
        Cursor cursor = db.rawQuery("select TeamCoach from Coach", new String[] { });
        if(cursor.moveToFirst()){
            do {
                Coach coach = new Coach();
                coach.setCoachName(cursor.getString(cursor.getColumnIndex("TeamCoach")));
                coachList.add(coach);
            }while(cursor.moveToNext());
            cursor.close();
        }


        CoachAdapter coachAdapter = new CoachAdapter(this,R.layout.coach_item,
                coachList);

        final ListView listView = (ListView) findViewById(R.id.coachList);
        listView.setAdapter(coachAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(CoachActivity.this, Detail_CoachActivity.class);
                String coachName = coachList.get(position).getCoachName();

                Bundle bundle = new Bundle();
                bundle.putSerializable("CoachName",coachName);

                intent.putExtras(bundle);
                startActivity(intent);

            }
        });
    }
}
