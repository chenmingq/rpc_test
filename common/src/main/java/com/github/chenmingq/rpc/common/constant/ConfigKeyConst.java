package com.github.chenmingq.rpc.common.constant;

/**
 * @author : cmq
 * date : 2021/01
 * description : 配置信息
 */

public interface ConfigKeyConst {

    /**
     * 提供者配置
     */
    interface ProviderConfig {
        String PROVIDER_PORT = "provider.port";
        String PROVIDER_NODE = "provider.node";
        String PROVIDER_REGISTER = "provider.register";
        String PROVIDER_REGISTER_ADDRESS = "provider.register.address";
    }

    /**
     * 消费者配置
     */
    interface ConsumerConfig {
        String CONSUMER_PORT = "consumer.port";
        String CONSUMER_NODE = "consumer.node";
        String CONSUMER_REGISTER = "consumer.register";
        String CONSUMER_REGISTER_ADDRESS = "consumer.register.address";
    }

    /**
     * 注册中心配置
     */
    interface RegisterConfig {
        String REGISTER_PORT = "register.port";
        String REGISTER_NODE = "register.node";
        String REGISTER_CONNECT_MAX = "register.connect.max";
    }
}