package org.my.adventure.questeditor.web.jsf;

import org.apache.commons.io.FilenameUtils;
import org.my.adventure.dao_manager.api.entities.Resource;
import org.my.adventure.questeditor.ejb.beans.ResourceUploaderBean;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dimko_000 on 11.02.2016.
 */
@ManagedBean(name="uploadController")
@SessionScoped
public class UploadController implements Serializable{

    private static final Long IMAGE = 1L;
    private static final Long SOUND = 2L;

    @EJB
    private ResourceUploaderBean uploaderBean;

    Long questId;

    public Long getQuestId() {
        return questId;
    }

    public void setQuestId(Long questId) {
        this.questId = questId;
    }

    public StreamedContent getImage(){
        StreamedContent streamedContent;
        FacesContext context = FacesContext.getCurrentInstance();
        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
//             So, we're rendering the HTML. Return a stub StreamedContent so that it will generate right URL.
            streamedContent = new DefaultStreamedContent();
        }else {
            String resourceId = context.getExternalContext().getRequestParameterMap().get("resourceId");
            byte[] imageData = uploaderBean.getImage(Long.parseLong(resourceId));
            if (imageData != null) {
                streamedContent = new DefaultStreamedContent(new ByteArrayInputStream(imageData), "image/jpg");
            } else {
                streamedContent = new DefaultStreamedContent();
            }
        }
        return streamedContent;
    }
    public StreamedContent getSound(){
        StreamedContent streamedContent;
        FacesContext context = FacesContext.getCurrentInstance();
        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
//             So, we're rendering the HTML. Return a stub StreamedContent so that it will generate right URL.
            streamedContent = new DefaultStreamedContent();
        }else {
            String resourceId = context.getExternalContext().getRequestParameterMap().get("resourceId");
            byte[] imageData = uploaderBean.getImage(Long.parseLong(resourceId));
            if (imageData != null) {
                streamedContent = new DefaultStreamedContent(new ByteArrayInputStream(imageData), "audio/mpeg");
            } else {
                streamedContent = new DefaultStreamedContent();
            }
        }
        return streamedContent;
    }
    public List<Resource> getImages(){
        return uploaderBean.getQuestResources(questId, IMAGE);
    }

    public List<Long> getImagesId(){
        List<Long> list = new ArrayList<>();
        List<Resource> resources = uploaderBean.getQuestResources(questId, IMAGE);
        for (Resource resource : resources) {
            list.add(resource.getId());
        }
        return list;
    }

    public void deleteResource(Long resourceId){
        uploaderBean.deleteResource(resourceId);
    }

    public void upload(FileUploadEvent event) {
        UploadedFile file = event.getFile();
        if(file!=null) {
            InputStream inputStream = null;
            try {
                inputStream = event.getFile().getInputstream();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            String fileExtension = FilenameUtils.getExtension(file.getFileName());
            Long typeId = null;
            if(fileExtension.equals("jpg")||fileExtension.equals("png")) {
                typeId = IMAGE;
            }
            else if(fileExtension.equals("wav")||fileExtension.equals("mp3")) {
                typeId = SOUND;
            }

            FacesContext context = FacesContext.getCurrentInstance();
            Long questId = (Long) event.getComponent().getAttributes().get("questId");

            uploaderBean.saveResource(file.getFileName(), questId, typeId, inputStream);
            FacesMessage message = new FacesMessage("Файл", event.getFile().getFileName() + " Загружен.");
            FacesContext.getCurrentInstance().addMessage(null,message);
        }
    }
}
