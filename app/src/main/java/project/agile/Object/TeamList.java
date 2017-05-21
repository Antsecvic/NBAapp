package project.agile.Object;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by John on 2017/5/14.
 */

public class TeamList {
    private List<Team> mTeamList;
    private static final TeamList ourInstance = new TeamList();

    public static TeamList getInstance() {
        return ourInstance;
    }

    private TeamList() {
        mTeamList = new ArrayList<>();
    }
    public List<Team> getmTeamList() {
        return mTeamList;
    }

    public void setmTeamList(List<Team> mTeamList) {
        this.mTeamList = mTeamList;
    }

    public void addTeam(Team team){
        mTeamList.add(team);
    }

    public Team getTeam(String league, String abbr) {
        Team team = new Team();
        team.setLeague(league);
        team.setAbbr(abbr);
        if (mTeamList.indexOf(team) < 0) {
            Log.d("TAG", mTeamList.indexOf(team) + "");
            return null;
        }
        return mTeamList.get(mTeamList.indexOf(team));
    }

}
