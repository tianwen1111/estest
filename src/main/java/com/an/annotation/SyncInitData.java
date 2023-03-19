package com.an.annotation;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Component
public class SyncInitData implements ApplicationListener<ApplicationReadyEvent> {

    private static ThreadPoolExecutor threadPoolExcutors = new ThreadPoolExecutor(10,160,10L, TimeUnit.SECONDS,new LinkedBlockingQueue(100));

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationEvent) {

        ConfigurableApplicationContext applicationContext = applicationEvent.getApplicationContext();
        Map<String, AbstractInitializeBean> jobs = applicationContext.getBeansOfType(AbstractInitializeBean.class);
        jobs.forEach((jobName,job)->{
            threadPoolExcutors.execute(job);
        });
        threadPoolExcutors.shutdown();
    }
}
