import org.my.adventure.questgame.ejb.GameBean;
import org.my.adventure.questgame.impl.wrappers.NodeWrapper;
import org.my.adventure.questgame.impl.wrappers.TransitionWrapper;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.List;


/**
 * Created by Максим on 09.02.2016.
 */

@ManagedBean(name="gameController")
@ViewScoped
public class GameControllerBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private NodeWrapper node;

    @EJB
    GameBean gameBean =null;

    @PostConstruct
    void  init(){
        node = gameBean.getCurrentWrappedNode();
    }

    public void loadQuest(long id) {
        gameBean.loadGame(id);
    }

    public String getName() {
        return gameBean.getQuestName();
    }

    public String getNodeText(){
        return node.getText();
    }

    public String getNodeName(){
        return node.getName();
    }

    public List<TransitionWrapper> getCurrentTransitions() {
        return  node.getTransitions();
    }

    public void changeCurrentNode(long transId){
        node = gameBean.getNextWrappedNode(transId);
    }
}
