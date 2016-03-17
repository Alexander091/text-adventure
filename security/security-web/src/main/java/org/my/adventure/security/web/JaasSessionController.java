package org.my.adventure.security.web;

import org.my.adventure.security.ejb.LoginInfoBean;

import javax.ejb.EJB;
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
public class JaasSessionController implements Serializable{

    @EJB
    LoginInfoBean loginInfo;

    public boolean isAdmin(){
        return loginInfo.isAdmin();
    }

    public String jaasUser(){
        return loginInfo.jaasUser();
    }

    public String jaasLogout(){
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        request.getSession().invalidate();
        return "/index.xhtml?faces-redirect=true";
    }
}
