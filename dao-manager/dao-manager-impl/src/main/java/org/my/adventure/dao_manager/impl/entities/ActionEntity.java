package org.my.adventure.dao_manager.impl.entities;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Дмитрий on 02.12.2015.
 */
@Entity
@Table(name = "action", schema = "public", catalog = "netcracker")
public class ActionEntity extends CommonEntity {
    private TypeOfActionEntity type;
    private String condition;
    private Float value;
    private ActionEntity parent;
    private NodeEntity node;
    private ItemEntity item;
    private ResourceEntity resource;
    private StatEntity stat;
    private List<ActionEntity> nextActions;
    @OneToMany(mappedBy = "parent")
    public List<ActionEntity> getNextActions() {
        return nextActions;
    }

    public void setNextActions(List<ActionEntity> nextActions) {
        this.nextActions = nextActions;
    }

    @ManyToOne
    @JoinColumn(name="resource", referencedColumnName = "id")
    public ResourceEntity getResource() {
        return resource;
    }

    public void setResource(ResourceEntity resource) {
        this.resource = resource;
    }
    @ManyToOne
    @JoinColumn(name="item", referencedColumnName = "id")
    public ItemEntity getItem() {
        return item;
    }

    public void setItem(ItemEntity item) {
        this.item = item;
    }
    @ManyToOne
    @JoinColumn(name="stat", referencedColumnName = "id")
    public StatEntity getStat() {
        return stat;
    }

    public void setStat(StatEntity stat) {
        this.stat = stat;
    }

    @ManyToOne
    @JoinColumn(name="type_id", referencedColumnName = "id", nullable = false)
    public TypeOfActionEntity getType() {
        return type;
    }

    public void setType(TypeOfActionEntity type) {
        this.type = type;
    }

    @Basic
    @Column(name = "condition", nullable = false, insertable = true, updatable = true, length = 200)
    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    @Basic
    @Column(name = "value", nullable = true, insertable = true, updatable = true, precision = 8)
    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }
    @ManyToOne
    @JoinColumn(name = "Parent", referencedColumnName = "Id")
    public ActionEntity getParent() {
        return parent;
    }

    public void setParent(ActionEntity actionByParent) {
        this.parent = actionByParent;
    }

    @ManyToOne
    @JoinColumn(name = "Node")
    public NodeEntity getNode() {
        return node;
    }

    public void setNode(NodeEntity node) {
        this.node = node;
    }
}
