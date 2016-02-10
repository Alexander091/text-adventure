package org.my.adventure.questeditor.web.jsf;

import javax.faces.bean.ManagedBean;
import javax.inject.Named;

import java.io.Serializable;

/**
 * Created by dimko_000 on 10.12.2015.
 */
@Named(value="test")
public class ActionController implements Serializable {
     public String getHelloWorld() {
          return "Hello, world!";
     }
}
