package com.github.chenmingq.rpc.consumer.process;

import com.github.chenmingq.rpc.common.channel.BaseMessage;
import com.github.chenmingq.rpc.common.channel.ChannelInitializerCommons;
import com.github.chenmingq.rpc.common.channel.coder.DeCoder;
import com.github.chenmingq.rpc.common.channel.coder.EnCoder;
import com.github.chenmingq.rpc.common.channel.session.Session;
import com.github.chenmingq.rpc.common.constant.RoteConst;
import com.github.chenmingq.rpc.common.net.message.msg.consumer.ConsumerConnectProviderMessage;
import com.github.chenmingq.rpc.common.net.message.msg.consumer.RegisterResultConsumerMessage;
import com.github.chenmingq.rpc.common.process.IProcess;
import com.github.chenmingq.rpc.common.transport.action.ConnectTarget;
import com.github.chenmingq.rpc.common.transport.action.State;
import com.github.chenmingq.rpc.common.transport.entity.NodeEntity;
import com.github.chenmingq.rpc.common.transport.options.OptionContext;
import com.github.chenmingq.rpc.common.transport.protocol.RpcTestProto;
import com.github.chenmingq.rpc.consumer.config.ConsumerConfig;
import com.github.chenmingq.rpc.consumer.handler.ConsumerHandler;
import com.github.chenmingq.rpc.consumer.remote.SessionManager;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author : cmq
 * date : 2021/01
 * description : 注册中心返回给消费者
 */

@Slf4j
public class RegisterResultConsumerProcess implements IProcess {


    private Session session;

    private BaseMessage message;

    @Override
    public void run() {
        RegisterResultConsumerMessage message = (RegisterResultConsumerMessage) this.message;
        RpcTestProto.ResProviderAddressMessage proto = message.getProto();
        List<RpcTestProto.AddressBean> addrListList = proto.getAddrListList();

        log.info("consumer 注册中心返回的提供者地址列表 {}", addrListList);
        if (addrListList.isEmpty()) {
            return;
        }
        Set<Integer> nodeSet = SessionManager.getInstance().getNodeSet();
        if (!nodeSet.isEmpty()) {
            List<Integer> collect = addrListList.stream().map(RpcTestProto.AddressBean::getNode).collect(Collectors.toList());
            Iterator<Integer> iterator = nodeSet.iterator();
            while (iterator.hasNext()) {
                Integer node = iterator.next();
                if (collect.contains(node)) {
                    continue;
                }
                SessionManager.getInstance().removeSession(node);
                iterator.remove();
            }
        }

        for (RpcTestProto.AddressBean bean : addrListList) {
            if (nodeSet.contains(bean.getNode())) {
                continue;
            }
            connectProvider(bean.getNode(), bean.getIp(), bean.getPort());
        }
    }

    @Override
    public void executor(Session session, BaseMessage baseMessage) {
        this.session = session;
        this.message = baseMessage;
    }

    private void connectProvider(int providerNode, String ip, int port) {
        log.info("准备连接到提供者服务 节点:[{}],地址 [{}:{}]", providerNode, ip, port);
        Session connect = OptionContext.getInstance().connect(ip, port,
                new ChannelInitializerCommons<>(EnCoder.class, DeCoder.class, Arrays.asList(new ConsumerHandler())));
        if (null == connect) {
            log.info("消费者节点 [{}] 连接到提供者 [{}:{}] 失败", ConsumerConfig.CONSUMER_NODE, ip, port);
            return;
        }
        SessionManager.getInstance().addSession(providerNode, new NodeEntity(connect, providerNode, ip, port, State.SessionState.ACTIVE));
        ConsumerConnectProviderMessage connectProviderMessage = new ConsumerConnectProviderMessage();
        connectProviderMessage.setMagicId(RoteConst.CoderMagicConst.CODER_UNIQUE_MAGIC);
        connectProviderMessage.setType(ConnectTarget.CONSUMER_TO_PROVIDER.getType());
        connect.sendMsg(connectProviderMessage);
    }
}
