package surabhya.aryal.marriage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.lang.reflect.Array;
import java.util.ArrayList;

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
                DatabaseContract.PlayerEntry.COLUMN_NAME_GAME_ID + " INTEGER NOT NULL, " +
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
                DatabaseContract.GameEntry.COLUMN_NAME_NUM_PLAYER + " INTEGER NOT NULL," +
                DatabaseContract.GameEntry.COLUMN_NAME_GAME_TYPE + " TEXT NOT NULL, " +
                DatabaseContract.GameEntry.COLUMN_NAME_MONEY_PER_POINT + " REAL NOT NULL, " +
                DatabaseContract.GameEntry.COLUMN_NAME_BETTER_MONEY + " REAL NOT NULL, " +
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

    public long addGameInfo(int numOfPlayers, String gameType, double moneyPerGame, double betterMoney){
        ContentValues values = new ContentValues();
        values.put(DatabaseContract.GameEntry.COLUMN_NAME_NUM_PLAYER, numOfPlayers);
        values.put(DatabaseContract.GameEntry.COLUMN_NAME_GAME_TYPE, gameType);
        values.put(DatabaseContract.GameEntry.COLUMN_NAME_MONEY_PER_POINT, moneyPerGame);
        values.put(DatabaseContract.GameEntry.COLUMN_NAME_BETTER_MONEY, betterMoney);
        SQLiteDatabase db = this.getWritableDatabase();
        long newRowId;
        newRowId = db.insert(
                DatabaseContract.GameEntry.TABLE_NAME,
                null,
                values);
        db.close();
        return newRowId;
    }

    public ArrayList<GameInfo> getAllGame(){

        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<GameInfo> data = new ArrayList<GameInfo>();

        String[] projection = {
                DatabaseContract.GameEntry.COLUMN_NAME_GAME_ID,
                DatabaseContract.GameEntry.COLUMN_NAME_NUM_PLAYER,
                DatabaseContract.GameEntry.COLUMN_NAME_GAME_TYPE,
                DatabaseContract.GameEntry.COLUMN_NAME_MONEY_PER_POINT,
                DatabaseContract.GameEntry.COLUMN_NAME_BETTER_MONEY,
                DatabaseContract.GameEntry.COLUMN_NAME_GRAND_TOTAL_MONEY
        };

        String sortOrder =
                DatabaseContract.GameEntry.COLUMN_NAME_GAME_ID + " ASC";

        Cursor c = db.query(
                DatabaseContract.GameEntry.TABLE_NAME,  // The table to query
                projection,                               // The columns to return
                null,                                // The columns for the WHERE clause
                null,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );

        while(c.moveToNext()){
            GameInfo item = new GameInfo(c.getInt(0),c.getInt(1),c.getString(2),c.getDouble(3),c.getDouble(4),c.getDouble(5));
            data.add(item);
        }
        c.close();
        db.close();
        return data;
    }

    public GameInfo findGameByGameID(int gameId){
        String query = "Select * FROM " + DatabaseContract.GameEntry.TABLE_NAME + " WHERE " + DatabaseContract.GameEntry.COLUMN_NAME_GAME_ID + " =  \"" + gameId + "\"";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(query, null);
        GameInfo item ;

        if (c.moveToFirst()) {
            c.moveToFirst();
            item = new GameInfo(c.getInt(0),c.getInt(1),c.getString(2),c.getDouble(3),c.getDouble(4),c.getDouble(5));
            c.close();
        } else {
            item = null;
        }
        db.close();
        return item;
    }

    public ArrayList<PlayerInfo> findAllPlayerByGameID(int gameId){
        String query = "Select * FROM " + DatabaseContract.PlayerEntry.TABLE_NAME + " WHERE " + DatabaseContract.PlayerEntry.COLUMN_NAME_GAME_ID + " =  \"" + gameId + "\"";
        ArrayList<PlayerInfo> data = new ArrayList<PlayerInfo>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(query, null);

        while(c.moveToNext()){
            PlayerInfo item = new PlayerInfo(c.getInt(0),c.getInt(1),c.getString(2),c.getInt(3)>0,c.getInt(4) > 0, c.getInt(5)>0,c.getDouble(6),c.getDouble(7));
            data.add(item);
        }
        db.close();
        return data;
    }

    public PlayerInfo findPlayer(int gameId, int playerID){
        String query = "Select * FROM " + DatabaseContract.PlayerEntry.TABLE_NAME + " WHERE " + DatabaseContract.PlayerEntry.COLUMN_NAME_GAME_ID + " =  \"" + gameId + "\""
                        + " and " +  DatabaseContract.PlayerEntry.COLUMN_NAME_PLAYER_ID + " =  \"" + playerID + "\"";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(query, null);
        PlayerInfo item = null;
        while(c.moveToNext()){
            item = new PlayerInfo(c.getInt(0),c.getInt(1),c.getString(2),c.getInt(3)>0,c.getInt(4) > 0, c.getInt(5)>0,c.getDouble(6),c.getDouble(7));
        }
        db.close();
        return item;
    }
}
