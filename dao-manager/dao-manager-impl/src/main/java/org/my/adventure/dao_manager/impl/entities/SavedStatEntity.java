package org.my.adventure.dao_manager.impl.entities;

import javax.persistence.*;

/**
 * Created by Дмитрий on 02.12.2015.
 */
@Entity
@Table(name = "saved_stat", schema = "public", catalog = "netcracker")
public class SavedStatEntity extends CommonEntity{
    private float value;
    private GameEntity gameByGameId;
    private StatEntity stat;

    @OneToOne
    @JoinColumn(name = "stat_id", referencedColumnName = "id")
    public StatEntity getStat() {
        return stat;
    }

    public void setStat(StatEntity stat) {
        this.stat = stat;
    }

    @Basic
    @Column(name = "value", nullable = false, insertable = true, updatable = true, precision = 8)
    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    @ManyToOne
    @JoinColumn(name = "game_id", referencedColumnName = "id", nullable = false)
    public GameEntity getGameByGameId() {
        return gameByGameId;
    }

    public void setGameByGameId(GameEntity gameByGameId) {
        this.gameByGameId = gameByGameId;
    }
}
