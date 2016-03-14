package org.my.adventure.questeditor.ejb.graph.vaildator;

import org.jgrapht.DirectedGraph;
import org.jgrapht.Graph;
import org.my.adventure.dao_manager.api.entities.Node;
import org.my.adventure.dao_manager.api.entities.Transition;
import org.my.adventure.questeditor.ejb.views.NodeView;
import org.my.adventure.questeditor.ejb.views.TransitionView;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by al on 03.03.2016.
 */
public class Validator {

    private ValidationStatus testConnectivity(Graph<NodeView, TransitionView> graph){
        Set<NodeView> nodes = graph.vertexSet();
        for(NodeView node : nodes) {
            Set<TransitionView> incomingEdges = ((DirectedGraph<NodeView, TransitionView>)graph).incomingEdgesOf(node);
            Set<TransitionView> outgoingEdges = ((DirectedGraph<NodeView, TransitionView>)graph).outgoingEdgesOf(node);
            if((incomingEdges.size()==0 && outgoingEdges.size()==0) || isAllSelfEdges(graph,node))
                return ValidationStatus.NOT_CONNECTED;
        }
        return ValidationStatus.VALID;

    }
    private boolean isAllSelfEdges(Graph<NodeView, TransitionView> graph, NodeView nodeView) {
        Set<TransitionView> incomingEdges = ((DirectedGraph<NodeView, TransitionView>)graph).incomingEdgesOf(nodeView);
        Set<TransitionView> outgoingEdges = ((DirectedGraph<NodeView, TransitionView>)graph).outgoingEdgesOf(nodeView);
        for(TransitionView transitionView : outgoingEdges) {
            if(!graph.getEdgeTarget(transitionView).equals(nodeView)) {
                return false;
            }
        }
        for(TransitionView transitionView : incomingEdges) {
            if(!graph.getEdgeSource(transitionView).equals(nodeView)) {
                return false;
            }
        }
        return true;
    }
    private ValidationStatus testMissingEnd(Graph<NodeView, TransitionView> graph){
        Set<NodeView> nodes = graph.vertexSet();
        for(NodeView node : nodes) {
            Set<TransitionView> incomingEdges = ((DirectedGraph<NodeView, TransitionView>)graph).incomingEdgesOf(node);
            Set<TransitionView> outgoingEdges = ((DirectedGraph<NodeView, TransitionView>)graph).outgoingEdgesOf(node);
            if(incomingEdges.size()!=0 && outgoingEdges.size()==0)
                return ValidationStatus.VALID;
        }
        return ValidationStatus.MISSING_END_NODE;
    }

    private ValidationStatus testMissingOrMultipleStartNode(Graph<NodeView, TransitionView> graph) {
        Set<NodeView> nodes = graph.vertexSet();
        int startNodes = 0;
        for(NodeView node : nodes) {
            Set<TransitionView> incomingEdges = ((DirectedGraph<NodeView, TransitionView>)graph).incomingEdgesOf(node);
            Set<TransitionView> outgoingEdges = ((DirectedGraph<NodeView, TransitionView>)graph).outgoingEdgesOf(node);
            if(incomingEdges.size()==0 && outgoingEdges.size()!=0) {
                startNodes++;
            }
        }
        if (startNodes==0) {
            return ValidationStatus.MISSING_START_NODE;
        }
        else if (startNodes>1) {
            return ValidationStatus.MULTIPLE_START_NODES;
        }
        else
            return ValidationStatus.VALID;
    }

    private ValidationStatus testValidStartNode(Graph<NodeView, TransitionView> graph, Node startNode) {
        Set<NodeView> nodes = graph.vertexSet();
        for(NodeView nodeView : nodes) {
            Set<TransitionView> incomingEdges = ((DirectedGraph<NodeView, TransitionView>)graph).incomingEdgesOf(nodeView);
            Set<TransitionView> outgoingEdges = ((DirectedGraph<NodeView, TransitionView>)graph).outgoingEdgesOf(nodeView);
            if(incomingEdges.size()==0 && outgoingEdges.size()!=0) {
                if(nodeView.getEntity().equals(startNode)) {
                    return ValidationStatus.VALID;
                }
                else
                    return ValidationStatus.INVALID_START_NODE;
            }
        }
        return ValidationStatus.INVALID_START_NODE;
    }





    public List<ValidationStatus> validate(Graph<NodeView, TransitionView> graph, Node startNode){
        List<ValidationStatus> result = new ArrayList<>();

        ValidationStatus connectivityResult = testConnectivity(graph);
        if(connectivityResult != ValidationStatus.VALID)
            result.add(connectivityResult);

        ValidationStatus missingEndResult = testMissingEnd(graph);
        if(missingEndResult != ValidationStatus.VALID)
            result.add(missingEndResult);

        ValidationStatus missingOrMultipleStartResult = testMissingOrMultipleStartNode(graph);
        if(missingOrMultipleStartResult != ValidationStatus.VALID)
            result.add(missingOrMultipleStartResult);

        ValidationStatus invalidStartResult = testValidStartNode(graph, startNode);
        if(invalidStartResult != ValidationStatus.VALID)
            result.add(invalidStartResult);

        if(result.size()==0)
            result.add(ValidationStatus.VALID);

        return result;
    }
}
