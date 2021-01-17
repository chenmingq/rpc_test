package com.github.chenmingq.rpc.consumer.queue;

import com.github.chenmingq.rpc.common.net.message.RequestRpcMessage;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author : cmq
 * date : 2021/1
 * description : 消费者和队列列表
 */

public class QueueFactory {

    /**
     * 消费失败的数据
     */
    public static LinkedBlockingQueue<RequestRpcMessage> WAIT_CONSUMER_QUEUE = new LinkedBlockingQueue<>();


}
