package org.my.adventure.security.web;

import org.my.adventure.security.api.SecurityConstants;

import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

/**
 * Created by al on 25.02.2016.
 */
@ManagedBean(name="loginController")
@SessionScoped
public class LoginController implements Serializable{
    public boolean isAdmin(){
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String username = request.getRemoteUser();
        return request.isUserInRole(SecurityConstants.ADMIN_ROLE);
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
