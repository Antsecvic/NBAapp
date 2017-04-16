package project.agile.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import project.agile.Object.Coach;
import project.agile.nbaapp.R;

/**
 * Created by John on 2017/4/16.
 */
public class CoachAdapter extends ArrayAdapter<Coach> {
    private int resourceId;

    public CoachAdapter(Context context, int textViewResourceId,
                         List<Coach> objects){
        super(context,textViewResourceId,objects);
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Coach player = getItem(position);
        View view;
        ViewHolder viewHolder;
        if(convertView == null){
            view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.coachName = (TextView)view.findViewById(R.id.coachName);
            view.setTag(viewHolder);
        }else{
            view = convertView;
            viewHolder = (ViewHolder)view.getTag();
        }
        viewHolder.coachName.setText(player.getCoachName());
        return view;
    }

    class ViewHolder{
        TextView coachName;
    }
}
