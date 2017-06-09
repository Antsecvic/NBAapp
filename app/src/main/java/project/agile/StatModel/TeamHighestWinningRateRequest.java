package project.agile.StatModel;

import android.content.Context;
import android.util.Log;
import android.widget.LinearLayout;

/**
 * Created by Guure on 2017/6/7.
 */

public class TeamHighestWinningRateRequest implements IStatRequest {

    private String name = "历史胜率排行榜";

    private int position = 1;

    @Override
    public void init(LinearLayout content, Context context) {

    }

    @Override
    public void addChart() {
        Log.d("Guu", "highest WR!");
    }

    @Override
    public void requestData() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

}
