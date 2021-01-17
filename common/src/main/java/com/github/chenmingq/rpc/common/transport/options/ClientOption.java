package com.github.chenmingq.rpc.common.transport.options;

import com.github.chenmingq.rpc.common.channel.BaseChannelHandler;
import com.github.chenmingq.rpc.common.channel.BaseChannelInitializer;
import com.github.chenmingq.rpc.common.channel.BaseMessage;
import com.github.chenmingq.rpc.common.channel.session.Session;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author : cmq
 * date : 2021/01
 * description :
 */

public class ClientOption {


    /**
     * 客户端启动
     *
     * @param ip
     * @param port
     * @param channelInitializer
     * @return
     */
    protected static <T extends BaseMessage> Session client(String ip, int port, BaseChannelInitializer<BaseChannelHandler<T>> channelInitializer) {
        EventLoopGroup group = new NioEventLoopGroup();
        Session session = null;
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    // 保持连接
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    // 有数据立即发送
                    .option(ChannelOption.TCP_NODELAY, true)
                    // 绑定处理group
                    .remoteAddress(ip, port)
                    .handler(channelInitializer);

            // Make a new connection.
            ChannelFuture future = b.connect(ip, port).sync();
//            future.addListener(ChannelFutureListener.CLOSE);
            Channel channel = future.channel();

            session = new Session();
            session.setChannel(channel);
        } catch (Exception e) {
//            e.printStackTrace();
        }/* finally {
            group.shutdownGracefully();
        }*/
        return session;
    }
}
