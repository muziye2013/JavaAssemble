package com.tison.basic.task;

import com.tison.basic.config.excutor.ExcutorProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author tison
 * @date 2019/11/6
 * @description
 */
@Slf4j
@Component
public class SchedulerService {

    @Autowired
    private ExcutorProperties excutorProperties;

    @Scheduled(cron = "0/5 * * * * *")
    public void scheduled(){
        log.info("corePoolSize={}", excutorProperties.getCorePoolSize());
    }

}
