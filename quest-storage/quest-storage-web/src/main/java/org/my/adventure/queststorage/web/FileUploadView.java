package org.my.adventure.queststorage.web;

/**
 * Created by al on 24.02.2016.
 */
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ManagedBean
public class FileUploadView {

    private UploadedFile file;

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public List<String> getImagesNames(){
        File f = new File("\\");
        ArrayList<String> names = new ArrayList<String>(Arrays.asList(f.list()));
        return names;
    }

    public void upload() {
        if(file != null) {
            InputStream inputStream = null;
            try {
                inputStream = file.getInputstream();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
            String questId = externalContext.getRequestParameterMap().get("editorForm:questIdInput");
//            String destPath = "/TextAdventure/text-adventure-app/text-adventure-app-web/src/main/webapp/resources/";
            String destPath = "";
            String fileExtension = FilenameUtils.getExtension(file.getFileName());
            if(fileExtension.equals("jpg")||fileExtension.equals("png")) {
                destPath = "/resources/images/"+file.getFileName();
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
            FacesMessage message = new FacesMessage("Файл ", file.getFileName() + " Загружен.");
            FacesContext.getCurrentInstance().addMessage(null,message);
        }
    }
}
