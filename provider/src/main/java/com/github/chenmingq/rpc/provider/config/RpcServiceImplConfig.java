package com.github.chenmingq.rpc.provider.config;

import com.github.chenmingq.rpc.common.annotation.RpcService;
import com.github.chenmingq.rpc.common.util.AnnotationUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author : cmq
 * date : 2021/01
 * description :服务实现的类配置保存
 */

@Slf4j
public class RpcServiceImplConfig {

    private static final RpcServiceImplConfig INSTANCE = new RpcServiceImplConfig();

    public static RpcServiceImplConfig getInstance() {
        return INSTANCE;
    }

    public RpcServiceImplConfig() {
    }


    /**
     * {api暴露的接口,服务实现类}
     */
    private static final Map<Class<?>, Class<?>> RPC_SERVICE_IMPL_MAP = new HashMap<>();


    public void init(Class<?> clazz) {
        Set<Class<?>> implClassSet = AnnotationUtil.lordClazz(RpcService.class, clazz.getPackage().getName());
        for (Class<?> implClass : implClassSet) {
            if (RPC_SERVICE_IMPL_MAP.containsValue(implClass)) {
                throw new RuntimeException("服务实现类重复使用");
            }
            Class<?>[] interfaces = implClass.getInterfaces();
            for (Class<?> interfaceClass : interfaces) {
                if (null == interfaceClass.getAnnotation(RpcService.class)) {
                    continue;
                }
                log.info("查找到 RpcService 实现类 [{}]", interfaceClass.getName());
                RPC_SERVICE_IMPL_MAP.put(interfaceClass, implClass);
                break;
            }
        }
    }

    public Class<?> getServiceImplClass(Class<?> clazz) {
        return RPC_SERVICE_IMPL_MAP.get(clazz);
    }

}
