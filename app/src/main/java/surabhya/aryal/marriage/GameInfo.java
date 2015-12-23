package surabhya.aryal.marriage;

/**
 * Created by saryal on 12/11/15.
 */
public class GameInfo {

    private int gameId;
    private int playerNum;
    private String gameType;
    private double moneyPerPoint;
    private double betterMoney;
    private double totalMoney;


    public GameInfo(int gameId, int playerNum, String gameType, double moneyPerPoint, double betterMoney, double totalMoney) {
        this.gameId =  gameId;
        this.playerNum = playerNum;
        this.gameType = gameType;
        this.moneyPerPoint = moneyPerPoint;
        this.betterMoney = betterMoney;
        this.totalMoney = totalMoney;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public int getPlayerNum() {
        return playerNum;
    }

    public void setPlayerNum(int playerNum) {
        this.playerNum = playerNum;
    }

    public String getGameType() {
        return gameType;
    }

    public void setGameType(String gameType) {
        this.gameType = gameType;
    }

    public double getMoneyPerPoint() {
        return moneyPerPoint;
    }

    public void setMoneyPerPoint(double moneyPerPoint) {
        this.moneyPerPoint = moneyPerPoint;
    }

    public double getBetterMoney() {
        return betterMoney;
    }

    public void setBetterMoney(double betterMoney) {
        this.betterMoney = betterMoney;
    }

    public double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(double totalMoney) {
        this.totalMoney = totalMoney;
    }


}
