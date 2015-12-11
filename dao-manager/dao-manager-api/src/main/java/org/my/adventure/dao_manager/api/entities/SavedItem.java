package org.my.adventure.dao_manager.api.entities;

/**
 * Created by Дмитрий on 11.12.2015.
 */
public interface SavedItem extends Common {
    Game getGameByGameId();
    void setGameByGameId(Game gameByGameId);
    Item getItemByItemId();
    void setItemByItemId(Item itemByItemId);
}
