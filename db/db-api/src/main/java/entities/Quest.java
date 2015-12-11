package entities;

import java.util.List;

/**
 * Created by Дмитрий on 11.12.2015.
 */
public interface Quest extends Common {
    Node getStartNode();
    void setStartNode(Node startNode);
    List<Item> getItems();
    void setItems(List<Item> items);
    List<Resource> getResources();
    void setResources(List<Resource> resources);
    List<Stat> getStats();
    void setStats(List<Stat> stats);
    String getDescription();
    void setDescription(String description);
    String getGenre();
    void setGenre(String genre);
    Integer getVersion();
    void setVersion(Integer version);
    Integer getAgeLimit();
    void setAgeLimit(Integer ageLimit);
    Float getRating();
    void setRating(Float rating);
    String getName();
    void setName(String name);

}
