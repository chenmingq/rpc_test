package com.github.chenmingq.rpc.api;

import com.github.chenmingq.rpc.common.annotation.RpcService;

/**
 * @author : cmq
 * date : 2021/01/08
 * description :
 */

@RpcService
public interface IHelloWorld {

    /**
     * helloWorld
     *
     * @param name
     * @return
     */
    String sayHello(String name);

    /**
     * noVoid
     *
     * @param name
     */
    void findFanFan(String name);
}
