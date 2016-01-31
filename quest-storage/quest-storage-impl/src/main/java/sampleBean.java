import com.sun.org.glassfish.gmbal.ManagedData;

import javax.annotation.ManagedBean;

/**
 * Created by al on 31.12.2015.
 */
@ManagedBean
public class sampleBean {

    private String name = "Success";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
