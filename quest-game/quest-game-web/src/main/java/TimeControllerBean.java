import org.my.adventure.questgame.ejb.GameBean;
import org.my.adventure.questgame.ejb.GameStagesBean;
import org.my.adventure.questgame.impl.QuestTimer;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Максим on 13.03.2016.
 */

@ManagedBean(name="timeController")
@SessionScoped
public class TimeControllerBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private Map<Long, QuestTimer> timeMap;
    private long questId;

    //@PostConstruct
    public void init(){
        timeMap = new HashMap<Long, QuestTimer>();
        timeMap.put(questId,new QuestTimer());
    }


    public void incrementTimer(){
        timeMap.get(questId).increment();
    }

    public String getQuestTimer(){
        return timeMap.get(questId).toString();
    }

    public void setQuestId(long questId) {
        this.questId = questId;
    }

    public long getQuestId(){
        return questId;
    }
}
