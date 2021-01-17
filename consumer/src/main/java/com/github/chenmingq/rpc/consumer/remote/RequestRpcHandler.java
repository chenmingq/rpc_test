package com.github.chenmingq.rpc.consumer.remote;

import com.github.chenmingq.rpc.common.constant.RoteConst;
import com.github.chenmingq.rpc.common.net.message.RequestRpcMessage;
import com.github.chenmingq.rpc.common.remote.entity.RpcRequest;
import com.github.chenmingq.rpc.common.transport.action.ConnectTarget;
import com.github.chenmingq.rpc.consumer.queue.QueueFactory;
import com.github.chenmingq.rpc.consumer.remote.future.RpcFuture;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.UUID;

/**
 * @author : cmq
 * date : 2021/01
 * description :
 */

@Slf4j
public class RequestRpcHandler implements InvocationHandler {

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        RequestRpcMessage requestRpcMessage = buildRequest(method, args);
        RpcFuture rpcFuture = SessionManager.getInstance().sendRpcRequest(requestRpcMessage);
        // TODO 某个提供者服务被kill进程,会导致数据调用失败,先用队列临时保存一下,此处需要将调用的数据保存下来
        if (null == rpcFuture) {
            log.info("[{}] 消费失败 , 重新消费", requestRpcMessage.getRequest().getId());
            QueueFactory.WAIT_CONSUMER_QUEUE.put(requestRpcMessage);
            return null;
        }
        return rpcFuture.getRpcResponse().getResult();
    }

    private RequestRpcMessage buildRequest(Method method, Object[] args) {
        RpcRequest request = new RpcRequest();
        request.setParameterTypes(method.getParameterTypes());
        request.setId(UUID.randomUUID().toString());
        request.setArgs(args);
        request.setClassName(method.getDeclaringClass().getName());
        request.setMethodName(method.getName());

        RequestRpcMessage requestRpcMessage = new RequestRpcMessage();
        requestRpcMessage.setRequest(request);
        requestRpcMessage.setMagicId(RoteConst.CoderMagicConst.CODER_UNIQUE_MAGIC);
        requestRpcMessage.setType(ConnectTarget.CONSUMER_RPC_REQ_PROVIDER.getType());
        return requestRpcMessage;
    }
}
