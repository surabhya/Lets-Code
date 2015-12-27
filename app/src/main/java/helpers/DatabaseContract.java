package helpers;

import android.provider.BaseColumns;

/**
 * Created by saryal on 11/23/15.
 */

public class DatabaseContract {

    public DatabaseContract(){
    }

    public static abstract class PlayerEntry implements BaseColumns{
        public static final String TABLE_NAME = "playerEntry";
        public static final String COLUMN_NAME_GAME_ID = "gameId";
        public static final String COLUMN_NAME_PLAYER_ID = "playerId";
        public static final String COLUMN_NAME_PLAYER_NAME = "playerName";
        public static final String COLUMN_NAME_IS_SEEN = "seen";
        public static final String COLUMN_NAME_IS_WINNER = "winner";
        public static final String COLUMN_NAME_IS_LESS = "less";
        public static final String COLUMN_NAME_CURRENT_POINT = "currentPoint";
        public static final String COLUMN_NAME_CURRENT_TOTAL = "currentTotal";
    }

    public static abstract class GameEntry implements BaseColumns{
        public static final String TABLE_NAME = "gameEntry";
        public static final String COLUMN_NAME_GAME_ID = "gameId";
        public static final String COLUMN_NAME_NUM_PLAYER= "playerNum";
        public static final String COLUMN_NAME_GAME_TYPE = "gameType";
        public static final String COLUMN_NAME_MONEY_PER_POINT = "moneyPerPoint";
        public static final String COLUMN_NAME_BETTER_MONEY= "betterMoney";
        public static final String COLUMN_NAME_GRAND_TOTAL_MONEY= "totalMoney";
    }

}
