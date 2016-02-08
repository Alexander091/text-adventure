package org.my.adventure.questeditor.impl;

import org.my.adventure.dao_manager.api.dao.NodeDAO;

import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * Created by dimko_000 on 07.02.2016.
 */
@Stateless
public class NodeBean {
    @EJB
    NodeDAO nodeDAO;


}
