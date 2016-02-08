package org.my.adventure.dao_manager.api.entities;

/**
 * Created by Дмитрий on 02.12.2015.
 */
import javax.persistence.*;
@Entity
@Table(name = "resource", schema = "public", catalog = "netcracker")
public class Resource extends Common{
    private TypeOfResource type;
    private String name;
    private String path;
    private Quest questByQuestId;

    @ManyToOne
    @JoinColumn(name = "type_id", referencedColumnName = "id", nullable = false)
    public TypeOfResource getType() {
        return type;
    }

    public void setType(TypeOfResource type) {
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
    @JoinColumn(name = "quest_id", referencedColumnName = "id", nullable = true)
    public Quest getQuestByQuestId() {
        return questByQuestId;
    }

    public void setQuestByQuestId(Quest questByQuestId) {
        this.questByQuestId = questByQuestId;
    }
}
