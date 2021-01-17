package com.github.chenmingq.rpc.common.net.message.msg.provider;

import com.github.chenmingq.rpc.common.channel.BaseMessage;
import com.github.chenmingq.rpc.common.transport.protocol.RpcTestProto;
import com.google.protobuf.InvalidProtocolBufferException;
import lombok.Getter;
import lombok.Setter;

/**
 * @author : cmq
 * date : 2021/01
 * description : 提供者连接到注册中心
 */

@Getter
@Setter
public class ProviderConnectRegisterMessage extends BaseMessage {

    private RpcTestProto.ReqConnectServerMessage proto;

    @Override
    public void deCoder(byte[] body) throws InvalidProtocolBufferException {
        proto = RpcTestProto.ReqConnectServerMessage.parseFrom(body);
    }

    @Override
    public byte[] enCoder() {
        if (null == proto) {
            return null;
        }
        return proto.toByteArray();
    }
}
