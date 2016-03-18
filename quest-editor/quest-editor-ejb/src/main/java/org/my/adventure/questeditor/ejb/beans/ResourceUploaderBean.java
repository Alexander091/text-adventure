package org.my.adventure.questeditor.ejb.beans;

import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateError;
import org.my.adventure.dao_manager.api.dao.QuestDAO;
import org.my.adventure.dao_manager.api.dao.ResourceDAO;
import org.my.adventure.dao_manager.api.dao.TypeOfResourceDAO;
import org.my.adventure.dao_manager.api.entities.Resource;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by al on 02.03.2016.
 */
@Stateless
public class ResourceUploaderBean {

    private final Logger log = LogManager.getLogger(ResourceUploaderBean.class);

    @EJB
    private ResourceDAO resourceDAO;

    @EJB
    QuestDAO questDAO;

    @EJB
    TypeOfResourceDAO typeOfResourceDAO;

    public byte[] getImage(Long resourceId){
        return resourceDAO.getById(resourceId).getData();
    }

    public List<Resource> getQuestResources(Long questId, Long typeOfResourceId){
        return resourceDAO.getResources(questId, typeOfResourceId);
    }
    public List<Resource> getPartialQuestResources(Long questId, Long typeOfResourceId){
        return resourceDAO.getPartialResources(questId, typeOfResourceId);
    }
    public void deleteResource(Long id){
        log.debug("deleting " + id+ " " + questDAO.getById(id));
        try {
            resourceDAO.deleteById(id);
        }catch (HibernateError h){
            log.error(h.getMessage(), h);
        }
    }

    public void saveResource(String name, Long questId, Long typeId, InputStream res){
        Resource resource = new Resource();
        resource.setName(name);
        if (questId != null)
            resource.setQuestByQuestId(questDAO.getById(questId));
        resource.setType(typeOfResourceDAO.getById(typeId));
        try {
            resource.setData(IOUtils.toByteArray(res));
            resourceDAO.saveOrUpdate(resource);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }
}
