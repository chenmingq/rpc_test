package com.github.chenmingq.rpc.common.channel;

import java.util.List;

/**
 * @author : cmq
 * date : 2021/01
 * description :
 */

public class ChannelInitializerCommons<T extends BaseMessage> extends BaseChannelInitializer<BaseChannelHandler<T>> {

    public ChannelInitializerCommons(Class<? extends BaseEnCoder<? extends BaseMessage>> enCoder, Class<? extends BaseDeCoder<? extends BaseMessage>> deCoder, List<BaseChannelHandler<T>> handlerAdapter) {
        super(enCoder, deCoder, handlerAdapter);
    }

}
