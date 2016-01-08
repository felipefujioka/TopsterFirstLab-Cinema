package data_persistency;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by felipefujioka on 1/5/16.
 */
public class SQLiteUtil extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "MOVIES";
    private static final String MOVIE_TABLE_NAME = "MOVIES_TABLE";
    private static final String THEATER_TABLE_NAME = "THEATER_TABLE";


    private static final String MOVIES_TABLE_CREATE = "CREATE TABLE " + MOVIE_TABLE_NAME + "(name text)";
    private static final String THEATERS_TABLE_CREATE = "CREATE TABLE " + THEATER_TABLE_NAME + "(name text)";



    SQLiteUtil (Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(MOVIES_TABLE_CREATE);
        db.execSQL(THEATERS_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
