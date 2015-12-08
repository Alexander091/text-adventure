package org.textadventure.dashboard;

/**
 * Created by Максим on 08.12.2015.
 */
public class User {

    private int id;
    private String login;
    private String passwordHash;
    private boolean banned;


    public void setLogin(String login){}
    public String getLogin(){return login;}

    public void setPasswordHash(String passwordHash){}
    public String getPasswordHash(){return passwordHash;}

    public void setBanned(){banned=true;}
    public boolean isBanned(){return banned;}

}
