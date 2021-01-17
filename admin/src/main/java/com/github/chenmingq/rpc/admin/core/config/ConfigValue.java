package com.github.chenmingq.rpc.admin.core.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author : cmq
 * date : 2021/01
 * description : 配置文件
 */

@Component
@PropertySource(value = "classpath:admin.properties")
public class ConfigValue {

    @Value("${userName}")
    public String userName;

    @Value("${password}")
    public String password;
}
