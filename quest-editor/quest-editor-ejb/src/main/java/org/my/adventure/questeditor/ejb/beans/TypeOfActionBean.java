package org.my.adventure.questeditor.ejb.beans;

import org.my.adventure.dao_manager.api.dao.TypeOfActionDAO;
import org.my.adventure.dao_manager.api.entities.TypeOfAction;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

/**
 * Created by dimko_000 on 27.02.2016.
 */
@Stateless
public class TypeOfActionBean extends CommonBean<TypeOfAction> {
    @EJB
    TypeOfActionDAO typeOfActionDAO;

    public List<TypeOfAction> getAllTypes() {
        return typeOfActionDAO.getAll();
    }

    public TypeOfAction getById(Long id) {
        return typeOfActionDAO.getById(id);
    }

    public void delete(TypeOfAction object) {
        typeOfActionDAO.delete(object);
    }

    public void saveOrUpdate(TypeOfAction object) {
        typeOfActionDAO.saveOrUpdate(object);
    }
}
