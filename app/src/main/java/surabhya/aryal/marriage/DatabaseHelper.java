package surabhya.aryal.marriage;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by saryal on 11/23/15.
 */
public class DatabaseHelper extends SQLiteOpenHelper {


    // If you change the database schema, you must increment the database version.
    private static final int DATABASE_VERSION =1;
    static final String DATABASE_NAME = "marriageGame.db";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String SQL_CREATE_PLAYER_TABLE = "CREATE TABLE " + DatabaseContract.PlayerEntry.TABLE_NAME + " (" +
                DatabaseContract.PlayerEntry.COLUMN_NAME_PLAYER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                DatabaseContract.PlayerEntry.COLUMN_NAME_PLAYER_NAME + " TEXT NOT NULL, " +
                DatabaseContract.PlayerEntry.COLUMN_NAME_IS_SEEN + " INTEGER NOT NULL, " +
                DatabaseContract.PlayerEntry.COLUMN_NAME_IS_WINNER + " INTEGER NOT NULL, " +
                DatabaseContract.PlayerEntry.COLUMN_NAME_IS_LESS + " INTEGER NOT NULL, " +
                DatabaseContract.PlayerEntry.COLUMN_NAME_CURRENT_POINT + " INTEGER NOT NULL, " +
                DatabaseContract.PlayerEntry.COLUMN_NAME_CURRENT_TOTAL + " INTEGER NOT NULL " +
                " )";
        sqLiteDatabase.execSQL(SQL_CREATE_PLAYER_TABLE);

        final String SQL_CREATE_GAME_TABLE = "CREATE TABLE " + DatabaseContract.GameEntry.TABLE_NAME + " (" +
                DatabaseContract.GameEntry.COLUMN_NAME_GAME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                DatabaseContract.GameEntry.COLUMN_NAME_PLAYER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                DatabaseContract.GameEntry.COLUMN_NAME_MONEY_PER_POINT + " REAL NOT NULL, " +
                DatabaseContract.GameEntry.COLUMN_NAME_GRAND_TOTAL_POINT + " INTEGER NOT NULL, " +
                DatabaseContract.GameEntry.COLUMN_NAME_GRAND_TOTAL_MONEY + " REAL NOT NULL " +
                " )";
        sqLiteDatabase.execSQL(SQL_CREATE_GAME_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DatabaseContract.PlayerEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DatabaseContract.GameEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
