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
    Quest quest = null;
    @PostConstruct
    private void init() {
        quest = questDAO.getById(123L);
    }
    public String getName() {
        return quest.getName();
    }
    public String getDescription(){
        return quest.getDescription();
    }
    public String getGenre() {
        return quest.getGenre();
    }
    public int getVersion() {
        return quest.getVersion();
    }
    public int getAgeLimit() {
        return quest.getAgeLimit();
    }
}
