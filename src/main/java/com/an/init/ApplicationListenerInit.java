package com.an.init;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * 在Spring中，默认对ApplicationEvent事件提供了如下支持:
 * - ContextStartedEvent：ApplicationContext启动后触发的事件
 * - ContextStoppedEvent：ApplicationContext停止后触发的事件
 * - ContextRefreshedEvent：ApplicationContext初始化或刷新完成后触发的事件；也就是容器初始化完成后调用。
 * - ContextClosedEvent：ApplicationContext关闭后触发的事件；如web容器关闭时自动会触发spring容器的关闭。
 */
@Component
public class ApplicationListenerInit implements ApplicationListener<ContextRefreshedEvent> {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
          //TODO
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
