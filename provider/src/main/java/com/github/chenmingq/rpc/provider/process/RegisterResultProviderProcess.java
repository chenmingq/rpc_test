package com.github.chenmingq.rpc.provider.process;

import com.github.chenmingq.rpc.common.channel.BaseMessage;
import com.github.chenmingq.rpc.common.channel.session.ProviderSessionManager;
import com.github.chenmingq.rpc.common.channel.session.Session;
import com.github.chenmingq.rpc.common.process.IProcess;
import lombok.extern.slf4j.Slf4j;

/**
 * @author : cmq
 * date : 2021/01
 * description : 注册中心返回连接结果
 */

@Slf4j
public class RegisterResultProviderProcess implements IProcess {


    private Session session;

    private BaseMessage message;

    @Override
    public void run() {
        log.info("provider 连接注册中心上报成功");
        ProviderSessionManager.getInstance().addRegisterSession(session);
    }

    @Override
    public void executor(Session session, BaseMessage baseMessage) {
        this.session = session;
        this.message = baseMessage;
    }
}
