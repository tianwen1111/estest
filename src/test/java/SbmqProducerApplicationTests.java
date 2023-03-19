import com.an.ElasticSearchAppliction;
import com.an.config.RabbitConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes={ElasticSearchAppliction.class})
@RunWith(SpringRunner.class)
public class SbmqProducerApplicationTests {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void simpleQueue() {
        // 简单模式下，路由key 默认为队列名称,交换机为默认
        rabbitTemplate.convertAndSend(RabbitConfig.SIMPLE_QUEUE_NAME, "hello world");
        /**源码
         public void convertAndSend(String routingKey, Object object) throws AmqpException {
         this.convertAndSend(this.exchange, routingKey, object, (CorrelationData)null);
         }
         */
    }


    @Test
    public void psQueue() {
        rabbitTemplate.convertAndSend(RabbitConfig.FANOUT_EXCHANGE_NAME, "", "ps");
    }


    @Test
    public void topicQueue() {
        rabbitTemplate.convertAndSend(RabbitConfig.TOPIC_EXCHANGE_NAME, "log.info", "topic_info");// 队列3
        rabbitTemplate.convertAndSend(RabbitConfig.TOPIC_EXCHANGE_NAME, "log.error", "topic_error");// 队列2
        rabbitTemplate.convertAndSend(RabbitConfig.TOPIC_EXCHANGE_NAME, "order.error", "topic_order_error");// 队列1、队列2
    }

    @Test
    public void topicAckQueue() {
        //rabbitTemplate.setConfirmCallback();

    }

}
