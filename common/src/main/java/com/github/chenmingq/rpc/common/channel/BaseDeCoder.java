package com.github.chenmingq.rpc.common.channel;

import io.netty.handler.codec.ReplayingDecoder;

/**
 * @author : cmq
 * date : 2021/01
 * description : 解码
 */

public abstract class BaseDeCoder<T extends BaseMessage> extends ReplayingDecoder<T> {
}
