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

import project.agile.Adapter.ArenaAdapter;
import project.agile.Object.Arena;
import project.agile.util.SQLdm;

/**
 * Created by Oneplus on 2017/5/25.
 */

public class ArenaFragment  extends Fragment {

    private List<Arena> arenaList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_common,container,false);
        ListView arenaListView = (ListView) view.findViewById(R.id.fragment_list);

        arenaList.clear();
        //初始化arenaList
        SQLdm s = new SQLdm();
        final SQLiteDatabase db = s.openDatabase(getContext());
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


        ArenaAdapter arenaAdapter = new ArenaAdapter(getActivity(),R.layout.arena_item,
                arenaList);

        arenaListView.setAdapter(arenaAdapter);
        arenaListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), Detail_ArenaActivity.class);
                String arenaName = arenaList.get(position).getArenaName();
                String arenaLocation = arenaList.get(position).getArenaLocation();

                Bundle bundle = new Bundle();
                bundle.putSerializable("ArenaName",arenaName);
                bundle.putSerializable("ArenaLocation",arenaLocation);

                intent.putExtras(bundle);
                startActivity(intent);

            }
        });

        return view;
    }

}
