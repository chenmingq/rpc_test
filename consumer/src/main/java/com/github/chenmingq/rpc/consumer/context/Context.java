package com.github.chenmingq.rpc.consumer.context;

import com.github.chenmingq.rpc.api.IHelloWorld;
import lombok.extern.slf4j.Slf4j;

/**
 * @author : cmq
 * date : 2021/01
 * description :
 */

@Slf4j
public class Context implements Runnable {


    @Override
    public void run() {
        ApplicationContext context = new ApplicationContext();
        context.start();

        for (int i = 0; i < 30; i++) {
            try {
                Thread.sleep(1000);
                demo(context,i+" - rpc");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void demo(ApplicationContext context, String name) {
        IHelloWorld iHelloWorld = context.getBean(IHelloWorld.class);
        String s = iHelloWorld.sayHello(name);
        System.out.println(s);

        iHelloWorld.findFanFan(name);
    }

}
