package entities;

import javax.persistence.*;
import java.util.List;

/**
 * Created by ������� on 02.12.2015.
 */
@Entity
@Table(name = "Quest", schema = "public", catalog = "NetCrackerProject")
public class QuestEntity {
    private Long id;
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

    @Id
    @SequenceGenerator(name="quest_sequence",sequenceName="entity_id_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="quest_sequence")
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        QuestEntity that = (QuestEntity) o;

        if (id != that.id) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (genre != null ? !genre.equals(that.genre) : that.genre != null) return false;
        if (version != null ? !version.equals(that.version) : that.version != null) return false;
        if (ageLimit != null ? !ageLimit.equals(that.ageLimit) : that.ageLimit != null) return false;
        if (rating != null ? !rating.equals(that.rating) : that.rating != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (genre != null ? genre.hashCode() : 0);
        result = 31 * result + (version != null ? version.hashCode() : 0);
        result = 31 * result + (ageLimit != null ? ageLimit.hashCode() : 0);
        result = 31 * result + (rating != null ? rating.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
