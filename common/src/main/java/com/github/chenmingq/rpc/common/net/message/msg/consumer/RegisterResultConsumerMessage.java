package com.github.chenmingq.rpc.common.net.message.msg.consumer;

import com.github.chenmingq.rpc.common.channel.BaseMessage;
import com.github.chenmingq.rpc.common.transport.protocol.RpcTestProto;
import com.google.protobuf.InvalidProtocolBufferException;
import lombok.Getter;
import lombok.Setter;

/**
 * @author : cmq
 * date : 2021/01
 * description : 注册中心返回给消费者
 */

@Getter
@Setter
public class RegisterResultConsumerMessage extends BaseMessage {

    private RpcTestProto.ResProviderAddressMessage proto;

    @Override
    public void deCoder(byte[] body) throws InvalidProtocolBufferException {
        this.proto = RpcTestProto.ResProviderAddressMessage.parseFrom(body);
    }

    @Override
    public byte[] enCoder() {
        return proto.toByteArray();
    }
}
