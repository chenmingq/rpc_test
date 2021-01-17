package com.github.chenmingq.rpc.register.process.remote;

import com.github.chenmingq.rpc.common.channel.BaseMessage;
import com.github.chenmingq.rpc.common.channel.session.Session;
import com.github.chenmingq.rpc.common.net.message.msg.consumer.ConsumerConnectRegisterMessage;
import com.github.chenmingq.rpc.common.process.IProcess;
import com.github.chenmingq.rpc.common.transport.entity.NodeEntity;
import com.github.chenmingq.rpc.common.util.RemotingUtil;
import com.github.chenmingq.rpc.register.manager.RegisterSessionManager;
import com.github.chenmingq.rpc.register.utils.NoticeUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * @author : cmq
 * date : 2021/01
 * description : 消费者连接到注册中心
 */

@Slf4j
public class ConsumerConnectRegisterProcess implements IProcess {

    private Session session;

    private BaseMessage message;

    @Override
    public void executor(Session session, BaseMessage message) {
        this.session = session;
        this.message = message;
    }

    @Override
    public void run() {
        ConsumerConnectRegisterMessage message = (ConsumerConnectRegisterMessage) this.message;
        String host = RemotingUtil.socketAddress2Host(session.getChannel().remoteAddress());
        int node = message.getProto().getNode();
        int port = message.getProto().getPort();
        RegisterSessionManager.getInstance().addConsumerAllSession(session, new NodeEntity(session, node, host, port));

        log.info("[{}]节点 [{}],地址[{}:{}]连接至注册中心", "CONSUMER", node, host, port);
        NoticeUtils.noticeConsumerAllProviderConnect(this.session);
    }
}
