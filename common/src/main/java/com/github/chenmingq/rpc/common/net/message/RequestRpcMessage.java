package com.github.chenmingq.rpc.common.net.message;

import com.github.chenmingq.rpc.common.channel.BaseMessage;
import com.github.chenmingq.rpc.common.remote.entity.RpcRequest;
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
public class RequestRpcMessage extends BaseMessage {

    private RpcRequest request;

    @Override
    public void deCoder(byte[] body) throws InvalidProtocolBufferException {
        request = ProtoStuffUtils.deserializer(body, RpcRequest.class);
        setMsg(request);
    }

    @Override
    public byte[] enCoder() {
        setMsg(request);
        return ProtoStuffUtils.serializer(getMsg());
    }

}
