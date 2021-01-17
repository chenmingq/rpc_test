package com.github.chenmingq.rpc.common.net.message.msg.consumer;

import com.github.chenmingq.rpc.common.channel.BaseMessage;
import com.google.protobuf.InvalidProtocolBufferException;
import lombok.Getter;
import lombok.Setter;

/**
 * @author : cmq
 * date : 2021/01
 * description : 消费者连接提供者
 */

@Getter
@Setter
public class ConsumerConnectProviderMessage extends BaseMessage {
    @Override
    public void deCoder(byte[] body) throws InvalidProtocolBufferException {

    }

    @Override
    public byte[] enCoder() {
        return new byte[0];
    }
}
