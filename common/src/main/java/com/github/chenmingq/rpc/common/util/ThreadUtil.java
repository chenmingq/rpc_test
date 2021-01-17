package com.github.chenmingq.rpc.common.util;

import com.github.chenmingq.rpc.common.hook.JvmCloseHook;

/**
 * @author : cmq
 * date : 2021/01
 * description :
 */

public class ThreadUtil {

    public static void addShutdownHook(int type) {
        Runtime.getRuntime().addShutdownHook(new JvmCloseHook(type));
    }
}
