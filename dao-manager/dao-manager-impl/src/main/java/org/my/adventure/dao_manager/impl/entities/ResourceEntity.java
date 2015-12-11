package org.my.adventure.dao_manager.impl.entities;

import javax.persistence.*;

/**
 * Created by Дмитрий on 02.12.2015.
 */
@Entity
@Table(name = "resource", schema = "public", catalog = "netcracker")
public class ResourceEntity extends CommonEntity{
    private TypeOfResourceEntity type;
    private String name;
    private String path;
    private QuestEntity questByQuestId;

    @ManyToOne
    @JoinColumn(name = "type_id", referencedColumnName = "id", nullable = false)
    public TypeOfResourceEntity getType() {
        return type;
    }

    public void setType(TypeOfResourceEntity type) {
        this.type = type;
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
    @Column(name = "path", nullable = false, insertable = true, updatable = true, length = 1000)
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @ManyToOne
    @JoinColumn(name = "quest_id", referencedColumnName = "id", nullable = false)
    public QuestEntity getQuestByQuestId() {
        return questByQuestId;
    }

    public void setQuestByQuestId(QuestEntity questByQuestId) {
        this.questByQuestId = questByQuestId;
    }
}
