package org.my.adventure.questeditor.impl;

import org.my.adventure.dao_manager.api.dao.QuestDAO;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.List;

/**
 * Created by dimko_000 on 10.12.2015.
 */
@ManagedBean(name="questBean")
public class QuestController {
    QuestDAO questDAO;
    public String getName() {
        return "Test Quest";
    }
    public String getDescription() {
        return "Some words about quest...";
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
