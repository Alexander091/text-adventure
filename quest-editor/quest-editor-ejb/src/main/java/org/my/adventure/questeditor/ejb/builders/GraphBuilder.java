package org.my.adventure.questeditor.ejb.builders;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.my.adventure.dao_manager.api.entities.Node;
import org.my.adventure.dao_manager.api.entities.Quest;
import org.my.adventure.dao_manager.api.entities.Transition;
import org.my.adventure.questeditor.ejb.views.NodeView;
import org.my.adventure.questeditor.ejb.views.TransitionView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dimko_000 on 18.02.2016.
 */
public class GraphBuilder {
    private static int VISITED = 1;
    private static int UNVISITED = 0;
    public static Graph<NodeView,TransitionView> buildQuestGraph(Quest quest) {
        Graph<NodeView, TransitionView> graph = new DefaultDirectedGraph<NodeView, TransitionView>(TransitionView.class);
        Map<NodeView, Integer> visitMap = new HashMap<NodeView, Integer>();
        Node node = quest.getStartNode();
        NodeView nodeView = null;
        if(node!=null) {
            nodeView = ViewBuilder.buildNodeView(node);
            graph.addVertex(nodeView);
            visitMap.put(nodeView, UNVISITED);
        }
        while(nodeView!=null) {
            visitMap.put(nodeView, VISITED);
            for(Transition transition : nodeView.getEntity().getTransitions()) {
                Node destination = transition.getNodeByToNode();
                NodeView destinationNodeView = findNodeViewInVisitByNode(destination, visitMap);
                if(destinationNodeView==null) {
                    destinationNodeView = ViewBuilder.buildNodeView(destination);
                    visitMap.put(destinationNodeView,UNVISITED);
                    graph.addVertex(destinationNodeView);
                }
                TransitionView transitionView = ViewBuilder.buildTransitionView(transition,nodeView, destinationNodeView);
                graph.addEdge(nodeView, destinationNodeView, transitionView);
            }
            nodeView = findUnvisitedNodeView(visitMap);
        }
        return graph;
    }
    private static NodeView findNodeViewInVisitByNode(Node node, Map<NodeView, Integer> visitMap) {
        for (NodeView nodeView : visitMap.keySet()) {
            if(nodeView.getEntity().getId().longValue()==node.getId().longValue())
                return nodeView;
        }
        return null;
    }
    private static NodeView findUnvisitedNodeView( Map<NodeView, Integer> visitMap) {
        for(NodeView nodeView : visitMap.keySet()) {
            if(visitMap.get(nodeView)==UNVISITED)
                return nodeView;
        }
        return null;
    }
}
