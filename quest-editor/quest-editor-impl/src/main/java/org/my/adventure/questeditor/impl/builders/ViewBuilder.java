package org.my.adventure.questeditor.impl.builders;

import org.jgrapht.Graph;
import org.my.adventure.dao_manager.api.entities.Node;
import org.my.adventure.dao_manager.api.entities.Quest;
import org.my.adventure.dao_manager.api.entities.Transition;
import org.my.adventure.questeditor.impl.GraphUtils;
import org.my.adventure.questeditor.impl.views.NodeView;
import org.my.adventure.questeditor.impl.views.TransitionView;
import org.primefaces.json.JSONObject;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by dimko_000 on 18.02.2016.
 */
public class ViewBuilder {
    private static AtomicInteger idGenerator = new AtomicInteger();
    public static NodeView buildNodeView(Node node) {
        NodeView nodeView = new NodeView("n" + idGenerator.incrementAndGet(), node);
        return nodeView;
    }
    public static NodeView buildNodeView(String data,Quest quest) {
        JSONObject jsonData = new JSONObject(data);
        Node node = new Node();
        node.setName(jsonData.getString("name"));
        node.setDescription(jsonData.getString("description"));
        node.setQuestByQuestId(quest);
        node.setPosition(jsonData.getString("position"));
        return buildNodeView(node);
    }
    public static TransitionView buildTransitionView(Transition transition, NodeView from, NodeView to) {
        TransitionView transitionView = new TransitionView("e" + idGenerator.incrementAndGet(),  from, to, transition);
        return transitionView;
    }
    public static TransitionView buildTransitionView(String data, Graph<NodeView, TransitionView> viewGraph) {
        JSONObject jsonData = new JSONObject(data);
        Transition transition = new Transition();
        transition.setName(jsonData.getString("name"));
        NodeView from = GraphUtils.getNodeViewByViewId(viewGraph,jsonData.getString("source"));
        NodeView to = GraphUtils.getNodeViewByViewId(viewGraph, jsonData.getString("target"));
        TransitionView transitionView = buildTransitionView(transition, from, to);
        return transitionView;
    }
}
