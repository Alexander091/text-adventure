package entities;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SavedStatEntity that = (SavedStatEntity) o;

        if (id != that.id) return false;
        if (Float.compare(that.value, value) != 0) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = 0;
        result = 31 * result + (value != +0.0f ? Float.floatToIntBits(value) : 0);
        return result;
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
