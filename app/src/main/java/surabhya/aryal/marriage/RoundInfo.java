package surabhya.aryal.marriage;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

import views.controllers.R;


public class RoundInfo extends ActionBarActivity {

    TableLayout tl;
    String[] row;
    String[] col;
    int numOfPlayers;
    ArrayList<PlayerInfo> players;
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_round_info);
      //  tl = (TableLayout) findViewById(R.id.maintable);
        dbHelper = new DatabaseHelper(this);
        addData();
    }

    public void addData() {

        players = dbHelper.findAllPlayerByGameID(0);
        numOfPlayers = players.size();
        row = new String[numOfPlayers];
        for (int i = 0; i < numOfPlayers; i++) {
            row[i] = players.get(i).getPlayerName();
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

    public void addRound(View view) {
        boolean isWinnerDoubly = false;
        double totalPoint = 0;
        for (int i = 1; i <= numOfPlayers; i++) {
            int id = 0;
            TableRow t = (TableRow) findViewById(i);

            id++;
            CheckBox isWinner = (CheckBox) t.getChildAt(id++);
            CheckBox isSeen = (CheckBox) t.getChildAt(id++);
            CheckBox isLess = (CheckBox) t.getChildAt(id++);
            EditText point = (EditText) t.getChildAt(id++);
            totalPoint += Double.parseDouble(point.getText().toString());

            PlayerInfo player = players.get(i-1);
            player.setWinner(isWinner.isChecked());
            player.setSeen(isSeen.isChecked());
            player.setLess(isLess.isChecked());

            if(isWinner.isChecked() && isLess.isChecked()){
                player.setCurrentPoint(Double.parseDouble(point.getText().toString()) + 5);
            }else{
                player.setCurrentPoint(Double.parseDouble(point.getText().toString()));
            }

        }
        Calculation calculationMurder = new Calculation(this);

        if(dbHelper.findGameByGameID(1).getGameType().equals("murder")){
            calculationMurder.calculateMurder(totalPoint);
        }else if(dbHelper.findGameByGameID(0).getGameType().equals("kidnap")){
            Log.e("Game Type Kidnap ", "");
        }else{
            calculationMurder.calculateNormal(totalPoint);
            Log.e("Game Type Normal ", "");
        }
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
