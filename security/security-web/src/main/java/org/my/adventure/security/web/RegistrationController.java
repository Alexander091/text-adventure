package org.my.adventure.security.web;

import org.my.adventure.security.ejb.RegistrationBean;
import org.my.adventure.security.ejb.UserWrapper;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * Created by al on 15.03.2016.
 */

@ManagedBean(name = "registrationBean")
@ViewScoped
public class RegistrationController {

    String email;
    String login;
    String name;
    String surname;
    String password;

    @EJB
    RegistrationBean registrationBean;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String register(){
        registrationBean.addUser(new UserWrapper(name, surname, login, password, email));
        return "/quest-storage/storage.xhtml?faces-redirect=true";
    }
}
