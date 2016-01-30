package org.my.adventure.dao_manager.api.entities;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Дмитрий on 02.12.2015.
 */
@Entity
@Table(name = "action", schema = "public", catalog = "netcracker")
public class Action extends Common {
    private TypeOfAction type;
    private String condition;
    private Float value;
    private Action parent;
    private Node node;
    private Item item;
    private Resource resource;
    private Stat stat;
    private List<Action> nextActions;
    @OneToMany(mappedBy = "parent")
    public List<Action> getNextActions() {
        return nextActions;
    }

    public void setNextActions(List<Action> nextActions) {
        this.nextActions = nextActions;
    }

    @ManyToOne
    @JoinColumn(name="resource", referencedColumnName = "id")
    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }
    @ManyToOne
    @JoinColumn(name="item", referencedColumnName = "id")
    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
    @ManyToOne
    @JoinColumn(name="stat", referencedColumnName = "id")
    public Stat getStat() {
        return stat;
    }

    public void setStat(Stat stat) {
        this.stat = stat;
    }

    @ManyToOne
    @JoinColumn(name="type_id", referencedColumnName = "id", nullable = false)
    public TypeOfAction getType() {
        return type;
    }

    public void setType(TypeOfAction type) {
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
    public Action getParent() {
        return parent;
    }

    public void setParent(Action actionByParent) {
        this.parent = actionByParent;
    }

    @ManyToOne
    @JoinColumn(name = "Node")
    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }
}
