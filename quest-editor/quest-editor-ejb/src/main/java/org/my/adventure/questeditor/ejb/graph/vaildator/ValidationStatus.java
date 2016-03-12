package org.my.adventure.questeditor.ejb.graph.vaildator;

/**
 * Created by al on 03.03.2016.
 */
public enum ValidationStatus {
    VALID,
    NOT_VALID,
    NOT_VALID_START_NODE,
    NOT_VALID_END_NODE,
    NOT_VALID_START_AND_END_NODE,
    NOT_VALID_CONNECTIVITY,
    ERROR
}
