package org.my.adventure.security.ejb;

import org.my.adventure.dao_manager.api.entities.User;

/**
 * Created by al on 16.03.2016.
 */
public class UserWrapper {

    private String name;
    private String surname;
    private String logName;
    private String password;
    private String email;

    public UserWrapper(User user) {
        this.name = user.getName();
        this.surname = user.getSurname();
        this.logName = user.getLogName();
        this.password = user.getPassword();
        this.email = user.getEmail();
    }

    public UserWrapper(String name, String surname, String logName, String password, String email) {
        this.name = name;
        this.surname = surname;
        this.logName = logName;
        this.password = password;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLogName() {
        return logName;
    }

    public void setLogName(String logName) {
        this.logName = logName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
