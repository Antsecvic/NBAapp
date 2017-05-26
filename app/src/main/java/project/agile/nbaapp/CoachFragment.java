package project.agile.nbaapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import project.agile.Adapter.CoachAdapter;
import project.agile.Object.Coach;
import project.agile.util.SQLdm;

/**
 * Created by Oneplus on 2017/5/25.
 */

public class CoachFragment  extends Fragment {

    private List<Coach> coachList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_common,container,false);
        ListView coachListView = (ListView) view.findViewById(R.id.fragment_list);

        coachList.clear();
        //初始化coachList
        SQLdm s = new SQLdm();
        final SQLiteDatabase db = s.openDatabase(getContext());
        Cursor cursor = db.rawQuery("select distinct TeamCoach from Coach order by TeamCoach", new String[] { });
        if(cursor.moveToFirst()){
            do {
                Coach coach = new Coach();
                coach.setCoachName(cursor.getString(cursor.getColumnIndex("TeamCoach")));
                coachList.add(coach);
            }while(cursor.moveToNext());
            cursor.close();
        }


        CoachAdapter coachAdapter = new CoachAdapter(getActivity(),R.layout.coach_item,
                coachList);

        coachListView.setAdapter(coachAdapter);
        coachListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), Detail_CoachActivity.class);
                String coachName = coachList.get(position).getCoachName();

                Bundle bundle = new Bundle();
                bundle.putSerializable("CoachName",coachName);

                intent.putExtras(bundle);
                startActivity(intent);

            }
        });

        return view;
    }

}
