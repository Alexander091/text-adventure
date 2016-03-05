package org.my.adventure.questeditor.ejb.beans;

import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.my.adventure.dao_manager.api.dao.QuestDAO;
import org.my.adventure.dao_manager.api.dao.ResourceDAO;
import org.my.adventure.dao_manager.api.dao.TypeOfActionDAO;
import org.my.adventure.dao_manager.api.dao.TypeOfResourceDAO;
import org.my.adventure.dao_manager.api.entities.Resource;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.io.IOException;
import java.io.InputStream;

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

    public void setImage(Long resourceId, InputStream in){
        Resource resource = resourceDAO.getById(resourceId);
        try {
            resource.setData(IOUtils.toByteArray(in));
            resourceDAO.saveOrUpdate(resource);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }
}
