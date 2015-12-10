package org.textadventure.dashboard;

/**
 * Created by Максим on 08.12.2015.
 */
public class User {

    private int id;
    private String login;
    private String passwordHash;
    private boolean banned;

    public User(int id,String login, String passwordHash, boolean banned){
        this.id = id;
        this.login = login;
        this.passwordHash = passwordHash;
        this.banned = banned;
    }


    public void setLogin(String login){}
    public String getLogin(){return login;}

    public void setPasswordHash(String passwordHash){}
    public String getPasswordHash(){return passwordHash;}

    public void setBanned(){banned=true;}
    public boolean isBanned(){return banned;}

}



/*@Bean
public class UserController {
    private UserDAO userDao;
    public User getUser(){
        User user = new User();
        return user;
    }
}

public class UserDAO{
    public User getUserById(long id){

    }
}*/
