package project.agile.Object;

/**
 * Created by John on 2017/5/14.
 */

public class CoachInOneSeason {
    private double season;
    private Team  mTeam;
    private Coach  mCoach;

    public double getSeason() {
        return season;
    }

    public void setSeason(double season) {
        this.season = season;
    }

    public Team getTeam() {
        return mTeam;
    }

    public void setTeam(Team team) {
        mTeam = team;
    }

    public Coach getCoach() {
        return mCoach;
    }

    public void setCoach(Coach coach) {
        mCoach = coach;
    }
}
