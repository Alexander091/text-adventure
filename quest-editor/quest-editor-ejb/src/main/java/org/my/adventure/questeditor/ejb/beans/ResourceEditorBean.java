package org.my.adventure.questeditor.ejb.beans;

import org.my.adventure.dao_manager.api.dao.ResourceDAO;
import org.my.adventure.dao_manager.api.entities.Resource;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

/**
 * Created by dimko_000 on 28.02.2016.
 */
@Stateless
public class ResourceEditorBean extends CommonBean<Resource>{
    @EJB
    ResourceDAO resourceDAO;
    public Resource getById(Long id) {
        return resourceDAO.getById(id);
    }

    public void saveOrUpdate(Resource object) {
        resourceDAO.saveOrUpdate(object);
    }

    public void delete(Resource object) {
        resourceDAO.saveOrUpdate(object);
    }
    public List<Resource> getResourcesList(Long questId, Long typeOfResourceId) {
        return resourceDAO.getResources(questId, typeOfResourceId);
    }
}
