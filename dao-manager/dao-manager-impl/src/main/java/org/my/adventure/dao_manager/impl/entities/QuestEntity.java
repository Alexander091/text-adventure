package org.my.adventure.dao_manager.impl.entities;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Дмитрий on 02.12.2015.
 */
@Entity
@Table(name = "quest", schema = "public", catalog = "netcracker")
public class QuestEntity extends CommonEntity{
    private String description;
    private String genre;
    private Integer version;
    private Integer ageLimit;
    private Float rating;
    private String name;
    private List<ItemEntity> items;
    private List<ResourceEntity> resources;
    private List<StatEntity> stats;
    private NodeEntity startNode;

    @OneToOne
    @JoinColumn(name="start_node", referencedColumnName = "id")
    public NodeEntity getStartNode() {
        return startNode;
    }

    public void setStartNode(NodeEntity startNode) {
        this.startNode = startNode;
    }

    @OneToMany(mappedBy = "quest")
    public List<ItemEntity> getItems() {

        return items;
    }

    public void setItems(List<ItemEntity> items) {
        this.items = items;
    }

    @OneToMany(mappedBy = "questByQuestId")
    public List<ResourceEntity> getResources() {
        return resources;
    }

    public void setResources(List<ResourceEntity> resources) {
        this.resources = resources;
    }

    @OneToMany(mappedBy = "questByQuestId")
    public List<StatEntity> getStats() {
        return stats;
    }

    public void setStats(List<StatEntity> stats) {
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
