package org.my.adventure.dao_manager.impl.entities;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Дмитрий on 02.12.2015.
 */
@Entity
@Table(name = "node", schema = "public", catalog = "netcracker")
public class NodeEntity extends CommonEntity{
    private String name;
    private String description;
    private QuestEntity questByQuestId;
    private List<ActionEntity> Actions;
    private List<TransitionEntity> transitions;

    @OneToMany(mappedBy = "nodeByFromNode")
    public List<TransitionEntity> getTransitions() {
        return transitions;
    }

    public void setTransitions(List<TransitionEntity> transitions) {
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
    public QuestEntity getQuestByQuestId() {
        return questByQuestId;
    }

    public void setQuestByQuestId(QuestEntity questByQuestId) {
        this.questByQuestId = questByQuestId;
    }

    @OneToMany(mappedBy = "node")
    public List<ActionEntity> getActions() {
        return Actions;
    }

    public void setActions(List<ActionEntity> actions) {
        Actions = actions;
    }
}
