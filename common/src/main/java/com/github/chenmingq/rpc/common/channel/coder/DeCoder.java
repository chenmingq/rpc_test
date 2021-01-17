package com.github.chenmingq.rpc.common.channel.coder;

import com.github.chenmingq.rpc.common.channel.BaseDeCoder;
import com.github.chenmingq.rpc.common.channel.BaseMessage;
import com.github.chenmingq.rpc.common.constant.RoteConst;
import com.github.chenmingq.rpc.common.net.message.pool.ProtoMessagePool;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

import java.util.List;

/**
 * @author : cmq
 * date : 2021/01
 * description :
 */

public class DeCoder extends BaseDeCoder<BaseMessage> {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        int magicId = in.readInt();
        if (RoteConst.CoderMagicConst.CODER_UNIQUE_MAGIC != magicId) {
            return;
        }
        int length = in.readInt();
        int type = in.readInt();
        byte[] body = new byte[length];
        in.readBytes(body);

        BaseMessage message = ProtoMessagePool.getCoderMessage(type);
        if (null == message) {
            return;
        }
        message.setBody(body);
        message.setMagicId(magicId);
        message.deCoder(body);
        message.setType(type);

        out.add(message);
    }
}
