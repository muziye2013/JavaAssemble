package com.tison.kong.Observer.spring.listener;

import com.tison.kong.Observer.spring.dto.OrderEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author Tison
 * @date 2019-08-03
 * @description 提供E-mail服务的监听器
 */
@Component
@Slf4j
public class EMailListener {

    /**
     * 注册监听：处理订单事件
     * @param orderEvent
     */
    @EventListener
    @Async
    public void handlerOrderEvt(OrderEvent orderEvent){
        log.info("email-listener begin handle order:{}", orderEvent.getOrder());
        try {
            Thread.sleep(100L);
        } catch (InterruptedException e) {
            log.error("email handlerOrderEvt e:", e);
        }
        log.info("email-listener finished handle orderId:{}", orderEvent.getOrder().getOrderId());
    }

    /**
     * 处理其他事件如仓储、支付、物流都可以继续拓展
     * 如handleWarehouseEvt、handleWarehouseEvt等，只需要建立好事件对象，并注册监听
     */
}
