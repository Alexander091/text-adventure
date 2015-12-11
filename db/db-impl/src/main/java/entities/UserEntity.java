package entities;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Дмитрий on 02.12.2015.
 */
@Entity
@Table(name = "user", schema = "public", catalog = "netcracker")
public class UserEntity extends CommonEntity{
    private String name;
    private String surname;
    private String logName;
    private String password;
    private String email;
    private List<GameEntity> games;
    private UserRoleEntity userRole;

    @OneToMany(mappedBy = "user")
    public List<GameEntity> getGames() {
        return games;
    }

    public void setGames(List<GameEntity> games) {
        this.games = games;
    }

    @ManyToOne
    @JoinColumn(name = "user_role", referencedColumnName = "id")
    public UserRoleEntity getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRoleEntity userRole) {
        this.userRole = userRole;
    }

    @Basic
    @Column(name = "name", nullable = true, insertable = true, updatable = true, length = 100)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "surname", nullable = true, insertable = true, updatable = true, length = 200)
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Basic
    @Column(name = "log_name", nullable = false, insertable = true, updatable = true, length = 100)
    public String getLogName() {
        return logName;
    }

    public void setLogName(String logName) {
        this.logName = logName;
    }

    @Basic
    @Column(name = "password", nullable = false, insertable = true, updatable = true, length = 200)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "email", nullable = false, insertable = true, updatable = true, length = 200)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEntity that = (UserEntity) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (surname != null ? !surname.equals(that.surname) : that.surname != null) return false;
        if (logName != null ? !logName.equals(that.logName) : that.logName != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (logName != null ? logName.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }
}
