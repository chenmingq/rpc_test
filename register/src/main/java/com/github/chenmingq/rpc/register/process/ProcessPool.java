package com.github.chenmingq.rpc.register.process;

import com.github.chenmingq.rpc.common.channel.BaseMessage;
import com.github.chenmingq.rpc.common.net.message.msg.consumer.ConsumerConnectRegisterMessage;
import com.github.chenmingq.rpc.common.net.message.msg.provider.ProviderConnectRegisterMessage;
import com.github.chenmingq.rpc.common.process.IProcess;
import com.github.chenmingq.rpc.register.process.remote.ConsumerConnectRegisterProcess;
import com.github.chenmingq.rpc.register.process.remote.ProviderConnectRegisterProcess;

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
        PROCESS_MAP.put(ConsumerConnectRegisterMessage.class, new ConsumerConnectRegisterProcess());
        PROCESS_MAP.put(ProviderConnectRegisterMessage.class, new ProviderConnectRegisterProcess());
    }


    public static IProcess getProcess(Class<? extends BaseMessage> msg) {
        return PROCESS_MAP.get(msg);
    }
}
