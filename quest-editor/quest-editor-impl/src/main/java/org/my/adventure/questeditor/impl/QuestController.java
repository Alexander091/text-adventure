package org.my.adventure.questeditor.impl;

import org.my.adventure.dao_manager.api.dao.QuestDAO;
import org.my.adventure.dao_manager.api.entities.Quest;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * Created by dimko_000 on 10.12.2015.
 */
@ManagedBean(name = "questBean")
@RequestScoped
public class QuestController {
    @EJB
    QuestDAO questDAO;

    public String getName() {
        return "Test Quest";
    }
    public String getDescription(){
        return questDAO.getTestObject().getDescription();
    }
    public String[] getAllGenres() {
        return new String[]{"Comedy", "Fantasy", "Horror"};
    }
    public int getVersion() {
        return 1;
    }
    public String[] getAllAgeLimits() {
        return new String[] {"0+","3+","6+","12+", "16+", "18+"};
    }
}
