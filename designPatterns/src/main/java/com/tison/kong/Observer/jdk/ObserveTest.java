package com.tison.kong.Observer.jdk;

import java.util.Scanner;

/**
 * @author Tison
 * @date 2019-08-03
 * @description 观察者模式测试执行
 */
public class ObserveTest {

    public static void main(String[] args) {

        //定义被观察者
        NotifySender sender = new NotifySender("Sender");

        //定义三个观察者
        NotifyReceiver receiver1 = new NotifyReceiver("NO.1");
        NotifyReceiver receiver2 = new NotifyReceiver("NO.2");
        NotifyReceiver receiver3 = new NotifyReceiver("NO.3");

        //观察者注册关联
        sender.addObserver(receiver1);
        sender.addObserver(receiver2);
        sender.addObserver(receiver3);

        System.out.println("已成功注册关联，receiver可以接收到sender的变更推送！");

        Scanner scanner = new Scanner(System.in);
        //循环监听并推送
        while(true){
            String notify = scanner.nextLine();
            sender.sendNotify(notify);
        }
    }
}
