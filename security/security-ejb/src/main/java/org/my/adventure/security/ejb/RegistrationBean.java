package org.my.adventure.security.ejb;

import de.rtner.security.auth.spi.PBKDF2Parameters;
import de.rtner.security.auth.spi.SimplePBKDF2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.my.adventure.dao_manager.api.dao.UserDAO;
import org.my.adventure.dao_manager.api.dao.UserRoleDAO;
import org.my.adventure.dao_manager.api.entities.User;

import javax.ejb.EJB;
import javax.ejb.Stateless;


/**
 * Created by al on 16.03.2016.
 */

@Stateless
public class RegistrationBean {

    private final Logger log = LogManager.getLogger(RegistrationBean.class);
    private final Long ADMIN_ROLE = 0L;
    private final Long USER_ROLE = 1L;

    @EJB
    UserDAO userDAO;

    @EJB
    UserRoleDAO userRoleDAO;

    public String hash(String plainText) {
        if (plainText == null) return null;
        SimplePBKDF2 crypto = new SimplePBKDF2();
        PBKDF2Parameters params = crypto.getParameters();
        params.setHashCharset("UTF-8");
        params.setHashAlgorithm("HmacSHA1");
        params.setIterationCount(1000);
        return crypto.deriveKeyFormatted(plainText);
    }

    public void addUser(UserWrapper userWrapper){
        User user = new User();
        user.setName(userWrapper.getName());
        user.setSurname(userWrapper.getSurname());
        user.setLogName(userWrapper.getLogName());
        user.setEmail(userWrapper.getEmail());
        user.setUserRole(userRoleDAO.getById(USER_ROLE));
        user.setPassword(hash(userWrapper.getPassword()));
        userDAO.saveOrUpdate(user);
    }
}
