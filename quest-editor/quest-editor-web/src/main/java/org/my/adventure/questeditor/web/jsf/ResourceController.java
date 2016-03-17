package org.my.adventure.questeditor.web.jsf;

import org.my.adventure.dao_manager.api.entities.Resource;
import org.my.adventure.questeditor.ejb.beans.ResourceUploaderBean;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.List;

/**
 * Created by al on 12.03.2016.
 */

@ManagedBean
@ViewScoped
public class ResourceController {

    @EJB
    private ResourceUploaderBean uploaderBean;
    public List<Resource> getSounds(Long questId) {return uploaderBean.getQuestResources(questId, 2L);}
    public List<Resource> getImages(Long questId){
        return uploaderBean.getQuestResources(questId, 1L);
    }

    public void deleteResource(Long resourceId){
        uploaderBean.deleteResource(resourceId);
    }

}
