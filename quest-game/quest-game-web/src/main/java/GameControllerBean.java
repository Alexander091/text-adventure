import org.my.adventure.questgame.ejb.CurrentGameStageBean;
import org.my.adventure.questgame.ejb.GameBean;
import org.my.adventure.questgame.impl.wrappers.TransitionWrapper;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.List;


/**
 * Created by Максим on 09.02.2016.
 */

@ManagedBean(name="gameController")
@SessionScoped
public class GameControllerBean implements Serializable {
    private static final long serialVersionUID = 1L;

    @EJB
    GameBean gameBean =null;
    @EJB
    CurrentGameStageBean currentGameStageBean;

    public void loadQuest(long id) {
        currentGameStageBean.loadGameByQuestId(id);
    }

    public String getName() {
        return currentGameStageBean.getQuest().getName();
    }

    public String getNodeText(){
        return gameBean.getCurrentWrappedNode().getText();
    }

    public String getNodeName(){
        return gameBean.getCurrentWrappedNode().getName();
    }

    public List<TransitionWrapper> getCurrentTransitions() {
        return  gameBean.getCurrentWrappedNode().getTransitions();
    }

    public void changeCurrentNode(long transId){
        gameBean.changeCurrentNode(transId);
    }
}
