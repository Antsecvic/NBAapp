package project.agile.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import project.agile.Object.Team;
import project.agile.nbaapp.R;

/**
 * Created by John on 2017/4/16.
 */
public class TeamAdapter extends ArrayAdapter<Team>{
    private int resourceId;

    public TeamAdapter(Context context, int textViewResourceId,
                         List<Team> objects){
        super(context,textViewResourceId,objects);
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Team team = getItem(position);
        View view;
        ViewHolder viewHolder;
        if(convertView == null){
            view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.teamName = (TextView)view.findViewById(R.id.teamName);
            viewHolder.teamFromTo = (TextView)view.findViewById(R.id.teamFromTo);
            view.setTag(viewHolder);
        }else{
            view = convertView;
            viewHolder = (ViewHolder)view.getTag();
        }
        viewHolder.teamName.setText(team.getName());
        viewHolder.teamFromTo.setText(team.getFrom()+"-"+team.getTo());
        return view;
    }

    class ViewHolder{
        TextView teamName;
        TextView teamFromTo;
    }
}
