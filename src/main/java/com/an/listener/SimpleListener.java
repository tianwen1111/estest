package com.an.listener;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class SimpleListener {

    @RabbitListener(queues = "simple_queue")
    public void simpleListener(Message message) {
        System.out.println(message);
        System.out.println(new String(message.getBody()));
    }
}

