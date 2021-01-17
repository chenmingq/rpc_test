package com.github.chenmingq.rpc.register.manager;

import com.github.chenmingq.rpc.common.channel.session.Session;
import com.github.chenmingq.rpc.common.transport.entity.NodeEntity;
import com.github.chenmingq.rpc.register.utils.NoticeUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author : cmq
 * date : 2021/01
 * description : 所有连接到注册中心的session
 */

public class RegisterSessionManager {


    private final static RegisterSessionManager INSTANCE = new RegisterSessionManager();

    public static RegisterSessionManager getInstance() {
        return INSTANCE;
    }

    public RegisterSessionManager() {
    }

    /**
     * 连接会话
     * {客户端session,客户端端口节点信息}
     */
    private final static Map<Session, NodeEntity> PROVIDER_SESSION_MAP = new ConcurrentHashMap<>();
    private final static Map<Session, NodeEntity> CONSUMER_SESSION_MAP = new ConcurrentHashMap<>();


    public void addProviderAllSession(Session session, NodeEntity node) {
        PROVIDER_SESSION_MAP.put(session, node);
    }

    public void addConsumerAllSession(Session session, NodeEntity node) {
        CONSUMER_SESSION_MAP.put(session, node);
    }

    public List<NodeEntity> getProviderConnect() {
        return new ArrayList<>(PROVIDER_SESSION_MAP.values());
    }

    public List<NodeEntity> getConsumerConnect() {
        return new ArrayList<>(CONSUMER_SESSION_MAP.values());
    }

    public void removeSession(Session session) {
        PROVIDER_SESSION_MAP.remove(session);
        CONSUMER_SESSION_MAP.remove(session);
        NoticeUtils.noticeConsumerAllProviderConnect();
    }

}
