package models;

import java.util.ArrayList;

/**
 * Created by saryal on 12/22/15.
 */
public class Round {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrder() {
        return Order;
    }

    public void setOrder(int order) {
        Order = order;
    }

    public ArrayList<RoundPlayer> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<RoundPlayer> players) {
        this.players = players;
    }

    private int Order;
    private ArrayList<RoundPlayer> players;
}
