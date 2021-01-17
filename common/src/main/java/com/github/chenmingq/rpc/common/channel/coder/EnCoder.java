package com.github.chenmingq.rpc.common.channel.coder;

import com.github.chenmingq.rpc.common.channel.BaseEnCoder;
import com.github.chenmingq.rpc.common.channel.BaseMessage;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author : cmq
 * date : 2021/01
 * description :
 */


public class EnCoder extends BaseEnCoder<BaseMessage> {

    @Override
    protected void encode(ChannelHandlerContext ctx, BaseMessage msg, ByteBuf out) throws Exception {
        byte[] bytes = msg.getBody();
        out
                .writeInt(msg.getMagicId())
                .writeInt(bytes.length)
                .writeInt(msg.getType())
                .writeBytes(bytes);
    }
}
