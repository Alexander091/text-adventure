package entities;

import java.util.List;

/**
 * Created by Дмитрий on 11.12.2015.
 */
public interface Node extends Common {
    List<Transition> getTransitions();
    void setTransitions(List<Transition> transitions);
    String getName();
    void setName(String name);
    String getDescription();
    void setDescription(String description);
    Quest getQuestByQuestId();
    void setQuestByQuestId(Quest questByQuestId);
    List<Action> getActions();
    void setActions(List<Action> actions);
}
