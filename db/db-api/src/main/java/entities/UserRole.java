package entities;

import java.util.List;

/**
 * Created by Дмитрий on 11.12.2015.
 */
public interface UserRole extends Common{
    List<User> getUsers();
    void setUsers(List<User> users);
    String getName();
    void setName(String name);
}
