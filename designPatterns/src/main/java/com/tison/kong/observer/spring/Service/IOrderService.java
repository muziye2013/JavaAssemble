package com.tison.kong.observer.spring.Service;

import com.tison.kong.observer.spring.dto.Order;

/**
 * @author Tison
 * @date 2019-08-03
 * @description 订单服务类
 */
public interface IOrderService {

    /**
     * 下单
     * @param order
     */
    void placeOrder(Order order);
}
