package com.tison.kong.observer.jdk;

import java.util.Observable;
import java.util.Observer;

/**
 * @author Tison
 * @date 2019-08-03
 * @description 观察者类，需要实现Observer接口中的update()方法。
 */
public class NotifyReceiver implements Observer {

    private String name;

    public NotifyReceiver(String name){
        this.name = name;
    }

    @Override
    public void update(Observable observable, Object notify) {
        System.out.println("观察者["+name+"]已监测到来自["+observable.toString()+"]的更新："+notify);
    }
}
