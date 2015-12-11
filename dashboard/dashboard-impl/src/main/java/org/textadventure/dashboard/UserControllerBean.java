package org.textadventure.dashboard;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.text.SimpleDateFormat;


@ManagedBean(name="userControllerBean")
//@RequestScoped
@SessionScoped
public class UserControllerBean implements Serializable{
    private static final long serialVersionUID = 1L;

    private UserDAO userDAO;
    //private User user;

    public UserControllerBean(){
        userDAO = new UserDAO();

}

    public User getUserByID(int id)
    {
        return userDAO.getUserByID(id);
    }
}
