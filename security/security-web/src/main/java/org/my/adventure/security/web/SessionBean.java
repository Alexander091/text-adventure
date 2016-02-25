package org.my.adventure.security.web;

import org.my.adventure.security.api.SecurityConstants;

import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.Map;

/**
 * Created by al on 19.02.2016.
 */
@ManagedBean(name="sessionBean")
@ViewScoped
public class SessionBean implements Serializable{

    private ExternalContext externalContext = null;
    private Map<String, Object> sessionMap = null;

    public SessionBean() {
        externalContext = FacesContext.getCurrentInstance().getExternalContext();
        sessionMap = externalContext.getSessionMap();
    }

    private String username = "admin";

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
