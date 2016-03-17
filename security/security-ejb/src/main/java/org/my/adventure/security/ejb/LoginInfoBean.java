package org.my.adventure.security.ejb;

import org.my.adventure.dao_manager.api.dao.UserDAO;
import org.my.adventure.dao_manager.api.entities.User;
import org.my.adventure.security.api.SecurityConstants;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by al on 29.02.2016.
 */
@Stateless
public class LoginInfoBean {
//TODO: make it stateful, store user info

    @EJB
    UserDAO userDAO;

    public boolean isAdmin(){
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String username = request.getRemoteUser();
        return request.isUserInRole(SecurityConstants.ADMIN_ROLE);
    }

    public boolean isUser(){
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String username = request.getRemoteUser();
        return username != null;
    }

    public User getUser(){
        return userDAO.getUserByName(jaasUser());
    }

    public Long getUserId(){
        User user = userDAO.getUserByName(jaasUser());
        Long result = null;
        if (user != null){
            result = user.getId();
        }
        return result;
    }

    public String jaasUser(){
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        return request.getRemoteUser();
    }
}
