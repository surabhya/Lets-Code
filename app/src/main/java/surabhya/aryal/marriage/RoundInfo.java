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


public class RoundInfo extends ActionBarActivity {

    TableLayout tl;
    String[] row;
    String[] col;
    int numOfPlayers;
    ArrayList<PlayerInfo> players;
    DatabaseHelper dbHelper = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_round_info);
        tl = (TableLayout) findViewById(R.id.maintable);
        addData();
    }

    public void addData(){

        players = dbHelper.findAllPlayerByGameID(0);
        numOfPlayers = players.size();
        row = new String[numOfPlayers];
        for(int i =0; i<numOfPlayers; i++){
            row[i] = players.get(i).playerName;
        }
        col  = new String[]{ "Winner", "Seen", "Less", "Points"}; // get from database

        int rowCount=row.length;

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
                if(i==0 && j==0){
                    textView.setText(" ");
                    tableRow.addView(textView, tableRowParams);
                }else if(i>0 && j==0){
                    textView.setText(row[i - 1]); // Player Header
                    tableRow.addView(textView, tableRowParams);
                }else if(i==0 && j!=0){
                    textView.setText(col[j-1]); //Game Header
                    tableRow.addView(textView, tableRowParams);
                } else if(i>0 && j==1){
                    checkBox.setText(""); // Is Winner
                    checkBox.setId(count++);
                    tableRow.addView(checkBox, tableRowParams);
                }else if(i>0 && j==2){
                    checkBox.setText(""); // Is Seen
                    checkBox.setId(count++);
                    tableRow.addView(checkBox, tableRowParams);
                }else if(i>0 && j==3){
                    checkBox.setText(""); // Is Less
                    checkBox.setId(count++);
                    tableRow.addView(checkBox, tableRowParams);
                }else if(i>0 && j==4){
                    point.setInputType(100); // Points
                    point.setId(count++);
                    tableRow.addView(point, tableRowParams);
                }
            }
            tl.addView(tableRow, tableLayoutParams);
        }
    }

    public void addRound(View view){
        TableLayout table = (TableLayout) view.findViewById(R.id.maintable);
        for(int i=0; i<numOfPlayers; i++){
            int id = 1;
            TableRow t = (TableRow) findViewById(i);
            TextView isWinner = (TextView) t.getChildAt(1);
            Log.e("isWinner ", isWinner.toString());
//            TextView isSeen = (TextView) t.getChildAt(id++%4);
//            TextView isLess = (TextView) t.getChildAt(id++%4);
//            EditText point = (EditText) t.getChildAt(id++%4);
//            Log.e("isSeen ", isSeen+"");
//            Log.e("isLess ", isLess+"");
//            Log.e("point ", point+"");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_round_info, menu);
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
}
