package org.my.adventure.questeditor.ejb.graph.vaildator;

/**
 * Created by al on 03.03.2016.
 */
public enum ValidationStatus {
    VALID,
    MISSING_START_NODE,
    MULTIPLE_START_NODES,
    MISSING_END_NODE,
    NOT_CONNECTED
}
