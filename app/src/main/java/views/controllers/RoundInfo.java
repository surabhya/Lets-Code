package views.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import helpers.ViewHelper;
import models.GameType;
import models.Round;
import models.RoundPlayer;
import helpers.Calculation;


public class RoundInfo extends ViewHelper {

    TableLayout tl;
    String[] row;
    String[] col;
    int numOfPlayers;
    GameType gameType;
    Round currentRound;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_round_info;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        currentRound = mainMarriage.getCurrentRound();
        gameType = mainMarriage.getSettings().getType();
        tl = (TableLayout) findViewById(R.id.maintable);
        addData();
        initialize();
    }

    public void addData() {

        numOfPlayers = currentRound.getPlayers().size();
        row = new String[numOfPlayers];
        for (int i = 0; i < numOfPlayers; i++) {
            row[i] = currentRound.getPlayers().get(i).getPlayer().getName();
        }
        col = new String[]{"Winner", "Seen", "Less", "Points"}; // get from database

        int rowCount = row.length;

        TableLayout.LayoutParams tableLayoutParams = new TableLayout.LayoutParams();
        TableRow.LayoutParams tableRowParams = new TableRow.LayoutParams();
        tableRowParams.setMargins(1, 1, 1, 1);
        tableRowParams.weight = 1;

        for (int i = 0; i <= rowCount; i++) {
            TableRow tableRow = new TableRow(this);
            tableRow.setId(i);
            // create tableRow
            for (int j = 0; j <= 4; j++) {
                //create textView
                int count = 1;
                TextView textView = new TextView(this);
                textView.setGravity(Gravity.CENTER);
                CheckBox checkBox = new CheckBox(this);
                EditText point = new EditText(this);
                if (i == 0 && j == 0) {
                    textView.setText(" ");
                    tableRow.addView(textView, tableRowParams);
                } else if (i > 0 && j == 0) {
                    textView.setText(row[i - 1]); // Player Header
                    tableRow.addView(textView, tableRowParams);
                } else if (i == 0 && j != 0) {
                    textView.setText(col[j - 1]); //Game Header
                    tableRow.addView(textView, tableRowParams);
                } else if (i > 0 && j == 1) {
                    checkBox.setText(""); // Is Winner
                    checkBox.setId(count++);
                    tableRow.addView(checkBox, tableRowParams);
                } else if (i > 0 && j == 2) {
                    checkBox.setText(""); // Is Seen
                    checkBox.setId(count++);
                    tableRow.addView(checkBox, tableRowParams);
                } else if (i > 0 && j == 3) {
                    checkBox.setText(""); // Is Less
                    checkBox.setId(count++);
                    tableRow.addView(checkBox, tableRowParams);
                } else if (i > 0 && j == 4) {
                    point.setInputType(100); // Points
                    point.setId(count++);
                    tableRow.addView(point, tableRowParams);
                }
            }
            tl.addView(tableRow, tableLayoutParams);
        }
    }

    public void initialize() {

        for (int i = 1; i <= numOfPlayers; i++) {
            int id = 0;
            TableRow t = (TableRow) findViewById(i);

            id++;
            id++;
            id++;
            id++;
            EditText point = (EditText) (t.getChildAt(id++));
            point.setText("0");
        }
        if (gameType == GameType.Murder) {
        }
    }

    public boolean validInput(View View){
        return true;
    }

    public void addRound(View view) {
            int totalPoint = 0;
            for (int i = 1; i <= numOfPlayers; i++) {
                int id = 0;
                TableRow t = (TableRow) findViewById(i);

                id++;
                CheckBox isWinner = (CheckBox) t.getChildAt(id++);
                CheckBox isSeen = (CheckBox) t.getChildAt(id++);
                CheckBox isLess = (CheckBox) t.getChildAt(id++);
                EditText point = (EditText) t.getChildAt(id++);
                totalPoint += Integer.parseInt(point.getText().toString());


                RoundPlayer player = currentRound.getPlayers().get(i - 1);
                player.setIsWinner(isWinner.isChecked());
                player.setIsSeen(isSeen.isChecked());
                player.setIsPlayingDuplee(isLess.isChecked());

                if (isWinner.isChecked() && isLess.isChecked()) {
                    player.setMaal(Integer.parseInt(point.getText().toString()) + 5);
                } else {
                    player.setMaal(Integer.parseInt(point.getText().toString()));
                }

            }
            Calculation calculationMurder = new Calculation(mainMarriage);
            if (mainMarriage.getSettings().getType() == GameType.Murder) {
                calculationMurder.calculateMurder(totalPoint);
            } else if (mainMarriage.getSettings().getType() == GameType.Kidnap) {
                displayError("Game Type Kidnap Not Implemented");
            } else {
                calculationMurder.calculateNormal(totalPoint);
                displayError("Game Type Normal Not Implemented");
            }
            Intent intent = new Intent(this, GameDashboard.class);
            startActivity(intent);
    }

    public void displayError(String msg){
        Toast toast = Toast.makeText(this,
                msg, Toast.LENGTH_SHORT);
        toast.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        // getMenuInflater().inflate(R.menu.menu_round_info, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        // if (id == R.id.action_settings) {
        //     return true;
        // }

        return super.onOptionsItemSelected(item);
    }
}