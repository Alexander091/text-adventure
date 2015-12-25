package org.textadventure.game.impl.Actions;

import org.textadventure.questeditor.api.Action;
import org.textadventure.questeditor.api.Item;

/**
 * Created by Alexander on 25.12.2015.
 */
public class AddObjectToInventoryAction implements Action {
    Item item;

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    @Override
    public void doAction() {

    }
}
