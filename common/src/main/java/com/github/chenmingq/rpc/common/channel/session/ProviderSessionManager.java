package com.github.chenmingq.rpc.common.channel.session;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : cmq
 * date : 2021/01
 * description :
 */

public class ProviderSessionManager {

    private final static ProviderSessionManager INSTANCE = new ProviderSessionManager();

    public static ProviderSessionManager getInstance() {
        return INSTANCE;
    }

    public ProviderSessionManager() {
    }

    /**
     * 注册中心session列表
     */
    private final static List<Session> REGISTER_SESSION_LIST = new ArrayList<>();


    public void addRegisterSession(Session session) {
        REGISTER_SESSION_LIST.add(session);
    }

    public List<Session> getAllRegisterSession() {
        return REGISTER_SESSION_LIST;
    }


}
