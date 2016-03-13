package org.my.adventure.queststorage.web;

import org.my.adventure.queststorage.ejb.QuestStorageBean;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import java.io.ByteArrayInputStream;
import java.io.Serializable;

/**
 * Created by al on 13.03.2016.
 */

@ManagedBean
@SessionScoped
public class StorageResourceController implements Serializable {

    @EJB
    QuestStorageBean questStorageBean;

    public StreamedContent getImage(){
        StreamedContent streamedContent;
        FacesContext context = FacesContext.getCurrentInstance();
        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
//             So, we're rendering the HTML. Return a stub StreamedContent so that it will generate right URL.
            streamedContent = new DefaultStreamedContent();
        }else {
            String resourceId = context.getExternalContext().getRequestParameterMap().get("resourceId");
            byte[] imageData = questStorageBean.getImageByResourceId(Long.parseLong(resourceId));
            if (imageData != null) {
                streamedContent = new DefaultStreamedContent(new ByteArrayInputStream(imageData), "image/jpg");
            } else {
                streamedContent = new DefaultStreamedContent();
            }
        }
        return streamedContent;
    }
}
