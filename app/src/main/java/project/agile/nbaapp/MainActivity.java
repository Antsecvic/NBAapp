package project.agile.nbaapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import project.agile.DataAnalysis.ReadCSV;
import project.agile.util.SQLdm;
import project.agile.util.ToastUtil;
import project.agile.util.WriteToSD;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG = "MainActivity";

    private static final int UPDATE_SUCCESS = 0;
    private static final int UPDATE_FAILED = 1;

    private Handler mHandler;

    private Button playerButton;
    private Button teamButton;
    private Button coachButton;
    private Button arenaButton;
    private Button refreshButton;
    private Intent intent;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playerButton = (Button)findViewById(R.id.player_button);
        teamButton = (Button)findViewById(R.id.team_button);
        coachButton = (Button)findViewById(R.id.coach_button);
        arenaButton = (Button)findViewById(R.id.arena_button);
        refreshButton = (Button)findViewById(R.id.refresh_button);
        playerButton.setOnClickListener(this);
        teamButton.setOnClickListener(this);
        coachButton.setOnClickListener(this);
        arenaButton.setOnClickListener(this);
        refreshButton.setOnClickListener(this);

        mHandler = new Handler(){
            public void handleMessage(Message msg){
                switch (msg.what){
                    case UPDATE_SUCCESS:
                        // 更新成功的操作
                        progressDialog.dismiss();
                        ToastUtil.showToast(MainActivity.this, R.string.update_success);
                        break;
                    case UPDATE_FAILED:
                        // 更新失败的操作
                        progressDialog.dismiss();
                        ToastUtil.showToast(MainActivity.this, R.string.update_failed);
                        break;
                }
            }
        };

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.player_button:
                intent = new Intent(this,PlayerActivity.class);
                startActivity(intent);
                break;
            case R.id.team_button:
                intent = new Intent(this,TeamActivity.class);
                startActivity(intent);
                break;
            case R.id.coach_button:
                intent = new Intent(this,CoachActivity.class);
                startActivity(intent);
                break;
            case R.id.arena_button:
                intent = new Intent(this,ArenaActivity.class);
                startActivity(intent);
                break;
            case R.id.refresh_button:
                progressDialog = new ProgressDialog(MainActivity.this);
                progressDialog.setTitle(getResources().getString(R.string.loading_title));
                progressDialog.setMessage(getResources().getString(R.string.loading_message));
                progressDialog.setCancelable(false);
                progressDialog.show();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            SQLdm s = new SQLdm();
                            final SQLiteDatabase db = s.openDatabase(getApplicationContext());
                            db.execSQL("DELETE FROM Player;");
                            db.execSQL("DELETE FROM Arena;");
                            db.execSQL("DELETE FROM Coach;");
                            db.execSQL("DELETE FROM Team;");
                            Log.d(TAG, "Database Finish");
                            WriteToSD.writeToSD(getApplicationContext(), getResources().getString(R.string.file_name));
                            Log.d(TAG, "Copy File Finish");
                            ReadCSV readCSV = new ReadCSV();
                            readCSV.Insert(db, getResources().getString(R.string.file_name));
                            Log.d(TAG, "Insert Finish");
                            Message message = new Message();
                            message.what = UPDATE_SUCCESS;
                            mHandler.sendMessage(message);
                        }catch (Exception e){
                            e.printStackTrace();
                            Message message = new Message();
                            message.what = UPDATE_FAILED;
                            mHandler.sendMessage(message);
                        }
                    }
                }).start();
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacksAndMessages(null);
    }
}
