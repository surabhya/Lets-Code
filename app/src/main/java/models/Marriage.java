package models;

import android.drm.DrmStore;

import java.util.ArrayList;

/**
 * Created by saryal on 12/22/15.
 */
public class Marriage {
    private ArrayList<Player> Players;
    private Settings Settings;
    private ArrayList<Round> Rounds;
    private TotalForGame FinalNumbers;

    private Round CurrentRound;

    public Round getCurrentRound() {
        return CurrentRound;
    }

    public void setCurrentRound(Round currentRound) {
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

    public int getNextRoundId(){
        if(Rounds.size()==0) return 0;
        int id = Rounds.get(0).getId();
        for(int i = 1; i < Rounds.size(); i++){
            if(id<Rounds.get(i).getId()) id=Rounds.get(i).getId();
        }
        return id+1;
    }
}
