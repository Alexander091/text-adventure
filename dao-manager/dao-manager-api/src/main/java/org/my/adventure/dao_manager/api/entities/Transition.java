package org.my.adventure.dao_manager.api.entities;

/**
 * Created by Дмитрий on 02.12.2015.
 */
import javax.persistence.*;
@Entity
@Table(name = "transition", schema = "public", catalog = "netcracker")
public class Transition extends Common{
    private String condition;
    private Node nodeByToNode;
    private Node nodeByFromNode;

    @Basic
    @Column(name = "condition", nullable = false, insertable = true, updatable = true, length = 1000)
    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    @ManyToOne
    @JoinColumn(name = "to_node", referencedColumnName = "id", nullable = false)
    public Node getNodeByToNode() {
        return nodeByToNode;
    }

    public void setNodeByToNode(Node nodeByToNode) {
        this.nodeByToNode = nodeByToNode;
    }

    @ManyToOne
    @JoinColumn(name = "from_node", referencedColumnName = "id", nullable = false)
    public Node getNodeByFromNode() {
        return nodeByFromNode;
    }

    public void setNodeByFromNode(Node nodeByFromNode) {
        this.nodeByFromNode = nodeByFromNode;
    }
}
