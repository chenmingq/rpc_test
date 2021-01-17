package com.github.chenmingq.rpc.register.process.remote;

import com.github.chenmingq.rpc.common.channel.BaseMessage;
import com.github.chenmingq.rpc.common.channel.session.Session;
import com.github.chenmingq.rpc.common.constant.RoteConst;
import com.github.chenmingq.rpc.common.net.message.msg.provider.ProviderConnectRegisterMessage;
import com.github.chenmingq.rpc.common.net.message.msg.provider.RegisterResultProviderMessage;
import com.github.chenmingq.rpc.common.process.IProcess;
import com.github.chenmingq.rpc.common.transport.action.ConnectTarget;
import com.github.chenmingq.rpc.common.transport.entity.NodeEntity;
import com.github.chenmingq.rpc.common.util.RemotingUtil;
import com.github.chenmingq.rpc.register.manager.RegisterSessionManager;
import com.github.chenmingq.rpc.register.utils.NoticeUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * @author : cmq
 * date : 2021/01
 * description : 提供着连接至注册中心
 */

@Slf4j
public class ProviderConnectRegisterProcess implements IProcess {


    private Session session;

    private BaseMessage message;

    @Override
    public void executor(Session session, BaseMessage message) {
        this.session = session;
        this.message = message;
    }

    @Override
    public void run() {
        ProviderConnectRegisterMessage message = (ProviderConnectRegisterMessage) this.message;
        String host = RemotingUtil.socketAddress2Host(session.getChannel().remoteAddress());
        int node = message.getProto().getNode();
        int port = message.getProto().getPort();
        log.info("[{}]节点 [{}],地址[{}:{}]连接至注册中心", "PROVIDER", node, host, port);

        RegisterSessionManager.getInstance().addProviderAllSession(session, new NodeEntity(session, node, host, port));
        NoticeUtils.noticeConsumerAllProviderConnect();

        RegisterResultProviderMessage msg = new RegisterResultProviderMessage();
        msg.setType(ConnectTarget.REGISTER_TO_PROVIDER.getType());
        msg.setMagicId(RoteConst.CoderMagicConst.CODER_UNIQUE_MAGIC);
        this.session.sendMsg(msg);
    }
}
