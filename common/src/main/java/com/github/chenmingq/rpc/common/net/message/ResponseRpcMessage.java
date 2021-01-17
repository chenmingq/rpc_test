package com.github.chenmingq.rpc.common.net.message;

import com.github.chenmingq.rpc.common.channel.BaseMessage;
import com.github.chenmingq.rpc.common.remote.entity.RpcResponse;
import com.github.chenmingq.rpc.common.util.ProtoStuffUtils;
import com.google.protobuf.InvalidProtocolBufferException;
import lombok.Getter;
import lombok.Setter;

/**
 * @author : cmq
 * date : 2021/01
 * description :
 */

@Getter
@Setter
public class ResponseRpcMessage extends BaseMessage {

    private RpcResponse response;

    @Override
    public void deCoder(byte[] body) throws InvalidProtocolBufferException {
        response = ProtoStuffUtils.deserializer(body, RpcResponse.class);
        setMsg(response);
    }

    @Override
    public byte[] enCoder() {
        return ProtoStuffUtils.serializer(response);
    }
}
