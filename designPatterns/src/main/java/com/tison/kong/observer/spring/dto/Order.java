package com.tison.kong.observer.spring.dto;

import lombok.Builder;
import lombok.Data;

/**
 * @author Tison
 * @date 2019-08-03
 * @description 订单实体类
 */
@Data
@Builder(toBuilder = true)
public class Order {

    /**
     * 订单ID
     */
    private String orderId;

    /**
     * 订单时间
     */
    private long orderTime;

}
