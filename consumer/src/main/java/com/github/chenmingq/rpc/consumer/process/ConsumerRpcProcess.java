package com.github.chenmingq.rpc.consumer.process;

import com.github.chenmingq.rpc.common.channel.BaseMessage;
import com.github.chenmingq.rpc.common.channel.session.Session;
import com.github.chenmingq.rpc.common.net.message.ResponseRpcMessage;
import com.github.chenmingq.rpc.common.process.IProcess;
import com.github.chenmingq.rpc.common.remote.entity.RpcResponse;
import com.github.chenmingq.rpc.consumer.remote.future.FutureManager;
import com.github.chenmingq.rpc.consumer.remote.future.RpcFuture;

/**
 * @author : cmq
 * date : 2021/01
 * description : rpc处理
 */

public class ConsumerRpcProcess implements IProcess {

    private Session session;

    private BaseMessage message;

    @Override
    public void executor(Session session, BaseMessage message) {
        this.message = message;
        this.session = session;
    }

    @Override
    public void run() {
        ResponseRpcMessage responseRpcMessage = (ResponseRpcMessage) this.message;
        RpcResponse response = responseRpcMessage.getResponse();

        RpcFuture rpcFuture = new RpcFuture();
        rpcFuture.setRpcResponse(response);
        FutureManager.getInstance().put(response.getId(), rpcFuture);
    }
}
