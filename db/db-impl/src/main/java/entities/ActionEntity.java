package entities;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ActionEntity that = (ActionEntity) o;

        if (id != that.id) return false;
        if (!type.equals(that.type)) return false;
        if (!condition.equals(that.condition)) return false;
        if (value != null ? !value.equals(that.value) : that.value != null) return false;
        if (parent != null ? !parent.equals(that.parent) : that.parent != null) return false;
        if (node != null ? !node.equals(that.node) : that.node != null) return false;
        if (item != null ? !item.equals(that.item) : that.item != null) return false;
        if (resource != null ? !resource.equals(that.resource) : that.resource != null) return false;
        return !(stat != null ? !stat.equals(that.stat) : that.stat != null);

    }

    @Override
    public int hashCode() {
        int result = 0;
        result = 31 * result + type.hashCode();
        result = 31 * result + condition.hashCode();
        result = 31 * result + (value != null ? value.hashCode() : 0);
        result = 31 * result + (parent != null ? parent.hashCode() : 0);
        result = 31 * result + (node != null ? node.hashCode() : 0);
        result = 31 * result + (item != null ? item.hashCode() : 0);
        result = 31 * result + (resource != null ? resource.hashCode() : 0);
        result = 31 * result + (stat != null ? stat.hashCode() : 0);
        return result;
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
