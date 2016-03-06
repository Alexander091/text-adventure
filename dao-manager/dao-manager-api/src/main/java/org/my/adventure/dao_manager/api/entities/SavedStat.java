package org.my.adventure.dao_manager.api.entities;

/**
 * Created by Дмитрий on 02.12.2015.
 */
import javax.persistence.*;
@Entity
@Table(name = "saved_stat", schema = "public", catalog = "netcracker")
public class SavedStat extends Common{
    private float value;
    private Game gameByGameId;
    private Stat stat;

    @OneToOne
    @JoinColumn(name = "stat_id", referencedColumnName = "id")
    public Stat getStat() {
        return stat;
    }

    public void setStat(Stat stat) {
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
    public Game getGameByGameId() {
        return gameByGameId;
    }

    public void setGameByGameId(Game gameByGameId) {
        this.gameByGameId = gameByGameId;
    }
}
