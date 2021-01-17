package com.github.chenmingq.rpc.register.config;


import com.github.chenmingq.rpc.common.constant.ConfigKeyConst;
import com.github.chenmingq.rpc.common.util.LordPropertiesUtils;

import java.util.Properties;

/**
 * @author : cmq
 * date : 2021/01
 * description :注册中心配置
 */

public class RegisterConfig {

    public static int REGISTER_PORT;
    public static int REGISTER_NODE;
    public static int REGISTER_CONNECT_MAX;


    public static void init() {
        String def = "register.properties";
        String cfgPath = System.getProperty("cfgPath");
        Properties properties = LordPropertiesUtils.lordProperties(cfgPath, def);

        if (null == properties) {
            throw new RuntimeException("注册中心配置文件读取错误");
        }

        REGISTER_PORT = Integer.parseInt(properties.getProperty(ConfigKeyConst.RegisterConfig.REGISTER_PORT));
        REGISTER_NODE = Integer.parseInt(properties.getProperty(ConfigKeyConst.RegisterConfig.REGISTER_NODE));
        REGISTER_CONNECT_MAX = Integer.parseInt(properties.getProperty(ConfigKeyConst.RegisterConfig.REGISTER_CONNECT_MAX));
    }
}
