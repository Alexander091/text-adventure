package entities;

import javax.persistence.*;
import java.util.List;

/**
 * Created by ִלטענטי on 02.12.2015.
 */
@Entity
@Table(name = "Game", schema = "public", catalog = "NetCrackerProject")
public class GameEntity {
    private int id;
    private List<SavedItemEntity> savedItems;
    private List<SavedStatEntity> savedStats;
    private UserEntity player;
    private QuestEntity quest;

    @OneToMany(mappedBy = "gameByGameId")
    public List<SavedItemEntity> getSavedItems() {
        return savedItems;
    }

    public void setSavedItems(List<SavedItemEntity> savedItems) {
        this.savedItems = savedItems;
    }

    @OneToMany(mappedBy = "gameByGameId")
    public List<SavedStatEntity> getSavedStats() {
        return savedStats;
    }

    public void setSavedStats(List<SavedStatEntity> savedStats) {
        this.savedStats = savedStats;
    }

    @ManyToOne
    @JoinColumn(name = "PlayerId")
    public UserEntity getPlayer() {
        return player;
    }

    public void setPlayer(UserEntity player) {
        this.player = player;
    }

    @ManyToOne
    @JoinColumn(name = "QuestId")
    public QuestEntity getQuest() {
        return quest;
    }

    public void setQuest(QuestEntity quest) {
        this.quest = quest;
    }

    @Id
    @Column(name = "Id", nullable = false, insertable = true, updatable = true)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GameEntity that = (GameEntity) o;

        if (id != that.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
