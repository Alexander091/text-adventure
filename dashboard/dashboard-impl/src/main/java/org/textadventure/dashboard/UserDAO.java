package org.textadventure.dashboard;

/**
 * Created by ������ on 10.12.2015.
 */
public class UserDAO {

    public User getUserByID(int id){
        User user = new User(id, getLogin(), getPasswordHash(),isBanned());
        return user;
    }

    public void setLogin(String login){}
    public String getLogin(){return "user1";}

    public void setPasswordHash(String passwordHash){}
    public String getPasswordHash(){return "abc";}


    public boolean isBanned(){return false;}

}