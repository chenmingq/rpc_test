package com.github.chenmingq.rpc.register;

import com.github.chenmingq.rpc.common.channel.BaseChannelHandler;
import com.github.chenmingq.rpc.common.channel.BaseMessage;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author : cmq
 * date : 2021/01/05
 * description :
 */

@ChannelHandler.Sharable
public class ClientHandler extends BaseChannelHandler<BaseMessage> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, BaseMessage msg) throws Exception {
        System.out.println(msg);
    }
}
