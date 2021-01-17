package com.github.chenmingq.rpc.provider;

import com.github.chenmingq.rpc.provider.context.ApplicationContext;
import lombok.extern.slf4j.Slf4j;

/**
 * @author : cmq
 * date : 2021/01
 * description :
 */

@Slf4j
public class ProviderStart {

    public static void main(String[] args) {
        new ApplicationContext().start(ProviderStart.class);
    }
}
