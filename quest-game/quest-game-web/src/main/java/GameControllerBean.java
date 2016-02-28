import org.my.adventure.questgame.ejb.GameBean;
import org.my.adventure.questgame.impl.wrappers.NodeWrapper;
import org.my.adventure.questgame.impl.wrappers.TransitionWrapper;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;


import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;


/**
 * Created by ������ on 09.02.2016.
 */

@ManagedBean(name="gameController")
@ViewScoped
public class GameControllerBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private long questId;
    private NodeWrapper node;
    StreamedContent streamedContent;

    @EJB
    GameBean gameBean;

    public void loadQuest() {
        gameBean.loadGame(questId);
        node = gameBean.getCurrentWrappedNode(questId);
        //gameBean.input(questId);
    }

    public String getName() {
        return gameBean.getWrappedQuest(questId).getQuestName();
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
        node = gameBean.getNextWrappedNode(questId,transId);
    }

    public void setQuestId(long questId) {
        this.questId = questId;
    }

    public long getQuestId(){
        return questId;
    }

    public StreamedContent getImage() {
        byte[] image = gameBean.getImage(questId);

        if (image != null) {
            streamedContent = new DefaultStreamedContent(new ByteArrayInputStream(image), "image/jpg");
        }
        else {
            streamedContent = null;
        }
        return streamedContent;
    }





}
