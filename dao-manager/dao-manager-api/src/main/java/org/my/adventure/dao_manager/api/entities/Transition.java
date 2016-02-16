package org.my.adventure.dao_manager.api.entities;

/**
 * Created by Дмитрий on 02.12.2015.
 */
import javax.persistence.*;
@Entity
@Table(name = "transition", schema = "public", catalog = "netcracker")
public class Transition extends Common{
    private String name;
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

    @Basic
    @Column(name = "name", nullable = false, insertable = true, updatable = true, length = 200)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "to_node", referencedColumnName = "id", nullable = false)
    public Node getNodeByToNode() {
        return nodeByToNode;
    }

    public void setNodeByToNode(Node nodeByToNode) {
        this.nodeByToNode = nodeByToNode;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "from_node", referencedColumnName = "id", nullable = false)
    public Node getNodeByFromNode() {
        return nodeByFromNode;
    }

    public void setNodeByFromNode(Node nodeByFromNode) {
        this.nodeByFromNode = nodeByFromNode;
    }
}
