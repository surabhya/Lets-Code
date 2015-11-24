package surabhya.aryal.marriage;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

public class GameStatus extends Activity {

    TableLayout tl;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_status);
        tl = (TableLayout) findViewById(R.id.maintable);
        addData();
    }

    public void addData(){

        String[] column  = { "Player 1", "Player 2", "Player 3", "Player 5"};
        ArrayList<Integer[]> row = new ArrayList<Integer[]>();
        row.add(new Integer[]{2,6,7,3});
        row.add(new Integer[]{3,6,7,1});
        row.add(new Integer[]{5, 6, 7,5});

        int rowCount= row.size();
        int columnCount=column.length;

        TableLayout.LayoutParams tableLayoutParams = new TableLayout.LayoutParams();
        TableRow.LayoutParams tableRowParams = new TableRow.LayoutParams();
        tableRowParams.setMargins(1, 1, 1, 1);
        tableRowParams.weight = 1;

        for (int i = 0; i <= rowCount+1; i++) {
            TableRow tableRow = new TableRow(this);
            // create tableRow
            for (int j = 0; j <= columnCount; j++) {
                //create textView
                TextView textView = new TextView(this);
                textView.setGravity(Gravity.CENTER);
                if(i==0 && j==0){
                    textView.setText(" ");
                }else if(i==rowCount+1 && j==0){
                    textView.setText("Total");
                }else if(i==rowCount+1 && j!=0){
                    textView.setText("T");
                }else if(i==0 && j!=0){
                    textView.setText(column[j-1]);
                } else if(i!=0 && j>0){
                    textView.setText(row.get(i-1)[j-1]+"");
                }
                //add textView to tableRow
                tableRow.addView(textView, tableRowParams);
            }
            //dd tableRow to tableLayout
            tl.addView(tableRow, tableLayoutParams);
        }
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
