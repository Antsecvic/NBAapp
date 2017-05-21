package project.agile.Object;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by John on 2017/4/15.
 */
public class Player implements Serializable {
    private String name;
    private double birthYear;
    private List<PlayerInOneSeason> career;
    public void setName(String name) {
        this.name = name;
    }
    public String getName(){
        return name;
    }

    public Player(String name,double birthYear){
        this.name = name;
        this.birthYear = birthYear;
        career = new ArrayList<>();
    }

    public double getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(double birthYear) {
        this.birthYear = birthYear;
    }

    public List<PlayerInOneSeason> getCareer() {
        return career;
    }

    public void setCareer(List<PlayerInOneSeason> career) {
        this.career = career;
    }

    public void addOneSeason(PlayerInOneSeason playerInOneSeason) {
        career.add(playerInOneSeason);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(birthYear);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + ((name == null) ? 0 : name.hashCode());
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
        Player other = (Player) obj;
        if (Double.doubleToLongBits(birthYear) != Double.doubleToLongBits(other.birthYear))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }
}
