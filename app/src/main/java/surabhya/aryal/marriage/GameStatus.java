package surabhya.aryal.marriage;

import android.app.Activity;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.HorizontalScrollView;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;


public class GameStatus extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_status);

        String[] column  = { "Player 1", "Player 2", "Player 3"};
        ArrayList<Integer[]> row = new ArrayList<Integer[]>();
        row.add(new Integer[]{2,6,7});
        row.add(new Integer[]{3,6,7});
        row.add(new Integer[]{5,6,7});

        int rl=row.size();
        int cl=column.length;

        ScrollView sv = new ScrollView(this);
        TableLayout tableLayout = createTableLayout(row, column,rl, cl);
        HorizontalScrollView hsv = new HorizontalScrollView(this);

        hsv.addView(tableLayout);
        sv.addView(hsv);
        setContentView(sv);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_game_status, menu);
        return true;
    }

    private TableLayout createTableLayout(ArrayList<Integer[]> rv, String [] cv,int rowCount, int columnCount) {

        //Create a tableLayout and its params
        TableLayout.LayoutParams tableLayoutParams = new TableLayout.LayoutParams();
        TableLayout tableLayout = new TableLayout(this);


        //create tableRow params
        TableRow.LayoutParams tableRowParams = new TableRow.LayoutParams();
        tableRowParams.setMargins(1, 1, 1, 1);
        tableRowParams.weight = 1;

        for (int i = 0; i <= rowCount+1; i++) {
            TableRow tableRow = new TableRow(this);
            // create tableRow
            for (int j= 0; j <= columnCount; j++) {
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
                    textView.setText(cv[j-1]);
                } else if(i!=0 && j>0){
                    textView.setText(rv.get(i-1)[j-1]+"");
                }
                //add textView to tableRow
                tableRow.addView(textView, tableRowParams);
            }
            //dd tableRow to tableLayout
            tableLayout.addView(tableRow, tableLayoutParams);
        }

        return tableLayout;
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
