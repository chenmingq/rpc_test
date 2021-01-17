package com.github.chenmingq.rpc.common.channel;

import com.github.chenmingq.rpc.common.transport.action.ConnectTarget;
import com.google.protobuf.InvalidProtocolBufferException;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.Objects;

/**
 * @author : cmq
 * date : 2021/01
 * description :
 */

@Getter
@Setter
public abstract class BaseMessage {

    /**
     * 唯一标记
     */
    private int magicId;

    /**
     * 连接类型
     *
     * @see ConnectTarget
     */
    private int type;

    private byte[] body;

    /**
     * 消息
     */
    private Object msg;

    /**
     * deCoder
     *
     * @param body
     * @throws InvalidProtocolBufferException
     */
    public abstract void deCoder(byte[] body) throws InvalidProtocolBufferException;

    /**
     * enCoder
     *
     * @return
     */
    public abstract byte[] enCoder();

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BaseMessage that = (BaseMessage) o;
        return magicId == that.magicId && type == that.type && Arrays.equals(body, that.body);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(magicId, type);
        result = 31 * result + Arrays.hashCode(body);
        return result;
    }
}
