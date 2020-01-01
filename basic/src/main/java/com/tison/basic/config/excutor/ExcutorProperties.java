package com.tison.basic.config.excutor;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author tison
 * @date 2019/10/21
 * @description 线程池配置实体
 */
@Data
@Component
@ConfigurationProperties(prefix="excutor")
public class ExcutorProperties {

    private int corePoolSize = 5;

    private int maxPoolSize = 10;

    private int queueCapacity = 64;
}
