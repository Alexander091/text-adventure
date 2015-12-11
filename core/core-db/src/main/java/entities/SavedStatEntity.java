package entities;

import javax.persistence.*;

/**
 * Created by ִלטענטי on 02.12.2015.
 */
@Entity
@Table(name = "SavedStat", schema = "public", catalog = "NetCrackerProject")
public class SavedStatEntity {
    private int id;
    private float value;
    private GameEntity gameByGameId;
    private StatEntity stat;

    @OneToOne
    @JoinColumn(name = "StatId", referencedColumnName = "Id")
    public StatEntity getStat() {
        return stat;
    }

    public void setStat(StatEntity stat) {
        this.stat = stat;
    }

    @Id
    @Column(name = "Id", nullable = false, insertable = true, updatable = true)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "Value", nullable = false, insertable = true, updatable = true, precision = 8)
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
        int result = id;
        result = 31 * result + (value != +0.0f ? Float.floatToIntBits(value) : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "GameId", referencedColumnName = "Id", nullable = false)
    public GameEntity getGameByGameId() {
        return gameByGameId;
    }

    public void setGameByGameId(GameEntity gameByGameId) {
        this.gameByGameId = gameByGameId;
    }
}
