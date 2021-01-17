package com.github.chenmingq.rpc.common.net.message.pool;

import com.github.chenmingq.rpc.common.channel.BaseMessage;
import com.github.chenmingq.rpc.common.net.message.RequestRpcMessage;
import com.github.chenmingq.rpc.common.net.message.ResponseRpcMessage;
import com.github.chenmingq.rpc.common.net.message.msg.consumer.ConsumerConnectProviderMessage;
import com.github.chenmingq.rpc.common.net.message.msg.consumer.ConsumerConnectRegisterMessage;
import com.github.chenmingq.rpc.common.net.message.msg.consumer.ProviderConnectResultConsumerMessage;
import com.github.chenmingq.rpc.common.net.message.msg.consumer.RegisterResultConsumerMessage;
import com.github.chenmingq.rpc.common.net.message.msg.provider.ProviderConnectRegisterMessage;
import com.github.chenmingq.rpc.common.net.message.msg.provider.RegisterResultProviderMessage;
import com.github.chenmingq.rpc.common.transport.action.ConnectTarget;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : cmq
 * date : 2021/01
 * description : 消息协议注册
 */

public class ProtoMessagePool {

    private final static Map<Integer, BaseMessage> BASE_MESSAGE_MAP = new HashMap<>();


    static {
        registerCodeMsg();
    }


    private static void registerCodeMsg() {
        BASE_MESSAGE_MAP.put(ConnectTarget.CONSUMER_TO_REGISTER.getType(), new ConsumerConnectRegisterMessage());
        BASE_MESSAGE_MAP.put(ConnectTarget.REGISTER_TO_CONSUMER.getType(), new RegisterResultConsumerMessage());
        BASE_MESSAGE_MAP.put(ConnectTarget.PROVIDER_TO_REGISTER.getType(), new ProviderConnectRegisterMessage());
        BASE_MESSAGE_MAP.put(ConnectTarget.REGISTER_TO_PROVIDER.getType(), new RegisterResultProviderMessage());
        BASE_MESSAGE_MAP.put(ConnectTarget.CONSUMER_TO_PROVIDER.getType(), new ConsumerConnectProviderMessage());
        BASE_MESSAGE_MAP.put(ConnectTarget.PROVIDER_TO_CONSUMER.getType(), new ProviderConnectResultConsumerMessage());
        BASE_MESSAGE_MAP.put(ConnectTarget.CONSUMER_RPC_REQ_PROVIDER.getType(), new RequestRpcMessage());
        BASE_MESSAGE_MAP.put(ConnectTarget.PROVIDER_RPC_RES_CONSUMER.getType(), new ResponseRpcMessage());
    }


    public static BaseMessage getCoderMessage(int type) {
        return BASE_MESSAGE_MAP.get(type);
    }


}
