package org.my.adventure.dao_manager.impl.entities;

import javax.persistence.*;

/**
 * Created by Дмитрий on 02.12.2015.
 */
@Entity
@Table(name = "stat", schema = "public", catalog = "netcracker")
public class StatEntity extends CommonEntity{
    private String name;
    private Float value;
    private String description;
    private QuestEntity questByQuestId;

    @Basic
    @Column(name = "name", nullable = false, insertable = true, updatable = true, length = 200)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "value", nullable = true, insertable = true, updatable = true, precision = 8)
    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
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
    @JoinColumn(name = "quest_id", referencedColumnName = "Id", nullable = false)
    public QuestEntity getQuestByQuestId() {
        return questByQuestId;
    }

    public void setQuestByQuestId(QuestEntity questByQuestId) {
        this.questByQuestId = questByQuestId;
    }
}
