package com.github.chenmingq.rpc.provider.demo;

import com.github.chenmingq.rpc.api.IHelloWorld;
import com.github.chenmingq.rpc.common.annotation.RpcService;

/**
 * @author : chengmingqin
 * date : 2021/1/17
 * description :
 */

@RpcService
public class HelloWorldImpl implements IHelloWorld {
    @Override
    public String sayHello(String name) {
        return "你好 - " + name;
    }

    @Override
    public void findFanFan(String name) {
        System.out.println(name);
    }
}
