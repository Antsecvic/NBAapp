package project.agile.Object;

import java.io.Serializable;

/**
 * Created by John on 2017/4/16.
 */
public class Team implements Serializable{

    private String league;
    private String abbr;
    private String name;
    private int from;
    private int to;
    private int games;
    private int wins;
    private int loses;
    private int champions;
    public String getLeague() {
        return league;
    }
    public void setLeague(String league) {
        this.league = league;
    }
    public String getAbbr() {
        return abbr;
    }
    public void setAbbr(String abbr) {
        this.abbr = abbr;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getFrom() {
        return from;
    }
    public void setFrom(int from) {
        this.from = from;
    }
    public int getTo() {
        return to;
    }
    public void setTo(int to) {
        this.to = to;
    }
    public int getGames() {
        return games;
    }
    public void setGames(int games) {
        this.games = games;
    }
    public int getWins() {
        return wins;
    }
    public void setWins(int wins) {
        this.wins = wins;
    }
    public int getLoses() {
        return loses;
    }
    public void setLoses(int loses) {
        this.loses = loses;
    }
    public int getChampions() {
        return champions;
    }
    public void setChampions(int champions) {
        this.champions = champions;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((abbr == null) ? 0 : abbr.hashCode());
        result = prime * result + ((league == null) ? 0 : league.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Team other = (Team) obj;
        if (abbr == null) {
            if (other.abbr != null)
                return false;
        } else if (!abbr.equals(other.abbr))
            return false;
        if (league == null) {
            if (other.league != null)
                return false;
        } else if (!league.equals(other.league))
            return false;
        return true;
    }

}
