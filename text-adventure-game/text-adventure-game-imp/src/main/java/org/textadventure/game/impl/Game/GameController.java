package org.textadventure.game.impl.Game;

import org.textadventure.game.impl.GameTransition;
import org.textadventure.game.impl.Node.GameNode;

import java.util.ArrayList;

/**
 * Created by Alexander on 25.12.2015.
 */
public class GameController {
    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    Game game;

    public GameNode fetchNode(int number)
    {
       return new GameNode();
    }

    public void setCurrentNode(GameNode gn)
    {
        game.setCurrentNode(gn);
    }

    public ArrayList<GameTransition> getAvailableTransitions()
    {
        return new ArrayList<GameTransition>();
    }

    public void implementActions()
    {

    }


}
