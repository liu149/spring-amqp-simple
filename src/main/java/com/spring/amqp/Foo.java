package com.spring.amqp;

/**
 * author : liuqi
 * createTime : 2018-08-24
 * description : TODO
 * version : 1.0
 */
public class Foo {
    public void listen(String message) {
        System.out.println(" [x] Received '" + message + "'");
    }
}
