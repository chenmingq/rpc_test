package com.github.chenmingq.rpc.consumer.remote.future;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author : cmq
 * date : 2021/01
 * description : rpc回调数据保存
 */

public class FutureManager {

    private static final FutureManager INSTANCE = new FutureManager();

    public static FutureManager getInstance() {
        return INSTANCE;
    }

    public FutureManager() {
    }

    private final Map<String, RpcFuture> rpcFutureMap = new ConcurrentHashMap<>();

    public void put(String id, RpcFuture rpcFuture) {
        synchronized (this) {
            rpcFutureMap.put(id, rpcFuture);
            this.notify();
        }
    }

    public RpcFuture getFuture(String id) {
        synchronized (this) {
            while (!rpcFutureMap.containsKey(id)) {
                try {
                    // TODO 如果某个provider 关服了,会导致一直线程等待状态,此处需优化修改
                    this.wait(3000L);
                    if (!rpcFutureMap.containsKey(id)) {
                        return null;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return rpcFutureMap.get(id);
        }
    }

    public Map<String, RpcFuture> getRpcFutureMap() {
        return rpcFutureMap;
    }


}
