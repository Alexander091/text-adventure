package org.my.adventure.questeditor.web.jsf;

import org.my.adventure.questeditor.impl.beans.NodeBean;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * Created by dimko_000 on 10.12.2015.
 */
@ManagedBean(name = "nodeController")
@SessionScoped
public class NodeController {
    @EJB
    NodeBean nodeBean;

}
