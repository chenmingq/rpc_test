package com.github.chenmingq.rpc.common.transport.action;

import java.util.Arrays;

/**
 * @author : cmq
 * date : 2021/01
 * description : 连接目标
 */

public enum ConnectTarget {

    /**
     * 消费者连接注册中心
     */
    CONSUMER_TO_REGISTER(1),
    /**
     * 注册中心返回消费者连接结果
     */
    REGISTER_TO_CONSUMER(2),

    /**
     * 提供者连接注册中心
     */
    PROVIDER_TO_REGISTER(3),
    /**
     * 注册中心返回提供者连接结果
     */
    REGISTER_TO_PROVIDER(4),

    /**
     * 消费者连接提供者
     */
    CONSUMER_TO_PROVIDER(5),
    /**
     * 提供者返回消费者连接结果
     */
    PROVIDER_TO_CONSUMER(6),

    /**
     * RPC连接提供者
     */
    CONSUMER_RPC_REQ_PROVIDER(7),
    /**
     * 提供者返回RPC
     */
    PROVIDER_RPC_RES_CONSUMER(8),

    ;

    private final int type;

    public int getType() {
        return type;
    }

    ConnectTarget(int type) {
        this.type = type;
    }


    public static ConnectTarget parse(int type) {
        return Arrays.stream(ConnectTarget.values()).filter(v -> v.getType() == type).findAny().orElse(null);
    }
}
