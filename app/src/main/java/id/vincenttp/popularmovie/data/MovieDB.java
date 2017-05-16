package id.vincenttp.popularmovie.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by DELL on 5/16/2017.
 */

public class MovieDB extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "movie.db";
    private static final int VERSION = 1;
    public MovieDB(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String CREATE_TABLE = "CREATE TABLE "  + MovieContract.MovieEntry.TABLE_NAME + " (" +
                MovieContract.MovieEntry._ID                + " INTEGER PRIMARY KEY, " +
                MovieContract.MovieEntry.COLUMN_ID + " INTEGER NOT NULL, " +
                MovieContract.MovieEntry.COLUMN_TITLE + " TEXT NOT NULL, " +
                MovieContract.MovieEntry.COLUMN_VOTE + " TEXT NOT NULL, " +
                MovieContract.MovieEntry.COLUMN_DATE + " TEXT NOT NULL, " +
                MovieContract.MovieEntry.COLUMN_SINOPSIS + " TEXT NOT NULL, " +
                MovieContract.MovieEntry.COLUMN_IMAGE + " TEXT NOT NULL, "+
                "unique ("+ MovieContract.MovieEntry.COLUMN_ID+"));";

        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + MovieContract.MovieEntry.TABLE_NAME);
        onCreate(db);
    }
}
