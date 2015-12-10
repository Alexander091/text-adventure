package entities;

import javax.persistence.*;
import java.util.List;

/**
 * Created by ִלטענטי on 02.12.2015.
 */
@Entity
@Table(name = "UserRole", schema = "public", catalog = "NetCrackerProject")
public class UserRoleEntity {
    private int id;
    private String name;
   // private List<UserEntity> users;

    //@OneToMany(mappedBy = "userRole")
    //public List<UserEntity> getUsers() {
        //return users;
    //}

   // public void setUsers(List<UserEntity> users) {
      //  this.users = users;
    //}

    @Id
    @Column(name = "Id", nullable = false, insertable = true, updatable = true)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "Name", nullable = false, insertable = true, updatable = true, length = 2147483647)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserRoleEntity that = (UserRoleEntity) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
