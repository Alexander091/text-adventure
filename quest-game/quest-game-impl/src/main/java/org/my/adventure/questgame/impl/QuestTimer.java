package org.my.adventure.questgame.impl;

/**
 * Created by пїЅпїЅпїЅпїЅпїЅпїЅ on 12.03.2016.
 */
public class QuestTimer {
    private int hours = 0;
    private int minutes = 0;
    private int seconds = 0;

    public void increment(){
        if (minutes == 59) {
            minutes = 0;
            hours++;
        } else {
            minutes++;
        }
    }

    public String toString(){
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(appendValue(hours));
        stringBuffer.append("ч ");
        stringBuffer.append(appendValue(minutes));
        stringBuffer.append("мин");
        return stringBuffer.toString();
    }

    private String appendValue(int value){
        StringBuffer stringBuffer = new StringBuffer();
        if(value>9){
            stringBuffer.append(value);
        }else{
            stringBuffer.append(0);
            stringBuffer.append(value);
        }
        return stringBuffer.toString();
    }
}
