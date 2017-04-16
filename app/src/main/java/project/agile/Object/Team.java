package project.agile.Object;

import java.io.Serializable;

/**
 * Created by John on 2017/4/16.
 */
public class Team implements Serializable{
    private String teamName;
    private int teamFrom;
    private int teamTo;

    public void setTeamTo(int teamTo) {
        this.teamTo = teamTo;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public void setTeamFrom(int teamFrom) {
        this.teamFrom = teamFrom;
    }

    public String getTeamName() {
        return teamName;
    }

    public int getTeamFrom() {
        return teamFrom;
    }

    public int getTeamTo() {
        return teamTo;
    }

}
