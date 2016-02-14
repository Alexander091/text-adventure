import org.my.adventure.questgame.impl.QuestBean;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Максим on 27.01.2016.
 */

@ManagedBean(name="questController")
@SessionScoped
public class QuestControllerBean implements Serializable {
    private static final long serialVersionUID = 1L;

    @Inject
    QuestBean questBean = null;
    @PostConstruct
    public void init() {
        loadQuest(123L);
    }
    public void loadQuest(long id) {
        questBean.loadQuest(id);
    }

    public String getName() {
        return questBean.getName();
    }

    public int getVersion() {
        return questBean.getVersion();
    }

    public int getAgeLimit() {
        return questBean.getAgeLimit();
    }

    public String getQuestDescription(){return questBean.getQuestDescription();}

    public List<String> getNodesNames() {
        List<String> list = new ArrayList<String>();
        for(int i = 0; i<5; i++)
            list.add("Step "+(i+1));
        return list;
    }














//old:
    /*
    private GameDAO userDAO;
    private List<String> texts;
    private List<Node> nodes;
    //private User user;
    //private User user;
    private int id = -1;
    private int currentId = 1;

    @ManagedProperty("#{nodeBean}")
    private NodeBean nodeBean;

    @PostConstruct
    public void init() {
       nodes = nodeBean.createNodeList(10);
    }

    public QuestControllerBean(){
        userDAO = new GameDAO();
        texts = new ArrayList<String>();
        texts.add("Node 0 text");
        texts.add("Node 1 text");
        texts.add("Node 2 text");
        texts.add("Node 3 text");
    }

    public List<Node> getNodes(){
        return nodes;
    }

    //---
    public String getText(){
        id++;
        return texts.get(id);
    }
    //---

    public String getNodeText(){
        return nodes.get(currentId-1).getText();
    }

    public List<Node> getChildren(){
        List<Node> list = nodes.get(currentId-1).getChildren();
        return list;
    }

    public void changeCurrentId(int id){
        currentId = id;
    }


    public void setNodeBean(NodeBean nodeBean) {
        this.nodeBean = nodeBean;
    }*/
}
