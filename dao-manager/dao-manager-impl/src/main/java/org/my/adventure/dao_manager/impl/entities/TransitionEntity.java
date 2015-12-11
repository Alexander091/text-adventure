package org.my.adventure.dao_manager.impl.entities;

import javax.persistence.*;

/**
 * Created by Дмитрий on 02.12.2015.
 */
@Entity
@Table(name = "transition", schema = "public", catalog = "netcracker")
public class TransitionEntity extends CommonEntity {
    private String condition;
    private NodeEntity nodeByToNode;
    private NodeEntity nodeByFromNode;

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
