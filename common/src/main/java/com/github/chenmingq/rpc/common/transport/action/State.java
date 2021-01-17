package com.github.chenmingq.rpc.common.transport.action;

/**
 * @author : cmq
 * date : 2021/01
 * description : 状态类型
 */

public interface State {

    /**
     * session状态
     */
    interface SessionState {
        /**
         * 活跃
         */
        int ACTIVE = 1;

        /**
         * 暂停
         */
        int PAUSE = 2;

        /**
         * 关闭
         */
        int CLOSE = 3;
    }

}
