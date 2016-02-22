package org.my.adventure.security;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.Map;

/**
 * Created by al on 19.02.2016.
 */
@ManagedBean(name="sessionBean")
@SessionScoped
public class SessionBean implements Serializable{

    ExternalContext externalContext = null;
    Map<String, Object> sessionMap = null;

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

    public boolean isAdmin(){
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String username = request.getRemoteUser();
        return request.isUserInRole("admin");
    }

    public String getSessionUsername(){
        String res = "";
        for (Map.Entry<String, Object> entry : sessionMap.entrySet()) {
            res += entry.getKey() + " " + entry.getValue() + "<br/>";
        }
        return res;
    }

    public String jaasUser(){
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        return request.getRemoteUser();
    }

    public String jaasLogout(){
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        request.getSession().invalidate();
        return "/index.xhtml?faces-redirect=true";
    }
}
