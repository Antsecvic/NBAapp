package project.agile.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import project.agile.Object.Arena;
import project.agile.nbaapp.R;

/**
 * Created by John on 2017/4/16.
 */
public class ArenaAdapter extends ArrayAdapter<Arena>{
    private int resourceId;

    public ArenaAdapter(Context context, int textViewResourceId,
                         List<Arena> objects){
        super(context,textViewResourceId,objects);
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Arena arena = getItem(position);
        View view;
        ViewHolder viewHolder;
        if(convertView == null){
            view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.arenaName = (TextView)view.findViewById(R.id.arenaName);
            viewHolder.arenaLocation = (TextView)view.findViewById(R.id.arenaLocation);
            view.setTag(viewHolder);
        }else{
            view = convertView;
            viewHolder = (ViewHolder)view.getTag();
        }
        viewHolder.arenaName.setText(arena.getArenaName());
        viewHolder.arenaLocation.setText(arena.getArenaLocation());
        return view;
    }

    class ViewHolder{
        TextView arenaName;
        TextView arenaLocation;
    }
}
