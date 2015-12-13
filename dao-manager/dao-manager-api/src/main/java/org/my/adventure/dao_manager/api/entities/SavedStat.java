package org.my.adventure.dao_manager.api.entities;

/**
 * Created by Дмитрий on 11.12.2015.
 */
public interface SavedStat extends Common {
    Stat getStat();
    void setStat(Stat stat);
    float getValue();
    void setValue(float value);
    Game getGameByGameId();
    void setGameByGameId(Game gameByGameId);
}
