package org.my.adventure.dao_manager.api.entities;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Дмитрий on 02.12.2015.
 */
@Entity
@Table(name = "users", schema = "public", catalog = "netcracker")
public class User extends Common {
    private String name;
    private String surname;
    private String logName;
    private String password;
    private String email;
    private List<Game> games;
    private List<Quest> quests;
    private UserRole userRole;

    @OneToMany(mappedBy = "owner")
    public List<Quest> getQuests(){
        return quests;
    }

    public void setQuests(List<Quest> quests) {
        this.quests = quests;
    }

    @OneToMany(mappedBy = "user")
    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }

    @ManyToOne
    @JoinColumn(name = "user_role", referencedColumnName = "id")
    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
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

}
