package org.textadventure.dashboard;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * Created by Максим on 29.12.2015.
 */

@ManagedBean(name="quest")
@SessionScoped
public class Quest {
    private String name;
    private String imageInd;

    public Quest(){};


    public String getName() {
        return "Quest Name";
    }

    public String getImageInd() {
        return imageInd;
    }


}
