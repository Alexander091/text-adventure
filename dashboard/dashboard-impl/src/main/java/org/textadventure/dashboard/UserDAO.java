package org.textadventure.dashboard;

/**
 * Created by Максим on 10.12.2015.
 */
public class UserDAO {

    public User getUserByID(int id){
        User user = new User(id, getLogin(), getUserName(),getAge(),getDate(),getQuestsCount(),getRating(), getPasswordHash(),isBanned());
        return user;
    }

    private String getUserName() {
        return "Maksim Zheltoukhov";
    }

    public void setLogin(String login){}
    public String getLogin(){return "user1";}
    public int getAge(){return 20;}
    public String getDate(){return "20.12.15";}
    public int getQuestsCount(){return 5;}
    public float getRating(){return  55.7f;}

    public void setPasswordHash(String passwordHash){}
    public String getPasswordHash(){return "abc";}


    public boolean isBanned(){return false;}

}
