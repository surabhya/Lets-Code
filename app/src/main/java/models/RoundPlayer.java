package models;

/**
 * Created by saryal on 12/22/15.
 */
public class RoundPlayer {
    private Player player;
    private Boolean IsWinner;
    private Boolean IsSeen;
    private Boolean IsPlayingDuplee;
    private Boolean IsNotPlaying;

    public Boolean getIsNotPlaying() {
        return IsNotPlaying;
    }

    public void setIsNotPlaying(Boolean isNotPlaying) {
        IsNotPlaying = isNotPlaying;
    }

    private int Maal; //Maal
    private int TotalPoints; //Calculated for each round

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Boolean getIsWinner() {
        return IsWinner;
    }

    public void setIsWinner(Boolean isWinner) {
        IsWinner = isWinner;
    }

    public Boolean getIsSeen() {
        return IsSeen;
    }

    public void setIsSeen(Boolean isSeen) {
        IsSeen = isSeen;
    }

    public Boolean getIsPlayingDuplee() {
        return IsPlayingDuplee;
    }

    public void setIsPlayingDuplee(Boolean isPlayingDuplee) {
        IsPlayingDuplee = isPlayingDuplee;
    }

    public int getMaal() {
        return Maal;
    }

    public void setMaal(int maal) {
        Maal = maal;
    }

    public int getTotalPoints() {
        return TotalPoints;
    }

    public void setTotalPoints(int totalPoints) {
        TotalPoints = totalPoints;
    }
}
