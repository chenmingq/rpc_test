package com.github.chenmingq.rpc.provider.process;

import com.github.chenmingq.rpc.common.channel.BaseMessage;
import com.github.chenmingq.rpc.common.net.message.RequestRpcMessage;
import com.github.chenmingq.rpc.common.net.message.msg.consumer.ConsumerConnectProviderMessage;
import com.github.chenmingq.rpc.common.net.message.msg.provider.RegisterResultProviderMessage;
import com.github.chenmingq.rpc.common.process.IProcess;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : cmq
 * date : 2021/01
 * description :
 */

public class ProcessPool {
    private static final Map<Class<? extends BaseMessage>, IProcess> PROCESS_MAP = new HashMap<>();

    static {
        PROCESS_MAP.put(RegisterResultProviderMessage.class, new RegisterResultProviderProcess());
        PROCESS_MAP.put(RequestRpcMessage.class, new ConsumerRpcProcess());
        PROCESS_MAP.put(ConsumerConnectProviderMessage.class, new ConsumerConnectProviderProcess());
    }


    public static IProcess getProcess(Class<? extends BaseMessage> msg) {
        return PROCESS_MAP.get(msg);
    }

}
