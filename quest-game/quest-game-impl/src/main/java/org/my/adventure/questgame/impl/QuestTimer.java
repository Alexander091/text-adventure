package org.my.adventure.questgame.impl;

/**
 * Created by Максим on 12.03.2016.
 */
public class QuestTimer {
    private int hours = 0;
    private int minutes = 0;
    private int seconds = 0;

    public void increment(){
        if(seconds==59) {
            seconds = 0;
            if (minutes == 59) {
                minutes = 0;
                hours++;
            } else {
                minutes++;
            }
        }else {
            seconds++;
        }
    }

    public String toString(){
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Время: ");
        stringBuffer.append(appendValue(hours));
        stringBuffer.append(":");
        stringBuffer.append(appendValue(minutes));
        stringBuffer.append(":");
        stringBuffer.append(appendValue(seconds));
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
