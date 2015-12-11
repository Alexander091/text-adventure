package entities;

import javax.persistence.*;
import java.util.List;

/**
 * Created by ִלטענטי on 02.12.2015.
 */
@Entity
@Table(name = "Node", schema = "public", catalog = "NetCrackerProject")
public class NodeEntity {
    private int id;
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

    @Id
    @Column(name = "Id", nullable = false, insertable = true, updatable = true)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "Name", nullable = false, insertable = true, updatable = true, length = 2147483647)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "Description", nullable = true, insertable = true, updatable = true, length = 2147483647)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NodeEntity that = (NodeEntity) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "QuestId", referencedColumnName = "Id", nullable = false)
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
