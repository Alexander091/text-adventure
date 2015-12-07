package entities;

import javax.persistence.*;

/**
 * Created by ִלטענטי on 02.12.2015.
 */
@Entity
@Table(name = "Transition", schema = "public", catalog = "NetCrackerProject")
public class TransitionEntity {
    private int id;
    private String condition;
    private NodeEntity nodeByToNode;
    private NodeEntity nodeByFromNode;

    @Id
    @Column(name = "Id", nullable = false, insertable = true, updatable = true)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "Condition", nullable = false, insertable = true, updatable = true, length = 2147483647)
    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TransitionEntity that = (TransitionEntity) o;

        if (id != that.id) return false;
        if (condition != null ? !condition.equals(that.condition) : that.condition != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (condition != null ? condition.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "ToNode", referencedColumnName = "Id", nullable = false)
    public NodeEntity getNodeByToNode() {
        return nodeByToNode;
    }

    public void setNodeByToNode(NodeEntity nodeByToNode) {
        this.nodeByToNode = nodeByToNode;
    }

    @ManyToOne
    @JoinColumn(name = "FromNode", referencedColumnName = "Id", nullable = false)
    public NodeEntity getNodeByFromNode() {
        return nodeByFromNode;
    }

    public void setNodeByFromNode(NodeEntity nodeByFromNode) {
        this.nodeByFromNode = nodeByFromNode;
    }
}
