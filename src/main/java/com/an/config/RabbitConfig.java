package com.an.config;



import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    public static final String SIMPLE_QUEUE_NAME = "simple_queue";

    @Bean
    public Queue simpleQueue() {
        /**
         durable(): 是否持久化,当mq 重启之后还在
         exclusive(): 是否独占:只能有一个消费者监听这个队列,当Connection 关闭时,是否删除队列
         autoDelete(): 是否自动删除,当没有Consumer 监听时,自动删除
         withArgument(): 参数
         */
        return QueueBuilder.durable(SIMPLE_QUEUE_NAME).build();
    }
//////////////////////////////////////////////////////////////////////////////////////////

    public static final String FANOUT_EXCHANGE_NAME = "fanout_exchange";

    public static final String PS_QUEUE_NAME = "ps_queue";
    public static final String PS_QUEUE_NAME02 = "ps_queue02";

    @Bean("fanout_exchange")
    public Exchange fanoutExchange() {
        /**
         * 交换机类型:
         * DIRECT: 定向
         * FANOUT: 扇形(广播):发送消费给每一个与之绑定的队列
         * TOPIC: 通配符
         * HEADERS: 参数
         *
         * durable: 是否持久化
         * autoDelete: 自动删除
         * internal: 内部使用
         * arguments: 参数
         */
        return ExchangeBuilder.fanoutExchange(FANOUT_EXCHANGE_NAME).durable(true).build();
    }

    @Bean("ps_queue")
    public Queue psQueue01() {
        return QueueBuilder.durable(PS_QUEUE_NAME).build();
    }
    @Bean("ps_queue02")
    public Queue psQueue02() {
        return QueueBuilder.durable(PS_QUEUE_NAME02).build();
    }

    @Bean
    public Binding bindQueueExchange01(@Qualifier("ps_queue") Queue queue, @Qualifier("fanout_exchange") Exchange exchange) {
        // 如果交换机类型为fanout, routingKey(路由键,绑定规则) 设置为""
        return BindingBuilder.bind(queue).to(exchange).with("").noargs();
    }
    @Bean
    public Binding bindQueueExchange02(@Qualifier("ps_queue02") Queue queue, @Qualifier("fanout_exchange") Exchange exchange) {
        // 如果交换机类型为fanout, routingKey(路由键,绑定规则) 设置为""
        return BindingBuilder.bind(queue).to(exchange).with("").noargs();
    }

    ////////////////////////////////////////////////////////////////////////////////////////
    public static final String TOPIC_EXCHANGE_NAME = "topic_exchange";

    public static final String TOPIC_QUEUE_NAME = "topic_queue";
    public static final String TOPIC_QUEUE_NAME02 = "topic_queue02";
    public static final String TOPIC_QUEUE_NAME03 = "topic_queue03";

    @Bean("topic_exchange")
    public Exchange topicExchange() {
        return ExchangeBuilder.topicExchange(TOPIC_EXCHANGE_NAME).durable(true).build();
    }

    @Bean("topic_queue")
    public Queue topicQueue() {
        return QueueBuilder.durable(TOPIC_QUEUE_NAME).build();
    }
    @Bean("topic_queue02")
    public Queue topicQueue02() {
        return QueueBuilder.durable(TOPIC_QUEUE_NAME02).build();
    }
    @Bean("topic_queue03")
    public Queue topicQueue03() {
        return QueueBuilder.durable(TOPIC_QUEUE_NAME03).build();
    }

    @Bean
    public Binding bindQueueExchange05(@Qualifier("topic_queue") Queue queue, @Qualifier("topic_exchange") Exchange exchange) {
        //*: 代表单个单词;  #: 代表单个或多个单词
        return BindingBuilder.bind(queue).to(exchange).with("order.*").noargs();
    }
    @Bean
    public Binding bindQueueExchange06(@Qualifier("topic_queue02") Queue queue, @Qualifier("topic_exchange") Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("#.error").noargs();
    }

    @Bean
    public Binding bindQueueExchange07(@Qualifier("topic_queue03") Queue queue, @Qualifier("topic_exchange") Exchange exchange) {
        //*: 代表单个单词;  #: 代表单个或多个单词
        return BindingBuilder.bind(queue).to(exchange).with("*.info").noargs();
    }

}
