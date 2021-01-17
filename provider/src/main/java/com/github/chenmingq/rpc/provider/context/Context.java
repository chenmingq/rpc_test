package com.github.chenmingq.rpc.provider.context;

import com.github.chenmingq.rpc.common.net.NetWorkOption;
import com.github.chenmingq.rpc.provider.config.ProviderConfig;
import com.github.chenmingq.rpc.provider.config.RpcServiceImplConfig;
import com.github.chenmingq.rpc.provider.transport.ProviderOptionImpl;
import lombok.extern.slf4j.Slf4j;

/**
 * @author : cmq
 * date : 2021/01
 * description :
 */

@Slf4j
public class Context implements Runnable {


    private final Class<?> clazz;

    public Context(Class<?> clazz) {
        this.clazz = clazz;
    }

    @Override
    public void run() {
        ProviderConfig.init();
        RpcServiceImplConfig.getInstance().init(clazz);
        NetWorkOption netWorkOption = new ProviderOptionImpl();
        netWorkOption.start(ProviderConfig.PROVIDER_PORT);
        netWorkOption.connect();
//        ThreadUtil.addShutdownHook(ConnectTarget.PROVIDER_TO_REGISTER.getType());
    }

}
