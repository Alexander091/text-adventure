package org.my.adventure.questgame.impl.nodewr_builder;

import org.my.adventure.dao_manager.api.entities.Node;
import org.my.adventure.questgame.impl.wrappers.NodeWrapper;


/**
 * Created by Максим on 29.02.2016.
 */
public class NodeWrapperDirector {

    public NodeWrapper constructNodeWrapper(Node node) {
        NodeWrapperBuilder nodeWrapperBuilder = new NodeWrapperBuilder();
        nodeWrapperBuilder.createNodeWrapper();
        nodeWrapperBuilder.buildNodeName(node.getName());
        nodeWrapperBuilder.buildNodeText(node.getDescription());
        nodeWrapperBuilder.buildTransitions(node.getTransitions());
        nodeWrapperBuilder.buildActions(node.getActions());
        NodeWrapper nodeWrapper = nodeWrapperBuilder.getNodeWrapper();
        return nodeWrapper;
    }
}
