package org.my.adventure.dao_manager.api.entities;

import java.util.List;
import javax.persistence.*;

/**
 * Created by Дмитрий on 02.12.2015.
 */
@Entity
@Table(name = "quest", schema = "public", catalog = "netcracker")
public class Quest extends Common{
    private String description;
    private String genre;
    private Integer version;
    private Integer ageLimit;
    private Float rating;
    private String name;
    private List<Item> items;
    private List<Resource> resources;
    private List<Stat> stats;
    private Node startNode;

    @OneToOne
    @JoinColumn(name="start_node", referencedColumnName = "id")
    public Node getStartNode() {
        return startNode;
    }

    public void setStartNode(Node startNode) {
        this.startNode = startNode;
    }

    @OneToMany(mappedBy = "quest")
    public List<Item> getItems() {

        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    @OneToMany(mappedBy = "questByQuestId")
    public List<Resource> getResources() {
        return resources;
    }

    public void setResources(List<Resource> resources) {
        this.resources = resources;
    }

    @OneToMany(mappedBy = "questByQuestId")
    public List<Stat> getStats() {
        return stats;
    }

    public void setStats(List<Stat> stats) {
        this.stats = stats;
    }


    @Basic
    @Column(name = "description", nullable = true, insertable = true, updatable = true, length = 2000)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "genre", nullable = false, insertable = true, updatable = true, length = 100)
    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Basic
    @Column(name = "version", nullable = true, insertable = true, updatable = true)
    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Basic
    @Column(name = "ageLimit", nullable = true, insertable = true, updatable = true)
    public Integer getAgeLimit() {
        return ageLimit;
    }

    public void setAgeLimit(Integer ageLimit) {
        this.ageLimit = ageLimit;
    }

    @Basic
    @Column(name = "rating", nullable = true, insertable = true, updatable = true, precision = 8)
    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    @Basic
    @Column(name = "name", nullable = false, insertable = true, updatable = true, length = 200)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
