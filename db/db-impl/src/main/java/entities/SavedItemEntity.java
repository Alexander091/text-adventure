package entities;

import javax.persistence.*;

/**
 * Created by Дмитрий on 02.12.2015.
 */
@Entity
@Table(name = "saved_item", schema = "public", catalog = "netcracker")
public class SavedItemEntity extends CommonEntity{
    private GameEntity gameByGameId;
    private ItemEntity itemByItemId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SavedItemEntity that = (SavedItemEntity) o;

        if (id != that.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int)(long)id;
    }

    @ManyToOne
    @JoinColumn(name = "game_id", referencedColumnName = "id", nullable = false)
    public GameEntity getGameByGameId() {
        return gameByGameId;
    }

    public void setGameByGameId(GameEntity gameByGameId) {
        this.gameByGameId = gameByGameId;
    }

    @ManyToOne
    @JoinColumn(name = "item_id", referencedColumnName = "id", nullable = false)
    public ItemEntity getItemByItemId() {
        return itemByItemId;
    }

    public void setItemByItemId(ItemEntity itemByItemId) {
        this.itemByItemId = itemByItemId;
    }
}
