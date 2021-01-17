package com.github.chenmingq.rpc.provider.process;

import com.github.chenmingq.rpc.common.channel.BaseMessage;
import com.github.chenmingq.rpc.common.channel.session.Session;
import com.github.chenmingq.rpc.common.constant.RoteConst;
import com.github.chenmingq.rpc.common.net.message.msg.consumer.ConsumerConnectProviderMessage;
import com.github.chenmingq.rpc.common.net.message.msg.consumer.ProviderConnectResultConsumerMessage;
import com.github.chenmingq.rpc.common.process.IProcess;
import com.github.chenmingq.rpc.common.transport.action.ConnectTarget;
import lombok.extern.slf4j.Slf4j;

/**
 * @author : cmq
 * date : 2021/01
 * description : 消费者连接提供者
 */

@Slf4j
public class ConsumerConnectProviderProcess implements IProcess {

    private Session session;

    private BaseMessage message;

    @Override
    public void executor(Session session, BaseMessage message) {
        this.message = message;
        this.session = session;
    }

    @Override
    public void run() {
        ConsumerConnectProviderMessage msg = (ConsumerConnectProviderMessage) message;
        log.info("收到 [{}] 连接至提供者返回结果", "CONSUMER");
        ProviderConnectResultConsumerMessage message = new ProviderConnectResultConsumerMessage();
        message.setType(ConnectTarget.PROVIDER_TO_CONSUMER.getType());
        message.setMagicId(RoteConst.CoderMagicConst.CODER_UNIQUE_MAGIC);
        this.session.sendMsg(message);
    }
}
