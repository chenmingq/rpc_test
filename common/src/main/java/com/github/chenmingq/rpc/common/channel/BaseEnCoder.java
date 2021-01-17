package com.github.chenmingq.rpc.common.channel;

import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author : cmq
 * date : 2021/01
 * description : 编码
 */
public abstract class BaseEnCoder<T extends BaseMessage> extends MessageToByteEncoder<T> {
}
