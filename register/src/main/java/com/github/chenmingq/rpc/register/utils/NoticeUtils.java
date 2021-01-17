package com.github.chenmingq.rpc.register.utils;

import com.github.chenmingq.rpc.common.channel.session.Session;
import com.github.chenmingq.rpc.common.constant.RoteConst;
import com.github.chenmingq.rpc.common.net.message.msg.consumer.RegisterResultConsumerMessage;
import com.github.chenmingq.rpc.common.transport.action.ConnectTarget;
import com.github.chenmingq.rpc.common.transport.entity.NodeEntity;
import com.github.chenmingq.rpc.common.transport.protocol.RpcTestProto;
import com.github.chenmingq.rpc.register.manager.RegisterSessionManager;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : cmq
 * date : 2021/1
 * description : 消息通知
 */

public class NoticeUtils {


    /**
     * 注册中心返回给我消费者的提供者列表
     *
     * @param session
     */
    public static void noticeConsumerAllProviderConnect(Session session) {
        session.sendMsg(buildConsumerAllProviderConnectMessage());
    }

    /**
     * 注册中心返回给我消费者的提供者列表
     */
    public static void noticeConsumerAllProviderConnect() {
        RegisterResultConsumerMessage message = buildConsumerAllProviderConnectMessage();

        List<NodeEntity> consumerConnectList = RegisterSessionManager.getInstance().getConsumerConnect();
        consumerConnectList.forEach(v -> v.getSession().sendMsg(message));
    }

    private static RegisterResultConsumerMessage buildConsumerAllProviderConnectMessage() {
        List<NodeEntity> nodeEntityList = RegisterSessionManager.getInstance().getProviderConnect();
        RpcTestProto.ResProviderAddressMessage.Builder proto = RpcTestProto.ResProviderAddressMessage.newBuilder();

        List<RpcTestProto.AddressBean> addressBeanList = new ArrayList<>();
        nodeEntityList.forEach(nodeEntity -> {
            RpcTestProto.AddressBean.Builder bean = RpcTestProto.AddressBean.newBuilder();
            bean.setIp(nodeEntity.getIp());
            bean.setPort(nodeEntity.getPort());
            bean.setNode(nodeEntity.getNode());
            addressBeanList.add(bean.build());
        });
        proto.addAllAddrList(addressBeanList);
        RegisterResultConsumerMessage msg = new RegisterResultConsumerMessage();
        msg.setProto(proto.build());
        msg.setType(ConnectTarget.REGISTER_TO_CONSUMER.getType());
        msg.setMagicId(RoteConst.CoderMagicConst.CODER_UNIQUE_MAGIC);

        return msg;
    }
}
