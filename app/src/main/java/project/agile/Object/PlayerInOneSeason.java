package project.agile.Object;

/**
 * Created by John on 2017/5/14.
 */

public class PlayerInOneSeason {
    private Player mPlayer;
    private double season;
    private Team mTeam;
    private double games;
    private double points;

    public Player getPlayer() {
        return mPlayer;
    }

    public void setPlayer(Player player) {
        mPlayer = player;
    }

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

    public double getGames() {
        return games;
    }

    public void setGames(double games) {
        this.games = games;
    }

    public double getPoints() {
        return points;
    }

    public void setPoints(double points) {
        this.points = points;
    }
}
