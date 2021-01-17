package com.github.chenmingq.rpc.common.remote.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * @author : cmq
 * date : 2020/12/7
 * description : rpc远程传输
 */

@Getter
@Setter
public class RpcRequest {

    /**
     * 远程调用id
     */
    private String id;

    /**
     * 对象名称
     */
    private String className;

    /**
     * 方法名称
     */
    private String methodName;

    /**
     * 参数
     */
    private Object[] args;

    /**
     * 参数类型
     */
    private Class<?>[] parameterTypes;
}
