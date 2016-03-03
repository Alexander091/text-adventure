package org.my.adventure.questeditor.ejb.graph.vaildator;

import org.jgrapht.Graph;
import org.my.adventure.dao_manager.api.entities.Node;
import org.my.adventure.questeditor.ejb.views.NodeView;
import org.my.adventure.questeditor.ejb.views.TransitionView;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by al on 03.03.2016.
 */
public class Validator {

    public void marknode(NodeView node, Set<NodeView> nodes, Graph<NodeView, TransitionView> graph){
        Set<TransitionView> transitions = graph.edgesOf(node);
        if (nodes.contains(node)){
            nodes.remove(node);
            for (TransitionView transition : transitions) {
                if (transition.getFrom() == node && transition.getTo() != node)
                    marknode(transition.getTo(), nodes, graph);
            }
        }
    }

    public ValidationStatus testConnectivity(Graph<NodeView, TransitionView> graph){
        ValidationStatus result = ValidationStatus.NOT_VALID_CONNECTIVITY;
        Set<NodeView> nodes = graph.vertexSet();
        HashSet<NodeView> markSet = new HashSet<NodeView>();
        for (NodeView node : nodes) {
            markSet.add(node);
        }
        marknode(nodes.iterator().next(), markSet, graph);
        if (markSet.isEmpty())
            result = ValidationStatus.VALID;
        return result;
    }

    public ValidationStatus testStartEnd(Graph<NodeView, TransitionView> graph){
        ValidationStatus result = ValidationStatus.NOT_VALID;
        boolean endFinded = false;
        boolean startFinded = false;
        Set<NodeView> nodes = graph.vertexSet();
        for (NodeView node : nodes) {
            boolean endNode = true;
            boolean startNode = true;
            Set<TransitionView> transitionViews = graph.edgesOf(node);
            for (TransitionView transitionView : transitionViews) {

                if (!endFinded && transitionView.getFrom() == node){
                    endNode = false;
                }
                if (!startFinded && transitionView.getTo() == node){
                    startNode = false;
                }
            }
            if (endNode)
                endFinded = true;
            if (startNode)
                startFinded = true;
            if (startFinded && endFinded)
                return ValidationStatus.VALID;
        }
        if (startFinded && endFinded)
            result = ValidationStatus.NOT_VALID_SATRT_AND_END_NODE;
        else if (!startFinded)
            result = ValidationStatus.NOT_VALID_START_NODE;
        else if (!endFinded)
            result = ValidationStatus.NOT_VALID_END_NODE;
        return result;
    }
}
