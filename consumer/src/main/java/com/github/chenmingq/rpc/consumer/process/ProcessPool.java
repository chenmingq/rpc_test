package com.github.chenmingq.rpc.consumer.process;

import com.github.chenmingq.rpc.common.channel.BaseMessage;
import com.github.chenmingq.rpc.common.net.message.ResponseRpcMessage;
import com.github.chenmingq.rpc.common.net.message.msg.consumer.ProviderConnectResultConsumerMessage;
import com.github.chenmingq.rpc.common.net.message.msg.consumer.RegisterResultConsumerMessage;
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
        PROCESS_MAP.put(RegisterResultConsumerMessage.class, new RegisterResultConsumerProcess());
        PROCESS_MAP.put(ResponseRpcMessage.class, new ConsumerRpcProcess());
        PROCESS_MAP.put(ProviderConnectResultConsumerMessage.class, new ProviderConnectResultConsumerProcess());
    }


    public static IProcess getProcess(Class<? extends BaseMessage> msg) {
        return PROCESS_MAP.get(msg);
    }

}
