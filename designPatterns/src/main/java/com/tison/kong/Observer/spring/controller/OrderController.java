package com.tison.kong.Observer.spring.controller;

import com.tison.kong.Observer.spring.Service.IOrderService;
import com.tison.kong.Observer.spring.dto.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @author Tison
 * @date 2019-08-03
 * @description
 */
@RestController
@Slf4j
public class OrderController {

    @Resource
    private IOrderService orderService;

    @GetMapping("/placeOrder")
    public String placeOrder() {
        //1.模拟生成订单
        Order order = Order.builder()
                .orderId(UUID.randomUUID().toString())
                .orderTime(System.currentTimeMillis())
                .build();
        log.info("get order，placing order:{}", order);
        orderService.placeOrder(order);
        return "success place order=" + order;
    }
}
