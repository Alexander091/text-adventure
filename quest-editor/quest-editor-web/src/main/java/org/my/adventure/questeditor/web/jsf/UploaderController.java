package org.my.adventure.questeditor.web.jsf;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import sun.misc.IOUtils;
import sun.nio.ch.IOUtil;

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
public class UploaderController {
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
            String questId = externalContext.getRequestParameterMap().get("editorForm:questIdInput");
            String destPath = "/text-adventure/text-adventure-app/text-adventure-app-web/src/main/webapp/resources/";
            String fileExtension = FilenameUtils.getExtension(file.getFileName());
            if(fileExtension.equals("jpg")||fileExtension.equals("png")) {
                destPath+="images/"+file.getFileName();
            }
            else if(fileExtension.equals("wav")||fileExtension.equals("mp3")) {
                destPath +="sounds/" + file.getFileName();
            }
            String basePath = new File("").getAbsolutePath();
            File destFile = new File(basePath.concat(destPath));
            try {
                FileUtils.copyInputStreamToFile(inputStream,destFile);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            FacesMessage message = new FacesMessage("Файл", event.getFile().getFileName() + " Загружен.");
            FacesContext.getCurrentInstance().addMessage(null,message);
        }

    }
}
