package com.github.chenmingq.rpc.common.process;

import com.github.chenmingq.rpc.common.channel.BaseMessage;
import com.github.chenmingq.rpc.common.channel.session.Session;

/**
 * @author : cmq
 * date : 2021/01
 * description :
 */

public interface IProcess extends Runnable {

    /**
     * handler处理的消息
     *
     * @param session
     * @param message
     */
    void executor(Session session, BaseMessage message);

}
