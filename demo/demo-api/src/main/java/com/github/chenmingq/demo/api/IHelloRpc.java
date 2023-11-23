package com.github.chenmingq.demo.api;


import com.github.chenmingq.rpc.common.annotation.RpcService;

/**
 * @author : cmq
 * date : 2021/1
 * description :
 */


@RpcService
public interface IHelloRpc {

    /**
     * 没有返回值的调用
     *
     * @param name
     */
    void sayHi(String name);

    /**
     * 有返回值的调用
     *
     * @param name
     * @return
     */
    String sayHello(String name);

}
