package entities;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Дмитрий on 02.12.2015.
 */
@Entity
@Table(name = "game", schema = "public", catalog = "netcracker")
public class GameEntity extends CommonEntity{
    private List<SavedItemEntity> savedItems;
    private List<SavedStatEntity> savedStats;
    private UserEntity user;
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
    @JoinColumn(name = "user_id")
    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    @ManyToOne
    @JoinColumn(name = "quest_id")
    public QuestEntity getQuest() {
        return quest;
    }

    public void setQuest(QuestEntity quest) {
        this.quest = quest;
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
        return (int)((long)id);
    }
}
