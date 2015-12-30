package org.my.adventure.dao_manager.api.entities;

/**
 * Created by Дмитрий on 02.12.2015.
 */
import javax.persistence.*;
@Entity
@Table(name = "item", schema = "public", catalog = "netcracker")
public class Item extends Common{
    private String name;
    private String description;
    private Quest quest;

    @ManyToOne
    @JoinColumn(name="quest_id")
    public Quest getQuest() {
        return quest;
    }

    public void setQuest(Quest quest) {
        this.quest = quest;
    }

    @Basic
    @Column(name = "name", nullable = false, insertable = true, updatable = true, length = 200)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "description", nullable = true, insertable = true, updatable = true, length = 2000)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
