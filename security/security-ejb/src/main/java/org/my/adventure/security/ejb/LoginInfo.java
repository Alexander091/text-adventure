package org.my.adventure.security.ejb;

import org.my.adventure.security.api.SecurityConstants;

import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by al on 29.02.2016.
 */
@Stateless
public class LoginInfo {

    public boolean isAdmin(){
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String username = request.getRemoteUser();
        return request.isUserInRole(SecurityConstants.ADMIN_ROLE);
    }

    public String jaasUser(){
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        return request.getRemoteUser();
    }
}
