package com.github.chenmingq.rpc.common.hook;

/**
 * @author : cmq
 * date : 2021/01
 * description :
 */

public class JvmCloseHook extends Thread {

    private final int type;

    public JvmCloseHook(int type) {
        this.type = type;
    }

    @Override
    public void run() {

    }
}
