import org.my.adventure.dao_manager.api.entities.Node;
import org.my.adventure.dao_manager.api.entities.Transition;

import org.my.adventure.questgame.impl.NodeBean;
import org.my.adventure.questgame.impl.TransitionWrapper;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;


/**
 * Created by Максим on 09.02.2016.
 */

@ManagedBean(name="nodeController")
@SessionScoped
public class NodeControllerBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<TransitionWrapper> currentTransitions;

    @EJB
    NodeBean nodeBean=null;

    @PostConstruct
    public void init() {
        currentTransitions = nodeBean.getTransitions();
    }

    public String getNodeText(){
        return nodeBean.getNodeText();
    }

    public String getNodeName(){
        return nodeBean.getNodeName();
    }

    public List<TransitionWrapper> getCurrentTransitions() {
        return currentTransitions;
    }

    public void changeCurrentNode(long transId){
        nodeBean.changeCurrentNode(transId);
        currentTransitions = nodeBean.getTransitions();
    }
}
