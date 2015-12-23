package models;

/**
 * Created by saryal on 12/22/15.
 */
public class Settings {
    private String Name;
    private int NoOfPlayers;
    private GameType Type;
    private int BetterPoints;
    private Double MoneyPerPoints;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getNoOfPlayers() {
        return NoOfPlayers;
    }

    public void setNoOfPlayers(int noOfPlayers) {
        NoOfPlayers = noOfPlayers;
    }

    public GameType getType() {
        return Type;
    }

    public void setType(GameType type) {
        Type = type;
    }

    public int getBetterPoints() {
        return BetterPoints;
    }

    public void setBetterPoints(int betterPoints) {
        BetterPoints = betterPoints;
    }

    public Double getMoneyPerPoints() {
        return MoneyPerPoints;
    }

    public void setMoneyPerPoints(Double moneyPerPoints) {
        MoneyPerPoints = moneyPerPoints;
    }
}
