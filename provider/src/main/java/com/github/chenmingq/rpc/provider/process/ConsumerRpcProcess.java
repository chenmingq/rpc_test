package com.github.chenmingq.rpc.provider.process;

import com.github.chenmingq.rpc.common.channel.BaseMessage;
import com.github.chenmingq.rpc.common.channel.session.Session;
import com.github.chenmingq.rpc.common.constant.RoteConst;
import com.github.chenmingq.rpc.common.net.message.RequestRpcMessage;
import com.github.chenmingq.rpc.common.net.message.ResponseRpcMessage;
import com.github.chenmingq.rpc.common.process.IProcess;
import com.github.chenmingq.rpc.common.remote.entity.RpcRequest;
import com.github.chenmingq.rpc.common.remote.entity.RpcResponse;
import com.github.chenmingq.rpc.common.transport.action.ConnectTarget;
import com.github.chenmingq.rpc.provider.config.RpcServiceImplConfig;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;

/**
 * @author : cmq
 * date : 2021/01
 * description :
 */

@Slf4j
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
        RequestRpcMessage requestRpcMessage = (RequestRpcMessage) this.message;
        RpcRequest rpcRequest = requestRpcMessage.getRequest();

        String rpcRequestId = rpcRequest.getId();
        if (null == rpcRequestId) {
            return;
        }
        String className = rpcRequest.getClassName();

        RpcResponse rpcResponse = null;
        try {
            Class<?> rpcRequestClazz = Class.forName(className);
            Class<?> serviceImplClass = RpcServiceImplConfig.getInstance().getServiceImplClass(rpcRequestClazz);
            if (null == serviceImplClass) {
                return;
            }

            Object[] args = rpcRequest.getArgs();
            Class<?>[] parameterTypes = rpcRequest.getParameterTypes();
            String methodName = rpcRequest.getMethodName();

            Method method = rpcRequestClazz.getMethod(methodName, parameterTypes);
            Object invoke = method.invoke(serviceImplClass.newInstance(), args);

            rpcResponse = new RpcResponse();
            rpcResponse.setId(rpcRequestId);
            rpcResponse.setResult(invoke);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (null == rpcResponse) {
            return;
        }
        ResponseRpcMessage response = new ResponseRpcMessage();
        response.setType(ConnectTarget.PROVIDER_RPC_RES_CONSUMER.getType());
        response.setMagicId(RoteConst.CoderMagicConst.CODER_UNIQUE_MAGIC);
        response.setResponse(rpcResponse);
        this.session.sendMsg(response);
    }
}
