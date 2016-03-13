package org.my.adventure.questeditor.ejb;

import org.jgrapht.Graph;
import org.my.adventure.questeditor.ejb.views.NodeView;
import org.my.adventure.questeditor.ejb.views.TransitionView;

/**
 * Created by dimko_000 on 19.02.2016.
 */
public class GraphUtils {
    public static NodeView getNodeViewByViewId(Graph<NodeView, TransitionView> graph, String viewId) {
        for(NodeView nodeView : graph.vertexSet()) {
            if(nodeView.getViewId().equals(viewId))
                return nodeView;
        }
        return null;
    }
    public static TransitionView getTransitionViewByViewId(Graph<NodeView, TransitionView> graph, String viewId) {
        for(TransitionView transitionView : graph.edgeSet()) {
            if(transitionView.getViewId().equals(viewId))
                return transitionView;
        }
        return null;
    }
}
