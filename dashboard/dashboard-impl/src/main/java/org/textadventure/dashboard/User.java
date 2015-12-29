package org.textadventure.dashboard;

import java.lang.ref.SoftReference;

/**
 * Created by Максим on 08.12.2015.
 */
public class User {

    private int id;
    private String login;
    private String userName;
    private int age;
    private String date;
    private int questCount;
    private float rating;

    private String passwordHash;
    private boolean banned;

    public User(int id, String login, String userName, int age, String date, int questCount, float rating, String passwordHash, boolean banned){
        this.id = id;
        this.login = login;
        this.userName  = userName;
        this.passwordHash = passwordHash;
        this.banned = banned;
        this.age = age;
        this.date = date;
        this.questCount = questCount;
        this.rating = rating;
    }


    public void setLogin(String login){}
    public String getLogin(){return login;}

    public void setPasswordHash(String passwordHash){}
    public String getPasswordHash(){return passwordHash;}

    public void setBanned(){banned=true;}
    public boolean isBanned(){return banned;}

    public String getUserName() {
        return userName;
    }

    public int getAge() {
        return age;
    }

    public String getDate() {
        return date;
    }

    public int getQuestCount() {
        return questCount;
    }

    public float getRating() {
        return rating;
    }
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
