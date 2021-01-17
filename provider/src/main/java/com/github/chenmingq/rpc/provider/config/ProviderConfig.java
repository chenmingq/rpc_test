package com.github.chenmingq.rpc.provider.config;


import com.github.chenmingq.rpc.common.channel.session.Address;
import com.github.chenmingq.rpc.common.constant.ConfigKeyConst;
import com.github.chenmingq.rpc.common.util.LordPropertiesUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author : cmq
 * date : 2021/01
 * description :
 */

public class ProviderConfig {

    public static int PROVIDER_PORT;
    public static int PROVIDER_NODE;
    public static String PROVIDER_REGISTER;
    public static List<Address> PROVIDER_REGISTER_ADDRESS_LIST;


    public static void init() {

        String def = "provider.properties";
        String cfgPath = System.getProperty("cfgPath");
        Properties properties = LordPropertiesUtils.lordProperties(cfgPath, def);

        if (null == properties) {
            throw new RuntimeException("生产者配置文件读取错误");
        }

        PROVIDER_PORT = Integer.parseInt(properties.getProperty(ConfigKeyConst.ProviderConfig.PROVIDER_PORT));
        PROVIDER_NODE = Integer.parseInt(properties.getProperty(ConfigKeyConst.ProviderConfig.PROVIDER_NODE));
        PROVIDER_REGISTER = properties.getProperty(ConfigKeyConst.ProviderConfig.PROVIDER_REGISTER, "default");
        String remoteAddress = properties.getProperty(ConfigKeyConst.ProviderConfig.PROVIDER_REGISTER_ADDRESS);
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
                PROVIDER_REGISTER_ADDRESS_LIST = addressList;
            }
        }

    }
}
