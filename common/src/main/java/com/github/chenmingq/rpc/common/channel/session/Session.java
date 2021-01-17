package com.github.chenmingq.rpc.common.channel.session;

import com.github.chenmingq.rpc.common.channel.BaseMessage;
import com.github.chenmingq.rpc.common.util.RemotingUtil;
import io.netty.channel.Channel;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

/**
 * @author : cmq
 * date : 2021/01/05
 * description :
 */

@Getter
@Setter
public class Session {

    public Session(Channel channel) {
        this.channel = channel;
    }

    public Session() {
    }

    private Channel channel;

    public void sendMsg(BaseMessage message) {
        byte[] bytes = message.enCoder();
        message.setBody(bytes);
        channel.writeAndFlush(message);
    }

    public void close() {
        RemotingUtil.closeChannel(this.channel);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Session session = (Session) o;
        return Objects.equals(channel, session.channel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(channel);
    }
}
