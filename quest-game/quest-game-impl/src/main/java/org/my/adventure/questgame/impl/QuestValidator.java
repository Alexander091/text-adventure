package org.my.adventure.questgame.impl;

import org.my.adventure.dao_manager.api.entities.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ������ on 14.02.2016.
 */
public class QuestValidator {
    public static String check(Node startNode, List<Node> nodes){
        String message="";
        if(startNode==null)
            message+="��������� ���� �� ������\n";
        List<String> endNodes = new ArrayList<>();
        for(Node node : nodes){
            if(node.getTransitions().isEmpty()){
                endNodes.add(node.getName());
            }
        }

        if(endNodes.isEmpty())
            message += "� ��������� ������ ��� ���������� �����. �������� ���� �� �������� ��� ���������\n";
        else
            if(endNodes.size()>1){
                message += "�������� ��������! � ��������� ������ "+endNodes.size()+" ��������� ������:\n";
                for(int i = 0; i < endNodes.size()-1;i++)
                    message += endNodes.get(i)+", ";
                message += endNodes.get(endNodes.size()-1)+".\n";
            }
        return message;
    }
}
