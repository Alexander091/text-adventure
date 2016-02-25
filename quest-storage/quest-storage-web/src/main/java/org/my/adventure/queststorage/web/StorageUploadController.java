package org.my.adventure.queststorage.web;

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
public class StorageUploadController {

    private UploadedFile file;

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public void upload() {

    }
}