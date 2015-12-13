package org.my.adventure.dao_manager.api.entities;

import java.util.List;

/**
 * Created by Дмитрий on 11.12.2015.
 */
public interface User extends Common {
    List<Game> getGames();
    void setGames(List<Game> games);
    UserRole getUserRole();
    void setUserRole(UserRole userRole);
    String getName();
    void setName(String name);
    String getSurname();
    void setSurname(String surname);
    String getLogName();
    void setLogName(String logName);
    String getPassword();
    void setPassword(String password);
    String getEmail();
    void setEmail(String email);

}
