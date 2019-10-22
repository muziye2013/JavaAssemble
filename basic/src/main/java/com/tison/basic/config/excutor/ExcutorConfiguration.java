package com.tison.basic.config.excutor;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author tison
 * @date 2019/10/22
 * @description 配置类注入容器
 */
@Configuration
public class ExcutorConfiguration {

    @Bean
    public ExcutorProperties excutorProperties() {
        return new ExcutorProperties();
    }
}
