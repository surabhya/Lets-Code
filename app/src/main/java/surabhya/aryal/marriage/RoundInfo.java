package surabhya.aryal.marriage;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
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
    DatabaseHelper dbHelper = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_round_info);
        tl = (TableLayout) findViewById(R.id.maintable);
        addData();
    }

    public void addData(){

        ArrayList<PlayerInfo> players = dbHelper.findAllPlayerByGameID(0);
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
        int count = 1;

        for (int i = 0; i <= rowCount; i++) {
            TableRow tableRow = new TableRow(this);
            // create tableRow
            for (int j = 0; j <= 4; j++) {
                //create textView
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
        for(i = 1; i <=numOfPlayers*4)
        TableRow t = (TableRow) view;
        TextView firstTextView = (TextView) t.getChildAt(0);
        TextView secondTextView = (TextView) t.getChildAt(1);
        String firstText = firstTextView.getText().toString();
        String secondText = secondTextView.getText().toString();
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
