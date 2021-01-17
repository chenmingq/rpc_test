package com.github.chenmingq.rpc.consumer.config;

import com.github.chenmingq.rpc.common.channel.session.Address;
import com.github.chenmingq.rpc.common.constant.ConfigKeyConst;
import com.github.chenmingq.rpc.common.util.LordPropertiesUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author : cmq
 * date : 2021/01/06
 * description :
 */

public class ConsumerConfig {

    public static int CONSUMER_PORT;
    public static int CONSUMER_NODE;
    public static String CONSUMER_REGISTER;
    public static List<Address> CONSUMER_REGISTER_ADDRESS_LIST;


    public static void init() {
        String def = "consumer.properties";
        String cfgPath = System.getProperty("cfgPath");
        Properties properties = LordPropertiesUtils.lordProperties(cfgPath, def);
        if (null == properties) {
            throw new RuntimeException("生产者配置文件读取错误");
        }

        CONSUMER_PORT = Integer.parseInt(properties.getProperty(ConfigKeyConst.ConsumerConfig.CONSUMER_PORT));
        CONSUMER_NODE = Integer.parseInt(properties.getProperty(ConfigKeyConst.ConsumerConfig.CONSUMER_NODE));
        CONSUMER_REGISTER = properties.getProperty(ConfigKeyConst.ConsumerConfig.CONSUMER_REGISTER, "default");
        String remoteAddress = properties.getProperty(ConfigKeyConst.ConsumerConfig.CONSUMER_REGISTER_ADDRESS);
        if (StringUtils.isNoneBlank(remoteAddress)) {
            String[] split = remoteAddress.split(",");
            List<Address> addressList = new ArrayList<>();
            for (String s : split) {
                String[] addr = s.split(":");
                if (addr.length < 2) {
                    continue;
                }
                addressList.add(new Address(addr[0], Integer.parseInt(addr[1])));
            }
            if (!addressList.isEmpty()) {
                CONSUMER_REGISTER_ADDRESS_LIST = addressList;
            }
        }

    }

}
