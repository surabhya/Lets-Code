package surabhya.aryal.marriage;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;


public class GameInitialization extends ActionBarActivity {


    int numberOfPlayers;
    String gameType;
    double moneyPerPoint;
    double better;

    DatabaseHelper dbHelper = new DatabaseHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_initialization);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_game_initialization, menu);

        Spinner staticSpinner = (Spinner) findViewById(R.id.editNumberOfPlayers);

        // Create an ArrayAdapter using the string array and a default spinner
        ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter
                .createFromResource(this, R.array.num_of_players_array,
                        android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        staticAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        staticSpinner.setAdapter(staticAdapter);
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

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radioMurder:
                if (checked)
                    gameType = "murder";
                    break;
            case R.id.radioKidnap:
                if (checked)
                    gameType = "kidnap";
                    break;
            case R.id.radioNormal:
                if (checked)
                    gameType = "normal";
                    break;
        }


    }

    public void startGame(View view){
        Spinner spinner = (Spinner) findViewById(R.id.editNumberOfPlayers);
        EditText moneyPerPointView  = (EditText) findViewById(R.id.editMoneyPerPoint);
        EditText betterView  = (EditText) findViewById(R.id.editBetter);
        Intent intent = new Intent(this, GameInitialization.class);

        if(String.valueOf(spinner.getSelectedItem())==null || moneyPerPointView.getText().toString().equals("")
                || betterView.getText().toString().equals("")){
        }else{
            numberOfPlayers = Integer.parseInt(String.valueOf(spinner.getSelectedItem()));
            moneyPerPoint = Double.parseDouble(moneyPerPointView.getText().toString());
            better = Double.parseDouble(betterView.getText().toString());
            dbHelper.addGameInfo(numberOfPlayers, gameType, moneyPerPoint, better);
            intent = new Intent(this, GameStatus.class);
        }
        startActivity(intent);
    }

}
