package com.spring.amqp;


import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;

/**
 * author : liuqi
 * createTime : 2018-08-23
 * description : TODO
 * version : 1.0
 */
public class AmqpTest {
    public static void main(String[] args) throws InterruptedException {
        ConnectionFactory cf = new CachingConnectionFactory("127.0.0.1",5672);

        RabbitAdmin admin = new RabbitAdmin(cf);
        //创建队列
        Queue queue = new Queue("myQueue");
        admin.declareQueue(queue);
        //创建topic类型的交换机
        TopicExchange exchange = new TopicExchange("myExchange");
        admin.declareExchange(exchange);
        //交换机和队列绑定，路由规则为匹配"foo."开头的路由键
        admin.declareBinding(
                BindingBuilder.bind(queue).to(exchange).with("foo.*"));

        //设置监听
        SimpleMessageListenerContainer container =
                new SimpleMessageListenerContainer(cf);
        Object listener = new Object() {
            public void handleMessage(String foo) {
                System.out.println(" [x] Received '" + foo + "'");
            }
        };
        MessageListenerAdapter adapter = new MessageListenerAdapter(listener);
        container.setMessageListener(adapter);
        container.setQueueNames("myQueue");
        container.start();

        //发送消息
        RabbitTemplate template = new RabbitTemplate(cf);
        template.convertAndSend("myExchange", "foo.bar", "Hello, world!");
        Thread.sleep(1000);
        container.stop();
    }
}
