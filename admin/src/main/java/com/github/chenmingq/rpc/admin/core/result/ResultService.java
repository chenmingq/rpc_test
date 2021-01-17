package com.github.chenmingq.rpc.admin.core.result;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author : cmq
 * date : 2021/01
 * description : 返回消息
 */

@Getter
@Setter
public class ResultService implements Serializable {


    public ResultService(int code, String msg, Object body) {
        this.code = code;
        this.message = msg;
        this.data = body;
    }

    public ResultService() {
    }

    /**
     * 返回状态
     */
    private int code;

    /**
     * 返回状态消息
     */
    private String message;

    /**
     * 消息体
     */
    private Object data;


}
