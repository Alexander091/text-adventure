package org.my.adventure.dao_manager.impl.entities;

import javax.persistence.*;

/**
 * Created by Дмитрий on 11.12.2015.
 */
@Entity
@Table(name = "type_of_action", schema = "public", catalog = "netcracker")
public class TypeOfActionEntity extends CommonEntity {
    private String name;

    @Basic
    @Column(name = "name", nullable = false, insertable = true, updatable = true, length = 100)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
