package models;

/**
 * Created by saryal on 12/22/15.
 */
public class TotalForGame {
    private RoundPlayer Player;

    public int getTotalRounds() {
        return TotalRounds;
    }

    public void setTotalRounds(int totalRounds) {
        TotalRounds = totalRounds;
    }

    public RoundPlayer getPlayer() {
        return Player;
    }

    public void setPlayer(RoundPlayer player) {
        Player = player;
    }

    private int TotalRounds;
}
