package org.my.adventure.dao_manager.api.entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Дмитрий on 11.12.2015.
 */
@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Common implements Serializable {
    protected Long id;

    @Id
    @SequenceGenerator(name="sequence",sequenceName="entity_id_seq")
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="sequence")
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
