package com.github.chenmingq.rpc.common.util;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Properties;


/**
 * @author : cmq
 * date : 2021/01
 * description : 读取配置文件
 */

public class LordPropertiesUtils {

    /**
     * 读取本地配置
     *
     * @param cfgPath
     * @param defPath
     * @return
     */
    public static Properties lordProperties(String cfgPath, String defPath) {
        InputStream inputStream = null;
        Properties properties = null;
        try {
            if (null == cfgPath) {
                URL resource = Thread.currentThread().getContextClassLoader().getResource(defPath);
                if (null == resource) {
                    throw new RuntimeException("the source is not");
                }
                properties = new Properties();
                inputStream = resource.openStream();
                BufferedReader bf = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
                properties.load(bf);
            } else {
                // 通过输入缓冲流进行读取配置文件
                inputStream = new BufferedInputStream(new FileInputStream(cfgPath));
                // 加载输入流
                properties = new Properties();
                properties.load(inputStream);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != inputStream) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return properties;
    }
}
