package org.my.adventure.questeditor.web.jsf;

import org.my.adventure.dao_manager.api.entities.Resource;
import org.my.adventure.questeditor.ejb.beans.ResourceEditorBean;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

/**
 * Created by dimko_000 on 05.03.2016.
 */
@ManagedBean
@RequestScoped
public class ResourceConverter implements Converter {
    @EJB
    ResourceEditorBean resourceEditorBean;
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        if(s==null||s.isEmpty()) {
            return  null;
        }
        try {
            Resource resource = resourceEditorBean.getById(Long.valueOf(s));
            return resource;
        }
        catch (NumberFormatException e) {
            throw new ConverterException(new FacesMessage(String.format("%s is not a valid resource ID", s)),e);
        }
    }

    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        if(o==null||o=="") {
            return "";
        }
        if(o instanceof Resource) {
            return String.valueOf(((Resource)o).getId());
        }
        else {
            throw new ConverterException(new FacesMessage(String.format("%s is not a valid resource ID", o)));
        }
    }
}
