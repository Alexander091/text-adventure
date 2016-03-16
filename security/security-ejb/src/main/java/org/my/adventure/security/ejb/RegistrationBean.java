package org.my.adventure.security.ejb;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.my.adventure.dao_manager.api.dao.UserDAO;
import org.my.adventure.dao_manager.api.dao.UserRoleDAO;
import org.my.adventure.dao_manager.api.entities.User;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;


/**
 * Created by al on 16.03.2016.
 */

@Stateless
public class RegistrationBean {

    private final Logger log = LogManager.getLogger(RegistrationBean.class);
    private final Long ADMIN_ROLE = 0L;

    @EJB
    UserDAO userDAO;

    @EJB
    UserRoleDAO userRoleDAO;

    public String generateHash(String input) throws NoSuchAlgorithmException {
        MessageDigest objSHA = MessageDigest.getInstance("SHA-256");
        byte[] bytSHA = objSHA.digest(input.getBytes());
        Base64.Encoder encoder = Base64.getEncoder();
        return encoder.encodeToString(bytSHA);
    }

    public void addUser(UserWrapper userWrapper){
        User user = new User();
        user.setName(userWrapper.getName());
        user.setSurname(userWrapper.getSurname());
        user.setLogName(userWrapper.getLogName());
        user.setEmail(userWrapper.getEmail());
        user.setUserRole(userRoleDAO.getById(ADMIN_ROLE));
        try {
            user.setPassword(generateHash(userWrapper.getPassword()));
        } catch (NoSuchAlgorithmException e) {
            log.error(e.getMessage(), e);
        }
        userDAO.saveOrUpdate(user);
    }
}
