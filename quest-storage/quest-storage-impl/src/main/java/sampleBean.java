import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * Created by al on 31.12.2015.
 */
@ManagedBean
@SessionScoped
public class sampleBean {

    private String name = "Success";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
