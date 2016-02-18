package org.my.adventure.questeditor.impl.builders;

import org.my.adventure.dao_manager.api.entities.Node;
import org.my.adventure.dao_manager.api.entities.Transition;
import org.my.adventure.questeditor.impl.views.NodeView;
import org.my.adventure.questeditor.impl.views.TransitionView;

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
    public static TransitionView buildTransitionView(Transition transition, NodeView from, NodeView to) {
        TransitionView transitionView = new TransitionView("e" + idGenerator.incrementAndGet(),  from, to, transition);
        return transitionView;
    }
}
