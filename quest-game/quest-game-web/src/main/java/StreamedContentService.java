import org.my.adventure.questgame.ejb.GameBean;
import org.my.adventure.questgame.ejb.GameStagesBean;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import java.io.ByteArrayInputStream;
import java.io.Serializable;

/**
 * Created by Максим on 06.03.2016.
 */

@ManagedBean(name="contentService")
@SessionScoped
public class StreamedContentService implements Serializable {
    private static final long serialVersionUID = 1L;

    @EJB
    GameStagesBean gameStagesBean;

    public StreamedContent getStreamedContent(){

        FacesContext context = FacesContext.getCurrentInstance();
        StreamedContent streamedContent = null;

        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            streamedContent = new DefaultStreamedContent();
        }
        else {
            String resourceId = context.getExternalContext().getRequestParameterMap().get("resourceId");
            byte[] image = gameStagesBean.getResourceById(Long.valueOf(resourceId)).getData();
            if (image!=null) {
                streamedContent = new DefaultStreamedContent(new ByteArrayInputStream(image));
            }
            /*else {
                return null;
            }*/
        }

        return streamedContent;

    }

}