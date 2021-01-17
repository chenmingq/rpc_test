package com.github.chenmingq.rpc.consumer.context;

import com.github.chenmingq.rpc.common.net.NetWorkOption;
import com.github.chenmingq.rpc.consumer.config.ConsumerConfig;
import com.github.chenmingq.rpc.consumer.remote.RequestRpcHandler;
import com.github.chenmingq.rpc.consumer.transport.ConsumerOptionImpl;

import java.lang.reflect.Proxy;

/**
 * @author : cmq
 * date : 2021/01
 * description :
 */

public class ApplicationContext {
    public <T> T getBean(Class<T> clazz) {
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, new RequestRpcHandler());
    }


    public void start() {
        ConsumerConfig.init();
        NetWorkOption netWorkOption = new ConsumerOptionImpl();
        netWorkOption.connect();
    }


}
