package com.spring.amqp;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * author : liuqi
 * createTime : 2018-08-24
 * description : TODO
 * version : 1.0
 */
public class SpringMain {
    public static void main(final String... args) throws Exception {

        AbstractApplicationContext ctx =
                new ClassPathXmlApplicationContext("spring-amqp.xml");
        RabbitTemplate template = ctx.getBean(RabbitTemplate.class);
//        template.send(new Message());
        template.convertAndSend("Hello, world!");
        Thread.sleep(1000);
        ctx.destroy();
    }
}
