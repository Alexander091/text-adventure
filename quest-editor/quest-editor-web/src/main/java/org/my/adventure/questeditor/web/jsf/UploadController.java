package org.my.adventure.questeditor.web.jsf;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.my.adventure.questeditor.ejb.beans.ResourceUploaderBean;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import sun.misc.IOUtils;
import sun.nio.ch.IOUtil;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by dimko_000 on 11.02.2016.
 */
@ManagedBean(name="uploadController")
public class UploadController {

    @EJB
    private ResourceUploaderBean uploaderBean;

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
            ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
            String questIdStr = externalContext.getRequestParameterMap().get("editorForm:questIdInput");
//            Long questId = Long.parseLong(questIdStr);
            Long questId = null;
            String fileExtension = FilenameUtils.getExtension(file.getFileName());
            Long typeId = null;
            if(fileExtension.equals("jpg")||fileExtension.equals("png")) {
                typeId = 1L;
            }
            else if(fileExtension.equals("wav")||fileExtension.equals("mp3")) {
                typeId = 2L;
            }
            uploaderBean.saveResource(file.getFileName(), questId, typeId, inputStream);
            FacesMessage message = new FacesMessage("Файл", event.getFile().getFileName() + " Загружен.");
            FacesContext.getCurrentInstance().addMessage(null,message);
        }

    }
}
