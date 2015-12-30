import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * Created by al on 30.12.2015.
 */
@ManagedBean
@SessionScoped
public class userControllerBean {
    private String name = "Jhon Doe";
    private int userAge = 19;

    public int getUGserAge() {
        return userAge;
    }

    public void setUSserAge(int userAge) {
        this.userAge = userAge;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getS(){
        return "A";
    }
}
