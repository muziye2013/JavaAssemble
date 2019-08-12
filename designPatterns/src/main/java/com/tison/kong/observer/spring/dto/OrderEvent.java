package com.tison.kong.observer.spring.dto;

import org.springframework.context.ApplicationEvent;

/**
 * @author Tison
 * @date 2019-08-03
 * @description 订单事件
 */
public class OrderEvent extends ApplicationEvent {

    private Order order;

    public OrderEvent(Object source, Order order) {
        super(source);
        this.order = order;
    }

    public Order getOrder(){
        return this.order;
    }

}
