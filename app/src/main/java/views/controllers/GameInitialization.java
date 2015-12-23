package views.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

import helpers.ViewHelper;
import models.GameType;
import models.Player;
import models.Settings;


public class GameInitialization extends ViewHelper {
    protected int layoutId = R.layout.game_initialization;

    GameType gameType;



    @Override
    protected int getLayoutId() {
        return R.layout.game_initialization;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (mainMarriage.getSettings() == null) {
            mainMarriage.setSettings(new Settings());
            mainMarriage.getSettings().setType(GameType.Murder);
            mainMarriage.getSettings().setNoOfPlayers(5);
            mainMarriage.getSettings().setBetterPoints(0);
            mainMarriage.getSettings().setMoneyPerPoints(0.10);
        }
        createNoOfOptions();
        initializeValues();
    }

    private void initializeValues() {
        setTextValue(R.id.editMoneyPerPoint, mainMarriage.getSettings().getMoneyPerPoints().toString());
        setTextValue(R.id.editBetter, mainMarriage.getSettings().getBetterPoints());
        GameType gameType = mainMarriage.getSettings().getType();
        switch (gameType) {
            case Kidnap:
                setRadio(R.id.radioKidnap);
                break;
            case Murder:
                setRadio(R.id.radioMurder);
                break;
            case Normal:
                setRadio(R.id.radioNormal);
                break;
        }
        setSpinnerValue(R.id.editNumberOfPlayers, mainMarriage.getSettings().getNoOfPlayers() + "");
    }

    private void createNoOfOptions() {
        Spinner staticSpinner = (Spinner) findViewById(R.id.editNumberOfPlayers);
        // make sure not to show no of players less than current player list
        // Create an ArrayAdapter using the string array and a default spinner
        ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter
                .createFromResource(this, R.array.num_of_players_array,
                        android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        staticAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        staticSpinner.setAdapter(staticAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_game_initialization, menu);


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
        switch (view.getId()) {
            case R.id.radioMurder:
                if (checked)
                    gameType = GameType.Murder;
                break;
            case R.id.radioKidnap:
                if (checked)
                    gameType = GameType.Kidnap;
                break;
            case R.id.radioNormal:
                if (checked)
                    gameType = GameType.Normal;
                break;
        }


    }

    public void startGame(View view) {
        Spinner spinner = (Spinner) findViewById(R.id.editNumberOfPlayers);
        EditText moneyPerPointView = (EditText) findViewById(R.id.editMoneyPerPoint);
        EditText betterView = (EditText) findViewById(R.id.editBetter);
        Intent intent = new Intent(this, GameInitialization.class);
        if (String.valueOf(spinner.getSelectedItem()) == null || moneyPerPointView.getText().toString().equals("")
                || betterView.getText().toString().equals("")) {
        } else {

            Integer numberOfPlayers = Integer.parseInt(String.valueOf(spinner.getSelectedItem()));
            Double moneyPerPoint = Double.parseDouble(moneyPerPointView.getText().toString());
            Integer better = Integer.parseInt(betterView.getText().toString());

            mainMarriage.getSettings().setMoneyPerPoints(moneyPerPoint);
            mainMarriage.getSettings().setBetterPoints(better);
            mainMarriage.getSettings().setNoOfPlayers(numberOfPlayers);
            mainMarriage.getSettings().setType(gameType);

            if(mainMarriage.getPlayers().size() < mainMarriage.getSettings().getNoOfPlayers()){
                int prevId = mainMarriage.getPlayerIdCount();
                for(int i = mainMarriage.getPlayers().size(); i <mainMarriage.getSettings().getNoOfPlayers(); i++ ) {
                    Player pl = new Player();
                    pl.setId(++prevId);
                    pl.setName("P " + prevId);
                    mainMarriage.getPlayers().add(pl);
                }
            }
            else if(mainMarriage.getPlayers().size() > mainMarriage.getSettings().getNoOfPlayers()) {
                mainMarriage.getSettings().setNoOfPlayers(mainMarriage.getPlayers().size());
            }
            intent = new Intent(this, GameDashboard.class);
        }
        startActivity(intent);
    }

}
