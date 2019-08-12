package com.tison.kong.observer.jdk;

import java.util.Observable;

/**
 * @author Tison
 * @date 2019-08-03
 * @description 被观察者，同时也是通知发送者
 */
public class NotifySender extends Observable {

    private String name;

    public NotifySender(String name){
        this.name = name;
    }

    @Override
    public String toString(){
        return this.name;
    }

    /**
     * @param notify 通知内容
     */
    public void sendNotify(String notify){
        //标识这个Observable对象已经改变了，更具体来将就是把Observable中属性changed置为true.
        setChanged();
        //在通知所有观察者之前，需要判断Observable中属性changed是否为true，如若不为则不会发出通知。
        notifyObservers(notify);
    }
}
