package project.agile.Object;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by John on 2017/4/15.
 */
public class Player implements Serializable {
    private String name;
    private int birthYear;

    public Player(String name,int birthYear){
        this.name = name;
        this.birthYear = birthYear;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName(){
        return name;
    }

    public int getBirthYear() {
        return birthYear;
    }
    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }
}
