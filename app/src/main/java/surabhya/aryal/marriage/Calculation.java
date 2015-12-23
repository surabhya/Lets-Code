package surabhya.aryal.marriage;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import java.util.ArrayList;

/**
 * Created by saryal on 12/13/15.
 */

public class Calculation extends AppCompatActivity {

    int numOfPlayers;
    DatabaseHelper dbHelper;
    ArrayList<PlayerInfo>  players;

    public Calculation(Context ctx){
        dbHelper = new DatabaseHelper(ctx);
        players = dbHelper.findAllPlayerByGameID(0);
        numOfPlayers = players.size();

    }

    public void calculateMurder(double point){
        double totalPoint = point;
        double winnerPoint = 0;
        PlayerInfo winnerPlayer = null;
        for(PlayerInfo player : players){
            if(player.isWinner()){
                winnerPlayer = player;
            }else{
                if(!player.isSeen()){
                    winnerPoint += totalPoint + 10;
                    player.setCurrentTotal(player.getCurrentTotal() - totalPoint -10);
                }else if (player.isSeen() && player.isLess()){
                    winnerPoint += totalPoint - player.getCurrentPoint();
                    player.setCurrentTotal(player.getCurrentTotal() - totalPoint + player.getCurrentPoint());
                }else if (player.isSeen()){
                    winnerPoint += totalPoint + 3 - player.getCurrentPoint();
                    player.setCurrentTotal(player.getCurrentTotal() - totalPoint - 3 + player.getCurrentPoint());
                }
            }
        }
        winnerPlayer.setCurrentTotal(winnerPlayer.getCurrentTotal() + winnerPoint);
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
