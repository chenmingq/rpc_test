package com.github.chenmingq.rpc.common.channel;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

import java.util.List;

/**
 * @author : cmq
 * date : 2021/01
 * description :
 */

public abstract class BaseChannelInitializer<H extends BaseChannelHandler<? extends BaseMessage>> extends ChannelInitializer<SocketChannel> {

    private final Class<? extends BaseEnCoder<? extends BaseMessage>> enCoder;

    private final Class<? extends BaseDeCoder<? extends BaseMessage>> deCoder;

    private final List<H> handlerAdapter;

    public BaseChannelInitializer(Class<? extends BaseEnCoder<? extends BaseMessage>> enCoder, Class<? extends BaseDeCoder<? extends BaseMessage>> deCoder, List<H> handlerAdapter) {
        this.enCoder = enCoder;
        this.deCoder = deCoder;
        this.handlerAdapter = handlerAdapter;
    }

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline().addLast(enCoder.newInstance());
        ch.pipeline().addLast(deCoder.newInstance());
        handlerAdapter.forEach(h -> {
            ch.pipeline().addLast(h);
        });
    }
}
