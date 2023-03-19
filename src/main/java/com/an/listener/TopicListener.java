package com.an.listener;


import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class TopicListener {

    @RabbitListener(queues = "topic_queue")
    public void topicListener(Message message) {
        System.out.println("队列1: "+ new String(message.getBody()));
    }

    @RabbitListener(queues = "topic_queue02")
    public void topic02Listener(Message message) {
        System.out.println("队列2: "+ new String(message.getBody()));
    }

    @RabbitListener(queues = "topic_queue03")
    public void topic03Listener(Message message) {
        System.out.println("队列3: "+ new String(message.getBody()));
    }
}

