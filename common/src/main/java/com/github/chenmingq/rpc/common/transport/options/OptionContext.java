package com.github.chenmingq.rpc.common.transport.options;

import com.github.chenmingq.rpc.common.channel.BaseChannelHandler;
import com.github.chenmingq.rpc.common.channel.BaseChannelInitializer;
import com.github.chenmingq.rpc.common.channel.BaseMessage;
import com.github.chenmingq.rpc.common.channel.session.Session;

/**
 * @author : cmq
 * date : 2021/01
 * description :
 */

public class OptionContext {

    private static final OptionContext INSTANCE = new OptionContext();

    public static OptionContext getInstance() {
        return INSTANCE;
    }

    public OptionContext() {
    }

    /**
     * 初始服务
     *
     * @param port
     * @param initializer
     */
    public <T extends BaseMessage> Session initServer(int port, BaseChannelInitializer<BaseChannelHandler<T>> initializer) {
        return ServerOption.init(port, initializer);
    }

    /**
     * 连接
     *
     * @param ip
     * @param port
     * @param initializer
     * @return
     */
    public  <T extends BaseMessage> Session connect(String ip, int port, BaseChannelInitializer<BaseChannelHandler<T>> initializer) {
        return ClientOption.client(ip, port, initializer);
    }

}
