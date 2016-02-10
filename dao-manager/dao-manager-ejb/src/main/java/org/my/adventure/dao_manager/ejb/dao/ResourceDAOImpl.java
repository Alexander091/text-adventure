package org.my.adventure.dao_manager.ejb.dao;

import org.my.adventure.dao_manager.api.dao.ResourceDAO;
import org.my.adventure.dao_manager.api.entities.Resource;

import javax.ejb.Remote;
import javax.ejb.Stateless;

/**
 * Created by Дмитрий on 11.12.2015.
 */

@Stateless
@Remote(ResourceDAO.class)
public class ResourceDAOImpl extends CommonDAOImpl<Resource>{
}
