package com.github.chenmingq.rpc.common.remote.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * @author : cmq
 * date : 2020/12
 * description : rpc远程传输返回
 */

@Setter
@Getter
public class RpcResponse {

    /**
     * 调用id
     */
    private String id;

    /**
     * 返回结果对象
     */
    private Object result;

    @Override
    public String toString() {
        return "RpcResponse{" +
                "id='" + id + '\'' +
                ", result=" + result +
                '}';
    }
}
