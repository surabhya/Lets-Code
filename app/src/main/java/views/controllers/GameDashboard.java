package views.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import helpers.ViewHelper;
import models.Player;
import models.Round;
import models.RoundPlayer;

public class GameDashboard extends ViewHelper {

    TableLayout tableLayout;
    TableRow tableRow;
    ArrayList<Player> players;
    int numOfPlayers;
    int noOfRounds;
    String[] playerName;
    ArrayList<Integer[]> roundInfo;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_game_dashboard;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tableLayout = (TableLayout) findViewById(R.id.gameStatusTable);
        roundInfo = new ArrayList<Integer[]>();
        players = mainMarriage.getPlayers();
        numOfPlayers = mainMarriage.getSettings().getNoOfPlayers();
        noOfRounds = mainMarriage.getRounds().size();

        getPointsPerRound(mainMarriage.getRounds());
        getPlayerNames();
        addData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_game_dashboard, menu);
        return true;
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

    public void getPlayerNames(){
        playerName = new String[numOfPlayers];
        for(int i =0; i < numOfPlayers; i++){
            playerName[i] = players.get(i).getName();
        }
    }

    public void getPointsPerRound(ArrayList<Round> roundList){
        for(int i =0; i<roundList.size(); i++){
            Integer[] points = new Integer[roundList.get(i).getPlayers().size()];
            for(int j = 0; j < roundList.get(i).getPlayers().size(); j++){
                points[j] = roundList.get(i).getPlayers().get(j).getTotalPoints();
            }
            roundInfo.add(points);
        }
    }

    public void addData(){
        int[] total = new int[numOfPlayers];

        TableLayout.LayoutParams tableLayoutParams = new TableLayout.LayoutParams();
        TableRow.LayoutParams tableRowParams = new TableRow.LayoutParams();
        tableRowParams.setMargins(1, 1, 1, 1);
        tableRowParams.weight = 1;

        for (int i = 0; i <= noOfRounds; i++) {
            tableRow = new TableRow(this);
            // create tableRow
            for (int j = 0; j <= numOfPlayers; j++) {
                //create textView
                TextView textView = new TextView(this);
                textView.setGravity(Gravity.BOTTOM);
                if(i==0 && j==0){
                    textView.setText("Round");
                }else if(i==numOfPlayers+1 && j==0){
                    textView.setText("Total");
                }else if(i==numOfPlayers+1 && j!=0){
                    textView.setText(total[j-1]+""); // Sum
                }else if(i==0 && j!=0){
                    textView.setText(playerName[j-1]); // Player Header
                } else if(i!=0 && j>0){
                    total[j-1] += roundInfo.get(i-1)[j-1];
                    textView.setText(roundInfo.get(i-1)[j-1]+"");
                }else{
                    textView.setText("Round" + i);
                }
                //add textView to tableRow
                tableRow.addView(textView, tableRowParams);
            }
            //dd tableRow to tableLayout
            tableLayout.addView(tableRow, tableLayoutParams);
        }
    }

    public void newRound(View view){
        Round r = new Round();
        r.setId(mainMarriage.getNextRoundId());
        ArrayList<RoundPlayer> rps = new ArrayList<RoundPlayer>();
        for(Player p : mainMarriage.getPlayers()){
            RoundPlayer rp = p.convertToRoundPlayer();
            rps.add(rp);
        }
        r.setPlayers(rps);
        mainMarriage.getRounds().add(r);
        mainMarriage.setCurrentRound(r);
        Intent intent = new Intent(this, RoundInfo.class);
        startActivity(intent);
    }

}
