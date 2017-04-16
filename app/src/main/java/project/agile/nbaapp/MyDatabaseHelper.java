package project.agile.nbaapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by John on 2017/4/16.
 */
public class MyDatabaseHelper extends SQLiteOpenHelper {

    public static final String CREATE_PLAYER = "create table Player("
            +"Player text primary key,"
            +"Birth text primary key,"
            +"Season text primary key,"
            +"Lg text,"
            +"TeamAbrr text,"
            +"G text,"
            +"PTS text,"
            + " FOREIGN KEY(TeamAbrr) REFERENCES Team(TeamAbbr),"
            + "FOREIGN KEY(Lg) REFERENCES Team(Lg))";
    public static final String CREATE_TEAM = "create table Team("
            +"Lg text primary key,"
            +"TeamAbbr text primary key,"
            +"TeamName text,"
            +"TeamFrom text,"
            +"TeamTo text,"
            +"TeamG text,"
            +"TeamW text,"
            + "TeamL text,"
            + "TeamChamp text)";
    public static final String CREATE_COACH = "create table Coach("
            +"TeamCoach text primary key,"
            +"TeamSeason text primary key,"
            +"Lg text,"
            +"TeamAbbr text,"
            +"FOREIGN KEY(TeamAbrr) REFERENCES Team(TeamAbbr),"
            +"FOREIGN KEY(Lg) REFERENCES Team(Lg))";
    public static final String CREATE_ARENA = "create table Arena("
            +"Arena text primary key,"
            +"ArenaStart text primary key,"
            + "ArenaEnd text primary key,"
            +"Lg text,"
            +"TeamAbbr text,"
            +"ArenaLocation text,"
            +"ArenaCapacity text,"
            +"FOREIGN KEY(TeamAbrr) REFERENCES Team(TeamAbbr),"
            +"FOREIGN KEY(Lg) REFERENCES Team(Lg))";

    private Context mContext;

    public MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_PLAYER);
        db.execSQL(CREATE_TEAM);
        db.execSQL(CREATE_COACH);
        db.execSQL(CREATE_ARENA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
