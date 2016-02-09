package org.textadventure.game.impl.Game;

import org.textadventure.game.impl.Node.GameNode;
import org.textadventure.game.impl.Player;

/**
 * Created by Alexander on 25.12.2015.
 */
public class Game {
    Player player;
    GameNode currentNode;

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public GameNode getCurrentNode() {
        return currentNode;
    }

    public void setCurrentNode(GameNode currentNode) {
        this.currentNode = currentNode;
    }
}
