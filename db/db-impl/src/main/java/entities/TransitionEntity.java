package entities;

import javax.persistence.*;

/**
 * Created by ִלטענטי on 02.12.2015.
 */
@Entity
@Table(name = "transition", schema = "public", catalog = "netcracker")
public class TransitionEntity {
    private Long id;
    private String condition;
    private NodeEntity nodeByToNode;
    private NodeEntity nodeByFromNode;

    @Id
    @SequenceGenerator(name="transition_sequence",sequenceName="entity_id_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="transition_sequence")
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "condition", nullable = false, insertable = true, updatable = true, length = 1000)
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
        int result = 0;
        result = 31 * result + (condition != null ? condition.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "to_node", referencedColumnName = "id", nullable = false)
    public NodeEntity getNodeByToNode() {
        return nodeByToNode;
    }

    public void setNodeByToNode(NodeEntity nodeByToNode) {
        this.nodeByToNode = nodeByToNode;
    }

    @ManyToOne
    @JoinColumn(name = "from_node", referencedColumnName = "id", nullable = false)
    public NodeEntity getNodeByFromNode() {
        return nodeByFromNode;
    }

    public void setNodeByFromNode(NodeEntity nodeByFromNode) {
        this.nodeByFromNode = nodeByFromNode;
    }
}
