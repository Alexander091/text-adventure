import javax.annotation.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * Created by al on 31.12.2015.
 */
@javax.faces.bean.ManagedBean
@SessionScoped
public class sampleBean {

    private String name = "Success";

    private String val = "Yes";

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
