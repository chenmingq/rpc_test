package com.github.chenmingq.rpc.common.transport.options;

import com.github.chenmingq.rpc.common.channel.BaseChannelHandler;
import com.github.chenmingq.rpc.common.channel.BaseChannelInitializer;
import com.github.chenmingq.rpc.common.channel.BaseMessage;
import com.github.chenmingq.rpc.common.channel.session.Session;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.ServerSocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;

/**
 * @author : cmq
 * date : 2021/01
 * description :
 */

@Slf4j
public class ServerOption {


    protected static <T extends BaseMessage> Session init(int port, BaseChannelInitializer<BaseChannelHandler<T>> channelInitializer) {
        if (port < 1) {
            throw new RuntimeException("端口格式有误");
        }
        Session session = null;

        ServerBootstrap serverBootstrap = new ServerBootstrap();

        EventLoopGroup workExecutors = new NioEventLoopGroup();
        EventLoopGroup bossExecutors = new NioEventLoopGroup();

        serverBootstrap.group(bossExecutors, workExecutors)
                .channel(useSocketChannel())
                .childHandler(channelInitializer)
                //BACKLOG大小
                .option(ChannelOption.SO_BACKLOG, 1024)
                .option(ChannelOption.SO_REUSEADDR, true)
                //有数据立即发送
                .childOption(ChannelOption.TCP_NODELAY, true)
                .localAddress(new InetSocketAddress(port));

        serverBootstrap.childOption(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);
        try {
            ChannelFuture future = serverBootstrap.bind().sync();
//            future.addListener(ChannelFutureListener.CLOSE);
            Channel channel = future.channel();
            session = new Session();
            if (future.isSuccess()) {
                session.setChannel(channel);
            }
        } catch (InterruptedException e) {
            log.error("exception {}", e.getMessage());
        }

        return session;
    }


    private static Class<? extends ServerSocketChannel> useSocketChannel() {
        return NioServerSocketChannel.class;
    }

}
