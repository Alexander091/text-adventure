package org.my.adventure.dao_manager.api.entities;

import java.util.List;
import javax.persistence.*;

/**
 * Created by Дмитрий on 02.12.2015.
 */
@Entity
@Table(name = "node", schema = "public", catalog = "netcracker")
public class Node extends Common{
    private String name;
    private String description;
    private Quest questByQuestId;
    private List<Action> Actions;
    private List<Transition> transitions;

    @OneToMany(mappedBy = "nodeByFromNode")
    public List<Transition> getTransitions() {
        return transitions;
    }

    public void setTransitions(List<Transition> transitions) {
        this.transitions = transitions;
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

    @ManyToOne
    @JoinColumn(name = "quest_id", referencedColumnName = "id", nullable = false)
    public Quest getQuestByQuestId() {
        return questByQuestId;
    }

    public void setQuestByQuestId(Quest questByQuestId) {
        this.questByQuestId = questByQuestId;
    }

    @OneToMany(mappedBy = "node")
    public List<Action> getActions() {
        return Actions;
    }

    public void setActions(List<Action> actions) {
        Actions = actions;
    }
}
