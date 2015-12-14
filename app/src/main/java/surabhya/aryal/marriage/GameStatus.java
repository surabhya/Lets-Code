package surabhya.aryal.marriage;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

public class GameStatus extends Activity {

    TableLayout tl;
    String[] playerName;
    ArrayList<Integer[]> row;
    DatabaseHelper dbHelper = new DatabaseHelper(this);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_status);
        tl = (TableLayout) findViewById(R.id.gameStatusTable);
        addData();
    }

    public void addData(){
        ArrayList<PlayerInfo> players = dbHelper.findAllPlayerByGameID(0);
        int numOfPlayers = players.size();
        playerName = new String[numOfPlayers];
        row = new ArrayList<Integer[]>();  // get from database
        for(int i =0; i<numOfPlayers; i++){
            playerName[i] = players.get(i).playerName;
            row.add(new Integer[]{1,2,3,4,5,6,7});
        }
        int[] total = new int[numOfPlayers];
        int rowCount= numOfPlayers;

        TableLayout.LayoutParams tableLayoutParams = new TableLayout.LayoutParams();
        TableRow.LayoutParams tableRowParams = new TableRow.LayoutParams();
        tableRowParams.setMargins(1, 1, 1, 1);
        tableRowParams.weight = 1;

        for (int i = 0; i <= rowCount+1; i++) {
            TableRow tableRow = new TableRow(this);
            // create tableRow
            for (int j = 0; j <= rowCount; j++) {
                //create textView
                TextView textView = new TextView(this);
                textView.setGravity(Gravity.CENTER);
                if(i==0 && j==0){
                    textView.setText("Round");
                }else if(i==rowCount+1 && j==0){
                    textView.setText("Total");
                }else if(i==rowCount+1 && j!=0){
                    textView.setText(total[j-1]+""); // Sum
                }else if(i==0 && j!=0){
                    textView.setText(playerName[j-1]); // Player Header
                } else if(i!=0 && j>0){
                    total[j-1] += row.get(i-1)[j-1];
                    textView.setText(row.get(i-1)[j-1]+"");
                }else{
                    textView.setText("Round" + i);
                }
                //add textView to tableRow
                tableRow.addView(textView, tableRowParams);
            }
            //dd tableRow to tableLayout
            tl.addView(tableRow, tableLayoutParams);
        }
    }

    public void newRound(View view){
        Intent intent = new Intent(this, RoundInfo.class);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
