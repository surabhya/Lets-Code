package views.controllers;

import android.app.Application;

import java.util.ArrayList;

import models.Marriage;
import models.Player;
import models.Round;

/**
 * Created by saryal on 12/22/15.
 */
public class MarriageApplication extends Application {
    Marriage mainMarriage;

    @Override
    public void onCreate() {
        super.onCreate();

        // Initialize the singletons so their instances
        // are bound to the application process.
        mainMarriage = new Marriage();
        mainMarriage.setPlayers(new ArrayList<Player>());
        mainMarriage.setRounds(new ArrayList<Round>());
        mainMarriage.setPlayerIdCount(0);
    }

    public Marriage getMainMarriage() {
        return mainMarriage;
    }
}
