package project.agile.Object;

import java.io.Serializable;

/**
 * Created by John on 2017/4/15.
 */
public class Player implements Serializable {
    private String name;
    private String age;
    public void setName(String name) {
        this.name = name;
    }
    public String getName(){
        return name;
    }
    public String getAge(){
        return age;
    }
    public void setAge(String age) { this.age = age;}
}
