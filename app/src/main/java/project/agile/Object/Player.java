package project.agile.Object;

import java.io.Serializable;

/**
 * Created by John on 2017/4/15.
 */
public class Player implements Serializable {
    private String name;
    private int birthYear;
    private String season;
    private String age;
    private String team;
    public void setName(String name) {
        this.name = name;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }
    public String getName(){
        return name;
    }
    public int getBirthYear(){
        return birthYear;
    }
}
