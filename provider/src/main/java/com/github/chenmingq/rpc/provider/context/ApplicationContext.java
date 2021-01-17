package com.github.chenmingq.rpc.provider.context;

import com.github.chenmingq.rpc.common.util.ExecutorsUtil;

/**
 * @author : cmq
 * date : 2021/01
 * description :
 */

public class ApplicationContext {


    public void start(Class<?> clazz) {
        ExecutorsUtil.execute(new Context(clazz));
    }


}
