import org.my.adventure.questgame.ejb.GameBean;
import org.my.adventure.questgame.ejb.GameStagesBean;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.io.Serializable;

/**
 * Created by Максим on 13.03.2016.
 */

@ManagedBean(name="timeController")
@RequestScoped
public class TimeControllerBean implements Serializable {
    private static final long serialVersionUID = 1L;


    @EJB
    GameStagesBean gameStagesBean;

    public void incrementTimer(long questId){
        gameStagesBean.incQuestTimer(questId);
    }

    public String getQuestTimer(long questId){
        return gameStagesBean.getQuestTimerString(questId);
    }

}
