package org.my.adventure.questgame.impl;

import org.my.adventure.dao_manager.api.dao.NodeDAO;
import org.my.adventure.dao_manager.api.entities.Node;
import org.my.adventure.dao_manager.api.entities.Transition;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Максим on 28.01.2016.
 */


@Stateful
public class NodeBean {
    private Node currentNode;
    @EJB
    NodeDAO nodeDAO;

    private List<Transition> transitions;

    public NodeBean() {
        currentNode = QuestBean.quest.getStartNode();
        transitions = currentNode.getTransitions();
        //nodes = getAllNodes();
    }



    public void changeCurrentNode(long transId) {
        this.currentNode = findTransById(transId).getNodeByToNode();
        transitions = currentNode.getTransitions();
    }

    private Transition findTransById(long id){
        Transition tr = null;
        for(Transition t : transitions)
            if (t.getId() == id) {
                tr = t;
                break;
            }
        return tr;
    }

    public List<TransitionWrapper> getTransitions() {
        List<TransitionWrapper> trans = new ArrayList<TransitionWrapper>();
        for(Transition tr : transitions)
            trans.add(new TransitionWrapper(tr.getName(),tr.getId()));

        return trans;
    }

    public Node getCurrentNode(){
        return currentNode;
    }

    public String getNodeText(){
        return currentNode.getDescription();
    }


    public String getNodeName() {
        return currentNode.getName();
    }
}



//old:
    /*
@ManagedBean(name = "nodeBean")
@SessionScoped
public class NodeBean {



    public List<Node> createNodeList(int size) {
        List<Node> list = new ArrayList<Node>();
        for(int i = 0 ; i < size ; i++) {
            Node node = new Node(i+1,"This is NODE "+(i+1),"Go to node "+(i+1));

            list.add(node);

        }

        for(int i = 0 ; i < size ; i++) {
            if (i < size - 3) {
                list.get(i).addChild(list.get(i+1));
                list.get(i).addChild(list.get(i+2));
                list.get(i).addChild(list.get(i + 3));
            }
        }

        return list;
    }

}
*/