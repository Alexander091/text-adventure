package org.my.adventure.dao_manager.api.entities;

/**
 * Created by Дмитрий on 11.12.2015.
 */
import javax.persistence.*;
@Entity
@Table(name = "type_of_action", schema = "public", catalog = "netcracker")
public class TypeOfAction extends Common{
    private String name;
    private TypeOfResource typeOfResource;

    @Basic
    @Column(name = "name", nullable = false, insertable = true, updatable = true, length = 100)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_of_resource_id", referencedColumnName = "id", nullable = false)
    public TypeOfResource getTypeOfResource() {
        return typeOfResource;
    }

    public void setTypeOfResource(TypeOfResource typeOfResource) {
        this.typeOfResource = typeOfResource;
    }
}
