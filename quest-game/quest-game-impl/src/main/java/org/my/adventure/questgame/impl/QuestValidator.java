package org.my.adventure.questgame.impl;

import org.my.adventure.dao_manager.api.entities.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Максим on 14.02.2016.
 */
public class QuestValidator {
    public static String check(Node startNode, List<Node> nodes){
        String message="";
        if(startNode==null)
            message+="Стартовый этап не найден\n";
        List<String> endNodes = new ArrayList<>();
        for(Node node : nodes){
            if(node.getTransitions().isEmpty()){
                endNodes.add(node.getName());
            }
        }

        if(endNodes.isEmpty())
            message += "В созданном квесте нет финального этапа. Добавьте этап из которого нет переходов\n";
        else
            if(endNodes.size()>1){
                message += "Обратите внимание! В созданном квесте "+endNodes.size()+" финальных этапов:\n";
                for(int i = 0; i < endNodes.size()-1;i++)
                    message += endNodes.get(i)+", ";
                message += endNodes.get(endNodes.size()-1)+".\n";
            }
        return message;
    }
}
