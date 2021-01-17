package com.github.chenmingq.rpc.consumer.remote;

import com.github.chenmingq.rpc.common.lb.impl.RandomNodeImpl;
import com.github.chenmingq.rpc.common.net.message.RequestRpcMessage;
import com.github.chenmingq.rpc.common.transport.entity.NodeEntity;
import com.github.chenmingq.rpc.common.util.ExecutorsUtil;
import com.github.chenmingq.rpc.consumer.remote.future.FutureManager;
import com.github.chenmingq.rpc.consumer.remote.future.RpcFuture;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author : cmq
 * date : 2021/01/08
 * description :
 */

@Slf4j
public class SessionManager {

    private static final SessionManager INSTANCE = new SessionManager();

    public static SessionManager getInstance() {
        return INSTANCE;
    }

    private final Map<Integer, NodeEntity> sessionMap = new ConcurrentHashMap<>();

    public SessionManager() {
    }

    public void addSession(int providerNode, NodeEntity nodeEntity) {
        int oldSize = sessionMap.size();
        this.sessionMap.put(providerNode, nodeEntity);
        if (oldSize < 1) {
            synchronized (this) {
                this.notify();
            }
        }
    }

    public Set<Integer> getNodeSet() {
        return this.sessionMap.keySet();
    }

    public Map<Integer, NodeEntity> getSessionMap() {
        return this.sessionMap;
    }


    public void removeSession(int providerNode) {
        this.sessionMap.remove(providerNode);
    }

    public RpcFuture sendRpcRequest(RequestRpcMessage message) throws InterruptedException, ExecutionException {
        if (sessionMap.isEmpty()) {
            synchronized (this) {
                while (sessionMap.isEmpty()) {
                    log.info("等待提供者服务...");
                    this.wait();
                }
            }
        }

        Future<RpcFuture> future = ExecutorsUtil.submit(() -> {
            NodeEntity nodeEntity = new RandomNodeImpl().randomNode(sessionMap);
            if (null == nodeEntity) {
                return null;
            }
            nodeEntity.getSession().sendMsg(message);
            RpcFuture rpcFuture = FutureManager.getInstance().getFuture(message.getRequest().getId());
            if (null == rpcFuture) {
                log.info("调用失败,重新获取调用地址");
                NodeEntity tempNode = new RandomNodeImpl().randomNode(sessionMap);
                if (null == tempNode) {
                    return null;
                }
                tempNode.getSession().sendMsg(message);
                rpcFuture = FutureManager.getInstance().getFuture(message.getRequest().getId());
            }
            return rpcFuture;
        });
        return future.get();
    }

}
