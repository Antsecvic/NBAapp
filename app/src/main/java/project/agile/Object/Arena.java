package project.agile.Object;

import java.io.Serializable;

/**
 * Created by John on 2017/4/16.
 */
public class Arena implements Serializable{
    private String arenaName;
    private String arenaLocation;

    public void setArenaName(String arenaName) {
        this.arenaName = arenaName;
    }

    public void setArenaLocation(String arenaLocation) {
        this.arenaLocation = arenaLocation;
    }

    public String getArenaLocation() {
        return arenaLocation;
    }

    public String getArenaName() {
        return arenaName;
    }

}
