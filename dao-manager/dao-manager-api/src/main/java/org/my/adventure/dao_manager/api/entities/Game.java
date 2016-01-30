package org.my.adventure.dao_manager.api.entities;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Дмитрий on 02.12.2015.
 */
@Entity
@Table(name = "game", schema = "public", catalog = "netcracker")
public class Game extends Common{
    private List<SavedItem> savedItems;
    private List<SavedStat> savedStats;
    private User user;
    private Quest quest;

    @OneToMany(mappedBy = "gameByGameId")
    public List<SavedItem> getSavedItems() {
        return savedItems;
    }

    public void setSavedItems(List<SavedItem> savedItems) {
        this.savedItems = savedItems;
    }

    @OneToMany(mappedBy = "gameByGameId")
    public List<SavedStat> getSavedStats() {
        return savedStats;
    }

    public void setSavedStats(List<SavedStat> savedStats) {
        this.savedStats = savedStats;
    }

    @ManyToOne
    @JoinColumn(name = "user_id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne
    @JoinColumn(name = "quest_id")
    public Quest getQuest() {
        return quest;
    }

    public void setQuest(Quest quest) {
        this.quest = quest;
    }

}
