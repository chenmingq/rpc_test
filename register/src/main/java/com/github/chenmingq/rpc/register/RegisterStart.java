package com.github.chenmingq.rpc.register;

import com.github.chenmingq.rpc.register.config.RegisterConfig;
import com.github.chenmingq.rpc.register.transport.RegisterOptionImpl;
import lombok.extern.slf4j.Slf4j;

/**
 * @author : cmq
 * date : 2021/01
 * description :
 */

@Slf4j
public class RegisterStart {

    public static void main(String[] args) {
        RegisterConfig.init();
        new RegisterOptionImpl().start(RegisterConfig.REGISTER_PORT);
    }
}
