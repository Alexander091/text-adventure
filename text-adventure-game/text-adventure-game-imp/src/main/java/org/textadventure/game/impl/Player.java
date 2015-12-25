package org.textadventure.game.impl;

import java.util.ArrayList;

/**
 * Created by Alexander on 11.12.2015.
 */
public class Player {
    ArrayList<GameItem> Inventory;

    public ArrayList<GameItem> getInventory() {
        return Inventory;
    }

    public void setInventory(ArrayList<GameItem> inventory) {
        Inventory = inventory;
    }
}
