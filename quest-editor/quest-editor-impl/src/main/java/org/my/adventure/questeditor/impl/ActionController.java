package org.my.adventure.questeditor.impl;


import javax.faces.bean.ManagedBean;
import java.io.Serializable;

/**
 * Created by dimko_000 on 10.12.2015.
 */
@ManagedBean(name="test")
public class ActionController implements Serializable {
     public String getHelloWorld() {
          return "Hello, world!";
     }
}
