package com.github.chenmingq.rpc.admin.core.consts;

/**
 * @author : cmq
 * date : 2021/01
 * description : 返回状态码
 */

public enum ResultCode {

    SUCCESS(20000, "执行成功"),
    FAILURE(300, "执行失败"),
    ERROR(500, "执行错误"),
    ;

    private int code;

    private String msg;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    ResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
