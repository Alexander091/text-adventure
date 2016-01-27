package org.my.adventure.dao_manager.api.entities;

import java.util.List;
import javax.persistence.*;

/**
 * Created by Дмитрий on 02.12.2015.
 */
@Entity
@Table(name = "user_role", schema = "public", catalog = "netcracker")
public class UserRole extends Common {
    private String name;
    private List<User> users;

    @OneToMany(mappedBy = "userRole")
    public List<User> getUsers() {
        return users;
    }
    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Basic
    @Column(name = "name", nullable = false, insertable = true, updatable = true, length = 200)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
