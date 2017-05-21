package project.agile.nbaapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import project.agile.Object.Team;

public class Detail_TeamActivity extends AppCompatActivity {
    private TextView teamNameText;
    private TextView leagueText;
    private TextView fromToText;
    private TextView abbrText;
    private TextView championsText;
    private TextView gamesText;
    private TextView winsText;
    private TextView losesText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail__team);

        Bundle bundle = getIntent().getExtras();
        Team team = (Team)bundle.getSerializable("team");

        leagueText = (TextView)findViewById(R.id.detailTeamLeague);
        teamNameText = (TextView)findViewById(R.id.detailTeamName);
        abbrText = (TextView)findViewById(R.id.detailTeamAbbr);
        fromToText = (TextView)findViewById(R.id.detailTeamFromTo);
        championsText = (TextView)findViewById(R.id.detailTeamChampions);
        gamesText = (TextView)findViewById(R.id.detailTeamGames);
        winsText = (TextView)findViewById(R.id.detailTeamWins);
        losesText = (TextView)findViewById(R.id.detailTeamLoses);
        leagueText.setText(team.getLeague());
        teamNameText.setText(team.getName());
        abbrText.setText(team.getAbbr());
        fromToText.setText(team.getFrom()+"-"+team.getTo());
        championsText.setText(team.getChampions()+"");
        gamesText.setText(team.getGames()+"");
        winsText.setText(team.getWins()+"");
        losesText.setText(team.getLoses()+"");
    }
}
