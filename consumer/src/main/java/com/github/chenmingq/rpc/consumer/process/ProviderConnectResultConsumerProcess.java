package com.github.chenmingq.rpc.consumer.process;

import com.github.chenmingq.rpc.common.channel.BaseMessage;
import com.github.chenmingq.rpc.common.channel.session.Session;
import com.github.chenmingq.rpc.common.net.message.msg.consumer.ProviderConnectResultConsumerMessage;
import com.github.chenmingq.rpc.common.process.IProcess;
import lombok.extern.slf4j.Slf4j;

/**
 * @author : cmq
 * date : 2021/01
 * description : 提供则返回消费者的连接结果
 */

@Slf4j
public class ProviderConnectResultConsumerProcess implements IProcess {

    private Session session;

    private BaseMessage message;

    @Override
    public void executor(Session session, BaseMessage message) {
        this.session = session;
        this.message = message;
    }

    @Override
    public void run() {
        ProviderConnectResultConsumerMessage message = (ProviderConnectResultConsumerMessage) this.message;
        log.info("CONSUMER 与提供者连接成功");
    }
}
