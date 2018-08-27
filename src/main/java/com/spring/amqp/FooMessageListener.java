package com.spring.amqp;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Component;

/**
 * author : liuqi
 * createTime : 2018-08-24
 * description : TODO
 * version : 1.0
 */
@Component
public class FooMessageListener implements MessageListener {
    @Override
    public void onMessage(Message message) {
        String messageBody = new String(message.getBody());
        System.out.println(" [x] Received '" + messageBody + "'");
    }
}
