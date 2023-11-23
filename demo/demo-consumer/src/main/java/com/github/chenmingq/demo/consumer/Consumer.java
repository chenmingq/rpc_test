package com.github.chenmingq.demo.consumer;

import com.github.chenmingq.demo.api.IHelloRpc;
import com.github.chenmingq.rpc.consumer.context.ApplicationContext;

/**
 * @author : cmq
 * date : 2021/1
 * description : 消费者启动
 */

public class Consumer {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ApplicationContext();
        applicationContext.start();

        for (int i = 0; i < 10000; i++) {
            IHelloRpc helloRpc = applicationContext.getBean(IHelloRpc.class);
            String sayHello = helloRpc.sayHello(i + " - 你好");
            System.out.println(sayHello);

            helloRpc.sayHi(i + " - Hello");
        }
    }
}
