package models;

import java.util.ArrayList;

/**
 * Created by saryal on 12/22/15.
 */
public class Marriage {
    private ArrayList<Player> Players;
    private Settings Settings;
    private ArrayList<Round> Rounds;
    private TotalForGame FinalNumbers;

    private int CurrentRound;

    public int getCurrentRound() {
        return CurrentRound;
    }

    public void setCurrentRound(int currentRound) {
        CurrentRound = currentRound;
    }

    private int playerIdCount;

    public int getPlayerIdCount() {
        return playerIdCount;
    }

    public void setPlayerIdCount(int playerIdCount) {
        this.playerIdCount = playerIdCount;
    }

    public ArrayList<Player> getPlayers() {
        return Players;
    }

    public void setPlayers(ArrayList<Player> players) {
        Players = players;
    }

    public models.Settings getSettings() {
        return Settings;
    }

    public void setSettings(models.Settings settings) {
        Settings = settings;
    }

    public ArrayList<Round> getRounds() {
        return Rounds;
    }

    public void setRounds(ArrayList<Round> rounds) {
        Rounds = rounds;
    }

    public TotalForGame getFinalNumbers() {
        return FinalNumbers;
    }

    public void setFinalNumbers(TotalForGame finalNumbers) {
        FinalNumbers = finalNumbers;
    }
}
