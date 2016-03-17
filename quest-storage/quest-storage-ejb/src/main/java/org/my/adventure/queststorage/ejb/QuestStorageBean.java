package org.my.adventure.queststorage.ejb;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateError;
import org.my.adventure.dao_manager.api.dao.QuestDAO;
import org.my.adventure.dao_manager.api.dao.ResourceDAO;
import org.my.adventure.dao_manager.api.dao.UserDAO;
import org.my.adventure.dao_manager.api.entities.Quest;
import org.my.adventure.dao_manager.api.entities.Resource;
import org.my.adventure.dao_manager.api.entities.User;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by al on 18.02.2016.
 */

@Stateless
public class QuestStorageBean {

    public static final String ALL_GENRES = "Все";
    public static final String MY_QUESTS = "Мои";
    public static final float DEFAULT_RATING = 0;
    public static final Long DEFAULT_PLACEHOLDER = 0L;

    private final Logger log = LogManager.getLogger(QuestStorageBean.class);

    @EJB
    private QuestDAO questDAO;

    @EJB
    ResourceDAO resourceDAO;

    @EJB
    UserDAO userDAO;

    public List<QuestWrapper> getQuests(){
        return getQuests(ALL_GENRES, null);
    }

    public byte[] getImageByQuestId(Long questId){
        Quest quest = questDAO.getById(questId);
        Resource resource = null;
        if (quest != null)
            resource = quest.getImage();
        if (resource != null)
            return resource.getData();
        else
            return null;
    }

    public byte[] getImageByResourceId(Long resourceId){
        Resource resource = resourceDAO.getById(resourceId);
        if (resource != null)
            return resource.getData();
        else
            return null;
    }

    public List<Quest> getQuestsByUserId(Long userId){
        User user = null;
        if (userId != null) {
            user = userDAO.getById(userId);
        }
        return user.getQuests();
    }

    public List<QuestWrapper> getQuests(String genre, Long userId){
        List<Quest> quests;
        switch (genre) {
            case ALL_GENRES:
                quests = questDAO.getAll();
                break;
            case MY_QUESTS:
                if (userId != null) {
                    quests = getQuestsByUserId(userId);
                } else {
                    quests = new ArrayList<>();
                }
                break;
            default:
                quests = questDAO.getAllByGenre(genre);
                break;
        }
        List<QuestWrapper> questWrappers = new ArrayList<>();
        for (Quest quest : quests) {
            if (quest.getId()!=null){
                QuestWrapper questWrapper = new QuestWrapper(quest);
            if (questWrapper.getImage() == null)
                questWrapper.setImage(resourceDAO.getById(DEFAULT_PLACEHOLDER));//Image placeholder
                if (questWrapper.getRating() == null)
                    questWrapper.setRating(DEFAULT_RATING);
                questWrappers.add(questWrapper);
            }
        }
        questWrappers.sort(new Comparator<QuestWrapper>() {
            public int compare(QuestWrapper o1, QuestWrapper o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        return questWrappers;
    }

    public void deleteQuest(Long id){
        log.debug("deleting " + id+ " " + questDAO.getById(id));
        try {
            questDAO.deleteById(id);
        }catch (HibernateError h){
            log.error(h.getMessage(), h);
        }
    }
}
