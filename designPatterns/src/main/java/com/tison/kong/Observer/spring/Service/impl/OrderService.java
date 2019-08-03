package com.tison.kong.Observer.spring.Service.impl;

import com.tison.kong.Observer.spring.Service.IOrderService;
import com.tison.kong.Observer.spring.dto.Order;
import com.tison.kong.Observer.spring.dto.OrderEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @author Tison
 * @date 2019-08-03
 * @description 订单服务类
 */
@Service
@Slf4j
public class OrderService implements IOrderService {

    @Resource
    private ApplicationContext context;

    /**
     * 下单请求
     * @param order
     */
    @Override
    public void placeOrder(Order order) {
        //1.模拟订单处理
        try {
            Thread.sleep(100L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("ordering finished, begin send message");

        //2.处理完毕，发布订单完成事件
        OrderEvent orderEvent = new OrderEvent(this, order);
        context.publishEvent(orderEvent);
    }
}
