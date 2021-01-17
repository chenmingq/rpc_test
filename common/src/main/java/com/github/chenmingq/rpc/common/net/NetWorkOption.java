package com.github.chenmingq.rpc.common.net;

import com.github.chenmingq.rpc.common.channel.BaseMessage;
import com.github.chenmingq.rpc.common.channel.session.Session;

import java.util.concurrent.TimeUnit;

/**
 * @author : cmq
 * date : 2021/01
 * description :
 */

public interface NetWorkOption {

    /**
     * 启动
     *
     * @param port
     */
    void start(int port);

    /**
     * 延迟启动
     *
     * @param port
     * @param unit
     * @param value
     */
    void start(int port, TimeUnit unit, long value);

    /**
     * 关闭
     */
    void stop();

    /**
     * 延迟关闭
     *
     * @param unit
     * @param value
     */
    void stop(TimeUnit unit, long value);

    /**
     * 监听连接状态
     */
    void onEventState();

    /**
     * 连接
     *
     * @param ip
     * @param port
     * @return Session
     */
    Session connect(String ip, int port);

    /**
     * 连接
     *
     * @return
     */
    Session connect();


    /**
     * 接受消息
     *
     * @param session
     * @param baseMessage
     */
    void onMessage(Session session, BaseMessage baseMessage);

}
