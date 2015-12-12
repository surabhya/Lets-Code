package surabhya.aryal.marriage;

/**
 * Created by saryal on 12/11/15.
 */


public class PlayerInfo {
    int gameID;
    int playerId;
    String playerName;
    boolean seen;
    boolean winner;
    boolean less;
    double currentPoint;
    double currentTotal;

    public PlayerInfo(int gameID, int playerId, String playerName, boolean seen, boolean winner,
                      boolean less, double currentPoint, double currentTotal) {
        this.gameID = gameID;
        this.playerId = playerId;
        this.playerName = playerName;
        this.seen = seen;
        this.winner = winner;
        this.less = less;
        this.currentPoint = currentPoint;
        this.currentTotal = currentTotal;
    }

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public boolean isSeen() {
        return seen;
    }

    public void setSeen(boolean seen) {
        this.seen = seen;
    }

    public boolean isWinner() {
        return winner;
    }

    public void setWinner(boolean winner) {
        this.winner = winner;
    }

    public boolean isLess() {
        return less;
    }

    public void setLess(boolean less) {
        this.less = less;
    }

    public double getCurrentPoint() {
        return currentPoint;
    }

    public void setCurrentPoint(double currentPoint) {
        this.currentPoint = currentPoint;
    }

    public double getCurrentTotal() {
        return currentTotal;
    }

    public void setCurrentTotal(double currentTotal) {
        this.currentTotal = currentTotal;
    }

}
