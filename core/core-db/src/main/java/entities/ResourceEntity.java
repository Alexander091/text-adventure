package entities;

import javax.persistence.*;

/**
 * Created by ִלטענטי on 02.12.2015.
 */
@Entity
@Table(name = "Resource", schema = "public", catalog = "NetCrackerProject")
public class ResourceEntity {
    private int id;
    private String type;
    private String name;
    private String path;
    private QuestEntity questByQuestId;

    @Id
    @Column(name = "Id", nullable = false, insertable = true, updatable = true)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "Type", nullable = false, insertable = true, updatable = true, length = 2147483647)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "Name", nullable = false, insertable = true, updatable = true, length = 2147483647)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "Path", nullable = false, insertable = true, updatable = true, length = 2147483647)
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ResourceEntity that = (ResourceEntity) o;

        if (id != that.id) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (path != null ? !path.equals(that.path) : that.path != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (path != null ? path.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "QuestId", referencedColumnName = "Id", nullable = false)
    public QuestEntity getQuestByQuestId() {
        return questByQuestId;
    }

    public void setQuestByQuestId(QuestEntity questByQuestId) {
        this.questByQuestId = questByQuestId;
    }
}
