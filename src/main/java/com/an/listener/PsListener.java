package com.an.listener;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class PsListener {

    @RabbitListener(queues = "ps_queue")
    public void psListener(Message message) {
        System.out.println("队列1: "+ new String(message.getBody()));
    }

    @RabbitListener(queues = "ps_queue02")
    public void ps02Listener(Message message) {
        System.out.println("队列2: "+ new String(message.getBody()));
    }

}

