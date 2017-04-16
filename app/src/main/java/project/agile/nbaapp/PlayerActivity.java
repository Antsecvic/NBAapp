package project.agile.nbaapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import project.agile.Adapter.PlayerAdapter;
import project.agile.Object.Player;

public class PlayerActivity extends AppCompatActivity {


    private List<Player> playerList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);


        //初始化playerList


        PlayerAdapter playerAdapter = new PlayerAdapter(this,R.layout.player_item,
                playerList);

        ListView listView = (ListView) findViewById(R.id.playerList);
        listView.setAdapter(playerAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }
}
