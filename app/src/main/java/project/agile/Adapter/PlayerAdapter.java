package project.agile.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import project.agile.Object.Player;
import project.agile.nbaapp.R;

/**
 * Created by John on 2017/4/16.
 */
public class PlayerAdapter extends ArrayAdapter<Player> {
    private int resourceId;

    public PlayerAdapter(Context context,int textViewResourceId,
                         List<Player> objects){
        super(context,textViewResourceId,objects);
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Player player = getItem(position);
        View view;
        ViewHolder viewHolder;
        if(convertView == null){
            view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.playerName = (TextView)view.findViewById(R.id.playerName);
            viewHolder.playerAge = (TextView)view.findViewById(R.id.playerAge);
            view.setTag(viewHolder);
        }else{
            view = convertView;
            viewHolder = (ViewHolder)view.getTag();
        }
        viewHolder.playerName.setText(player.getName());
        viewHolder.playerAge.setText(player.getBirthYear()+" years old");
        return view;
    }

    class ViewHolder{
        TextView playerName;
        TextView playerAge;
    }
}
