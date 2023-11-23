package com.github.chenmingq.demo.provider.impl;

import com.github.chenmingq.demo.api.IHelloRpc;
import com.github.chenmingq.rpc.common.annotation.RpcService;

/**
 * @author : cmq
 * date : 2021/01
 * description :
 */

@RpcService
public class HelloRpcImpl implements IHelloRpc {
    @Override
    public void sayHi(String name) {
        System.out.println("HelloRpcImpl.sayHi:" + name);
    }

    @Override
    public String sayHello(String name) {
        return "RPC : " + name;
    }
}
