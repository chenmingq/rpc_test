package com.github.chenmingq.rpc.provider.transport;

import com.github.chenmingq.rpc.common.channel.BaseMessage;
import com.github.chenmingq.rpc.common.channel.ChannelInitializerCommons;
import com.github.chenmingq.rpc.common.channel.coder.DeCoder;
import com.github.chenmingq.rpc.common.channel.coder.EnCoder;
import com.github.chenmingq.rpc.common.channel.session.Address;
import com.github.chenmingq.rpc.common.channel.session.Session;
import com.github.chenmingq.rpc.common.constant.RoteConst;
import com.github.chenmingq.rpc.common.net.NetWorkOption;
import com.github.chenmingq.rpc.common.net.message.msg.provider.ProviderConnectRegisterMessage;
import com.github.chenmingq.rpc.common.transport.action.ConnectTarget;
import com.github.chenmingq.rpc.common.transport.options.OptionContext;
import com.github.chenmingq.rpc.common.transport.protocol.RpcTestProto;
import com.github.chenmingq.rpc.common.util.ExecutorsUtil;
import com.github.chenmingq.rpc.common.util.Util;
import com.github.chenmingq.rpc.provider.config.ProviderConfig;
import com.github.chenmingq.rpc.provider.handler.ProviderHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @author : cmq
 * date : 2021/01
 * description :
 */

@Slf4j
public class ProviderOptionImpl implements NetWorkOption {

    @Override
    public void start(int port) {
        Future<Session> submit = ExecutorsUtil.submit(new Callable<Session>() {
            @Override
            public Session call() throws Exception {
                return OptionContext.getInstance().initServer(port, new ChannelInitializerCommons<>(EnCoder.class, DeCoder.class, Arrays.asList(new ProviderHandler())));
            }
        });
        try {
            Session session = submit.get();
            if (null != session) {
                log.info("提供者服务 [{}] 启动成功 PID - {} , 端口 - {}", "PROVIDER", Util.fetchProcessId(), port);
            } else {
                log.error("提供者服务启动失败 [{}]", "PROVIDER");
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    @Override
    public void start(int port, TimeUnit unit, long value) {
        ExecutorsUtil.schedule(new Runnable() {
            @Override
            public void run() {
                start(port);
            }
        }, value, unit);
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
                new ChannelInitializerCommons<>(EnCoder.class, DeCoder.class, Arrays.asList(new ProviderHandler())));
    }

    @Override
    public Session connect() {
        String providerRegister = ProviderConfig.PROVIDER_REGISTER;
        List<Address> providerRegisterAddressList = ProviderConfig.PROVIDER_REGISTER_ADDRESS_LIST;
        if (null == providerRegisterAddressList) {
            return null;
        }
        if ("default".equals(providerRegister)) {
            for (Address address : providerRegisterAddressList) {
                sendConnectRegister(address.getIp(), address.getPort());
            }
        }
        return null;
    }

    @Override
    public void onMessage(Session session, BaseMessage baseMessage) {

    }

    private void sendConnectRegister(String ip, int port) {
        Session session = connect(ip, port);
        if (null == session) {
            return;
        }

        ProviderConnectRegisterMessage msg = new ProviderConnectRegisterMessage();
        RpcTestProto.ReqConnectServerMessage.Builder proto = RpcTestProto.ReqConnectServerMessage.newBuilder();
        proto.setPort(ProviderConfig.PROVIDER_PORT);
        proto.setNode(ProviderConfig.PROVIDER_NODE);

        msg.setProto(proto.build());
        msg.setType(ConnectTarget.PROVIDER_TO_REGISTER.getType());
        msg.setMagicId(RoteConst.CoderMagicConst.CODER_UNIQUE_MAGIC);

        session.sendMsg(msg);
    }
}
