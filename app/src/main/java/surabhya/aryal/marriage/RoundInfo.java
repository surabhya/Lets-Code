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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_round_info);
        tl = (TableLayout) findViewById(R.id.maintable);
        addData();
    }

    public void addData(){

        row  = new String[]{ "Player 1", "Player 2", "Player 3", "Player 4"}; // get from database
        col  = new String[]{ "Winner", "Seen", "Less", "Points"}; // get from database

        int rowCount=row.length;

        TableLayout.LayoutParams tableLayoutParams = new TableLayout.LayoutParams();
        TableRow.LayoutParams tableRowParams = new TableRow.LayoutParams();
        tableRowParams.setMargins(1, 1, 1, 1);
        tableRowParams.weight = 1;

        for (int i = 0; i <= rowCount; i++) {
            TableRow tableRow = new TableRow(this);
            // create tableRow
            for (int j = 0; j <= rowCount; j++) {
                //create textView
                TextView textView = new TextView(this);
                textView.setGravity(Gravity.CENTER);
                RadioGroup radioGroup = new RadioGroup(this);
                RadioButton radioWinner = new RadioButton(this);
                CheckBox checkBox = new CheckBox(this);
                EditText point = new EditText(this);
                if(i==0 && j==0){
                    textView.setText(" ");
                    tableRow.addView(textView, tableRowParams);
                }else if(i>0 && j==0){
                    textView.setText(row[i - 1]);
                    tableRow.addView(textView, tableRowParams);
                }else if(i==0 && j!=0){
                    textView.setText(col[j-1]); // Player Header
                    tableRow.addView(textView, tableRowParams);
                } else if(i>0 && j==1){
                    radioWinner.setText("");
                    radioWinner.setId(j-1);
                    radioGroup.addView(radioWinner);
                    tableRow.addView(radioGroup, tableRowParams);
                }else if(i>0 && j==2){
                    checkBox.setText("");
                    checkBox.setId(j-1);
                    tableRow.addView(checkBox, tableRowParams);
                }else if(i>0 && j==3){
                    checkBox.setText("");
                    checkBox.setId(j-1);
                    tableRow.addView(checkBox, tableRowParams);
                }else if(i>0 && j==4){
                    point.setInputType(100);
                    point.setId(j-1);
                    tableRow.addView(point, tableRowParams);
                }
                //add textView to tableRow
                //tableRow.addView(textView, tableRowParams);
            }
            //dd tableRow to tableLayout
            tl.addView(tableRow, tableLayoutParams);
        }
    }

    public void addRound(View view){
        return;
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
