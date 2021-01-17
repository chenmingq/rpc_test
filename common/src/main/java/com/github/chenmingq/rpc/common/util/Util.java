package com.github.chenmingq.rpc.common.util;

import io.netty.channel.epoll.Epoll;

import java.lang.management.ManagementFactory;

/**
 * @author : cmq
 * date : 2020-11
 * Description： 工具
 */

public class Util {


    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取pid
     *
     * @return
     */
    public static int fetchProcessId() {
        try {
            return Integer.parseInt(ManagementFactory.getRuntimeMXBean().getName().split("@")[0]);
        } catch (Exception e) {
            return -1;
        }
    }

    /**
     * 当前系统是否支持epoll
     *
     * @return
     */
    public static boolean useEpoll() {
        return RemotingUtil.isLinuxPlatform()
                && Epoll.isAvailable();
    }
}
