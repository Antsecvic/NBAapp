package project.agile.nbaapp;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by John on 2017/4/15.
 */
public class TitleLayout extends LinearLayout {
    public TitleLayout(Context context, AttributeSet attrs){
        super(context,attrs);
        LayoutInflater.from(context).inflate(R.layout.title,this);

        String[] activity = {"PlayerActivity","TeamActivity","CoachActivity","ArenaActivity"};
        int index = 0;

        final Intent intent1 = new Intent(getContext(),MainActivity.class);
        final Intent intent2 = new Intent(getContext(),PlayerActivity.class);
        final Intent intent3 = new Intent(getContext(),TeamActivity.class);
        final Intent intent4 = new Intent(getContext(),CoachActivity.class);
        final Intent intent5 = new Intent(getContext(),ArenaActivity.class);

        final List<Intent> intentList = new ArrayList<Intent>();
        intentList.add(intent2);
        intentList.add(intent3);
        intentList.add(intent4);
        intentList.add(intent5);
        TextView text1 = (TextView)findViewById(R.id.text1);
        TextView text2 = (TextView)findViewById(R.id.text2);
        TextView text3 = (TextView)findViewById(R.id.text3);
        TextView text4 = (TextView)findViewById(R.id.text4);
        String temp = getContext().toString();
        String activityName = temp.substring(temp.lastIndexOf(".") + 1, temp.indexOf("@"));
        for(String i : activity){
            if(i.equals(activityName)){
                break;
            }
            index++;
        }
        text1.setText("首页");
        text1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                getContext().startActivity(intent1);
            }
        });

        int[] remainIndex = new int[activity.length];
        int j = 0;
        for(int i = 0;i < activity.length;){
            if(i == index){
                ++i;
            }
            remainIndex[j] = i;
            ++i;
            j++;
        }
        final int[] finalRemainIndex = remainIndex;
        text2.setText(activity[remainIndex[0]]);
        text3.setText(activity[remainIndex[1]]);
        text4.setText(activity[remainIndex[2]]);
        text2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                getContext().startActivity(intentList.get(finalRemainIndex[0]));
            }
        });
        text3.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                getContext().startActivity(intentList.get(finalRemainIndex[1]));
            }
        });
        text4.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                getContext().startActivity(intentList.get(finalRemainIndex[2]));
            }
        });
    }
}
