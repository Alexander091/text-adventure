package org.my.adventure.questeditor.ejb.graph.vaildator;

import org.jgrapht.Graph;
import org.my.adventure.questeditor.ejb.views.NodeView;
import org.my.adventure.questeditor.ejb.views.TransitionView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by al on 03.03.2016.
 */
public class Validator {

    private void markNode(NodeView node, Set<NodeView> nodes, Graph<NodeView, TransitionView> graph){
        Set<TransitionView> transitions = graph.edgesOf(node);
        if (nodes.contains(node)){
            nodes.remove(node);
            for (TransitionView transition : transitions) {
                if (transition.getFrom() == node && transition.getTo() != node)
                    markNode(transition.getTo(), nodes, graph);
            }
        }
    }

    private ValidationStatus testConnectivity(Graph<NodeView, TransitionView> graph, NodeView startNode){
        ValidationStatus result = ValidationStatus.NOT_CONNECTED;
        Set<NodeView> nodes = graph.vertexSet();
        //if start node is null searching for first not end node;
        if (nodes != null){
            if (startNode == null){
                do {
                    startNode = nodes.iterator().next();
                }while (isEnd(graph, startNode));
            }
            HashSet<NodeView> markSet = new HashSet<>();
            for (NodeView node : nodes) {
                markSet.add(node);
            }
            markNode(startNode, markSet, graph);
            if (markSet.isEmpty())
                result = ValidationStatus.VALID;
        }
        return result;
    }

    private List<NodeView> findStart(Graph<NodeView, TransitionView> graph){
        List<NodeView> startNodes = new ArrayList<>();
        NodeView start = null;
        Set<NodeView> nodes = graph.vertexSet();
        nodeLoop:
        for (NodeView node : nodes) {
            start = node;
            Set<TransitionView> transitions = graph.edgesOf(node);
            for (TransitionView transition : transitions) {
                if (transition.getTo() == node){
                    start = null;
                    continue nodeLoop;
                }
            }
            startNodes.add(start);
        }
        return startNodes;
    }

    private ValidationStatus haveEnd(Graph<NodeView, TransitionView> graph){
        Set<NodeView> nodes = graph.vertexSet();
        nodeLoop:
        for (NodeView node : nodes) {
            Set<TransitionView> transitions = graph.edgesOf(node);
            for (TransitionView transition : transitions) {
                if (transition.getFrom() == node){
                    continue nodeLoop;
                }
                return ValidationStatus.VALID;
            }
        }
        return ValidationStatus.MISSING_END_NODE;
    }

    private boolean isEnd(Graph<NodeView, TransitionView> graph, NodeView node){
        Set<TransitionView> transitions = graph.edgesOf(node);
        for (TransitionView transition : transitions) {
            if (transition.getFrom() == node)
                return false;
        }
        return true;
    }

    public List<ValidationStatus> validate(Graph<NodeView, TransitionView> graph){
        List<ValidationStatus> result = new ArrayList<>();

        ValidationStatus endRes = haveEnd(graph);
        if (endRes != ValidationStatus.VALID){
            result.add(endRes);
        }

        List<NodeView> startNodes = findStart(graph);
        NodeView startNode = null;
        switch (startNodes.size()){
            case 1: startNode = startNodes.get(0);
                break;
            case 0: result.add(ValidationStatus.MISSING_START_NODE);
                break;
            default: result.add(ValidationStatus.MULTIPLE_START_NODES);
                break;
        }

        if (result.contains(ValidationStatus.MULTIPLE_START_NODES)){
            result.add(ValidationStatus.NOT_CONNECTED);
        }else {
            ValidationStatus connRes = testConnectivity(graph, startNode);
            if (connRes != ValidationStatus.VALID){
                result.add(connRes);
            }
        }

        return result;
    }
}
