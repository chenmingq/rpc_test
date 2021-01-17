package com.github.chenmingq.rpc.provider.handler;

import com.github.chenmingq.rpc.common.channel.BaseChannelHandler;
import com.github.chenmingq.rpc.common.channel.BaseMessage;
import com.github.chenmingq.rpc.common.channel.session.AttributeUtil;
import com.github.chenmingq.rpc.common.channel.session.ChannelAttrKey;
import com.github.chenmingq.rpc.common.channel.session.Session;
import com.github.chenmingq.rpc.common.process.IProcess;
import com.github.chenmingq.rpc.common.util.ExecutorsUtil;
import com.github.chenmingq.rpc.common.util.RemotingHelper;
import com.github.chenmingq.rpc.common.util.RemotingUtil;
import com.github.chenmingq.rpc.provider.process.ProcessPool;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.extern.slf4j.Slf4j;

/**
 * @author : cmq
 * date : 2021/01
 * description : 提供者服务,对消费者提供服务
 */

@Slf4j
@ChannelHandler.Sharable
public class ProviderHandler extends BaseChannelHandler<BaseMessage> {

//    @Override
//    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//        log.info("新的连接加入");
//    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, BaseMessage message) throws Exception {
        IProcess process = ProcessPool.getProcess(message.getClass());
        if (null == process) {
            return;
        }
        Session session = new Session(ctx.channel());
        process.executor(session, message);
        AttributeUtil.set(ctx.channel(), ChannelAttrKey.SESSION, session);
        ExecutorsUtil.execute(process);
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        if (this == ctx.pipeline().last()) {
            cause.printStackTrace();
            ctx.close();
        }
//        super.exceptionCaught(ctx, cause);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        super.channelReadComplete(ctx);
    }


    /**
     * 收到客户端断开
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        super.handlerRemoved(ctx);
        Session session = AttributeUtil.get(ctx.channel(), ChannelAttrKey.SESSION);
        if (null == session) {
            return;
        }
        AttributeUtil.remove(ctx.channel(), ChannelAttrKey.SESSION);
        // TODO 移除连接
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt;
            if (event.state().equals(IdleState.ALL_IDLE)) {
                final String remoteAddress = RemotingHelper.parseChannelRemoteAddr(ctx.channel());
                log.warn("NETTY SERVER PIPELINE: IDLE exception [{}]", remoteAddress);
                RemotingUtil.closeChannel(ctx.channel());
            }
        }
        ctx.fireUserEventTriggered(evt);
    }

}
