package org.textadventure.dashboard;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Максим on 29.12.2015.
 *
 *
 */

@ManagedBean(name = "quests")
@ApplicationScoped
public class QuestControllerBean {
    private List<Quest> startedQuests;
    private List<Quest> completedQuests;
    private List<Quest> myQuests;
    public QuestControllerBean(){
        startedQuests = new ArrayList<Quest>();
        completedQuests = new ArrayList<Quest>();
        myQuests = new ArrayList<Quest>();
        startedQuests.add(new Quest());
        startedQuests.add(new Quest());
        completedQuests.add(new Quest());
        completedQuests.add(new Quest());
        completedQuests.add(new Quest());
        completedQuests.add(new Quest());
        completedQuests.add(new Quest());
        myQuests.add(new Quest());
        myQuests.add(new Quest());
        myQuests.add(new Quest());
    }

    public List<Quest> getStartedQuests(){
        return startedQuests;
    }
    public List<Quest> getCompletedQuests(){
        return completedQuests;
    }
    public List<Quest> getMyQuests(){
        return myQuests;
    }
}
