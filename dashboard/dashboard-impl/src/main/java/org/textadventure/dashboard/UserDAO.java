package org.textadventure.dashboard;

/**
 * Created by Максим on 10.12.2015.
 */
public class UserDAO {

    public User getUserByID(int id){
        User user = new User(id, getLogin(), getUserName(), getPasswordHash(),isBanned());
        return user;
    }

    private String getUserName() {
        return "Maksim Zheltoukhov";
    }

    public void setLogin(String login){}
    public String getLogin(){return "user1";}

    public void setPasswordHash(String passwordHash){}
    public String getPasswordHash(){return "abc";}


    public boolean isBanned(){return false;}

}
