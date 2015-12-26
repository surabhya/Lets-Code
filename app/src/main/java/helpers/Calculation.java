package helpers;

import android.content.Context;
import android.util.Log;
import java.util.ArrayList;

import models.Marriage;
import models.RoundPlayer;
import surabhya.aryal.marriage.PlayerInfo;

/**
 * Created by saryal on 12/13/15.
 */

public class Calculation  {

    int numOfPlayers;
    ArrayList<RoundPlayer>  players;
    Marriage mainMarriage;

    public Calculation(Marriage marriage){
        mainMarriage = marriage;
        players = mainMarriage.getCurrentRound().getPlayers();
        numOfPlayers = players.size();

    }

    public void calculateMurder(int point){
        int totalPoint = point;
        int winnerPoint = 0;
        RoundPlayer winnerPlayer = null;
        for(RoundPlayer player : players){
            if(player.getIsWinner()){
                winnerPlayer = player;
            }else{
                if(!player.getIsSeen()){
                    winnerPoint += totalPoint + 10;
                    player.setTotalPoints(player.getTotalPoints() - totalPoint - 10);
                }else if (player.getIsSeen() && player.getIsPlayingDuplee()){
                    winnerPoint += totalPoint - player.getMaal();
                    player.setTotalPoints(player.getTotalPoints() - totalPoint + player.getMaal());
                }else if (player.getIsSeen()){
                    winnerPoint += totalPoint + 3 - player.getMaal();
                    player.setTotalPoints(player.getTotalPoints() - totalPoint - 3 + player.getMaal());
                }
            }
        }
        winnerPlayer.setTotalPoints(winnerPlayer.getTotalPoints() + winnerPoint);
    }

    public void calculateKidnap(double point){
        double totalPoint = point;
        Log.e("Game Type ", "Kidnap");

    }

    public void calculateNormal(double point){
        double totalPoint = point;
        Log.e("Game Type ", "Murder");
    }

}
