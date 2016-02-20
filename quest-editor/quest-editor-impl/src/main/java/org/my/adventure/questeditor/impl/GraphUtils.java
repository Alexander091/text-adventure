package org.my.adventure.questeditor.impl;

import org.jgrapht.Graph;
import org.my.adventure.questeditor.impl.views.NodeView;
import org.my.adventure.questeditor.impl.views.TransitionView;

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
}
