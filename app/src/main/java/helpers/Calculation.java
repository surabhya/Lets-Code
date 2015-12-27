package helpers;

import java.util.ArrayList;
import models.Marriage;
import models.RoundPlayer;


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

    public void calculateKidnap(int point){
        int totalPoint = point;
        int winnerPoint = 0;
        RoundPlayer winnerPlayer = null;
        for(RoundPlayer player : players){
            if(player.getIsWinner()){
                winnerPlayer = player;
            }else{
                if(!player.getIsSeen()){
                    winnerPoint += totalPoint + 10 + player.getMaal();
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

    public void calculateNormal(int point){
        int totalPoint = point;
        int winnerPoint = 0;
        RoundPlayer winnerPlayer = null;
        for(RoundPlayer player : players){
            if(player.getIsWinner()){
                winnerPlayer = player;
            }else{
                if(!player.getIsSeen()){
                    winnerPoint += totalPoint + 10 - player.getMaal();
                    player.setTotalPoints(player.getTotalPoints() - totalPoint - 10 + player.getMaal());
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
}
