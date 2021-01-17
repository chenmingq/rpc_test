package com.github.chenmingq.rpc.consumer.transport;

import com.github.chenmingq.rpc.common.channel.BaseMessage;
import com.github.chenmingq.rpc.common.channel.ChannelInitializerCommons;
import com.github.chenmingq.rpc.common.channel.coder.DeCoder;
import com.github.chenmingq.rpc.common.channel.coder.EnCoder;
import com.github.chenmingq.rpc.common.channel.session.Address;
import com.github.chenmingq.rpc.common.channel.session.Session;
import com.github.chenmingq.rpc.common.constant.RoteConst;
import com.github.chenmingq.rpc.common.net.NetWorkOption;
import com.github.chenmingq.rpc.common.net.message.msg.consumer.ConsumerConnectRegisterMessage;
import com.github.chenmingq.rpc.common.transport.action.ConnectTarget;
import com.github.chenmingq.rpc.common.transport.options.OptionContext;
import com.github.chenmingq.rpc.common.transport.protocol.RpcTestProto;
import com.github.chenmingq.rpc.common.util.ExecutorsUtil;
import com.github.chenmingq.rpc.consumer.config.ConsumerConfig;
import com.github.chenmingq.rpc.consumer.handler.ConsumerHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author : cmq
 * date : 2021/01
 * description :
 */

@Slf4j
public class ConsumerOptionImpl implements NetWorkOption {

    @Override
    public void start(int port) {
    }

    @Override
    public void start(int port, TimeUnit unit, long value) {
    }

    @Override
    public void stop() {

    }

    @Override
    public void stop(TimeUnit unit, long value) {
        ExecutorsUtil.schedule(new Runnable() {
            @Override
            public void run() {
                stop();
            }
        }, value, unit);
    }

    @Override
    public void onEventState() {

    }

    @Override
    public Session connect(String ip, int port) {
        return OptionContext.getInstance().connect(ip, port,
                new ChannelInitializerCommons<>(EnCoder.class, DeCoder.class, Arrays.asList(new ConsumerHandler())));
    }

    @Override
    public Session connect() {
        String providerRegister = ConsumerConfig.CONSUMER_REGISTER;
        List<Address> providerRegisterAddressList = ConsumerConfig.CONSUMER_REGISTER_ADDRESS_LIST;
        if (null == providerRegisterAddressList) {
            return null;
        }
        if ("default".equals(providerRegister)) {
            for (Address address : providerRegisterAddressList) {
                sendConnectRegister(address.getIp(), address.getPort(), ConsumerConfig.CONSUMER_NODE);
            }
        }
        return null;
    }

    @Override
    public void onMessage(Session session, BaseMessage baseMessage) {

    }

    private void sendConnectRegister(String ip, int port, int nodeId) {
        Session session = connect(ip, port);
        if (null == session) {
            return;
        }
        ConsumerConnectRegisterMessage msg = new ConsumerConnectRegisterMessage();
        RpcTestProto.ReqConnectServerMessage.Builder proto = RpcTestProto.ReqConnectServerMessage.newBuilder();
        proto.setPort(ConsumerConfig.CONSUMER_PORT);
        proto.setNode(nodeId);

        msg.setProto(proto.build());
        msg.setType(ConnectTarget.CONSUMER_TO_REGISTER.getType());
        msg.setMagicId(RoteConst.CoderMagicConst.CODER_UNIQUE_MAGIC);

        session.sendMsg(msg);
    }
}
