package com.github.chenmingq.rpc.consumer.remote.future;

import com.github.chenmingq.rpc.common.remote.entity.RpcResponse;

/**
 * @author : cmq
 * date : 2020/12
 * description : rpc回调的对象
 */

public class RpcFuture {

    private RpcResponse rpcResponse;

    public RpcResponse getRpcResponse() {
        return rpcResponse;
    }

    public void setRpcResponse(RpcResponse rpcResponse) {
        this.rpcResponse = rpcResponse;
    }

    @Override
    public String toString() {
        return "RpcFuture{" +
                "rpcResponse=" + rpcResponse +
                '}';
    }
}
