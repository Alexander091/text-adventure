package org.my.adventure.dao_manager.api.entities;
import javax.persistence.*;

/**
 * Created by Дмитрий on 02.12.2015.
 */
@Entity
@Table(name = "resource", schema = "public", catalog = "netcracker")
public class Resource extends Common{
    private TypeOfResource type;
    private String name;
    private byte[] data;
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


    @Column(name = "data", nullable = false, insertable = true, updatable = true)
    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
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
