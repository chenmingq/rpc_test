package com.github.chenmingq.rpc.common.channel;

import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author : cmq
 * date : 2021/01
 * description :
 */

public abstract class BaseChannelHandler<T extends BaseMessage> extends SimpleChannelInboundHandler<T> {
}
