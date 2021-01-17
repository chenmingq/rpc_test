package com.github.chenmingq.rpc.common.transport.entity;

import com.github.chenmingq.rpc.common.channel.session.Session;
import com.github.chenmingq.rpc.common.transport.action.State;
import lombok.Getter;
import lombok.Setter;

/**
 * @author : cmq
 * date : 2021/1
 * description : 连接的节点信息
 */


@Getter
@Setter
public class NodeEntity {

    public NodeEntity(Session session, int node, String ip, int port) {
        this.session = session;
        this.node = node;
        this.ip = ip;
        this.port = port;
    }

    public NodeEntity(Session session, int node, String ip, int port, int state) {
        this.session = session;
        this.node = node;
        this.ip = ip;
        this.port = port;
        this.state = state;
    }

    public NodeEntity() {
    }

    /**
     * session
     */
    private Session session;

    /**
     * 节点
     */
    private int node;

    /**
     * ip
     */
    private String ip;


    /**
     * 端口
     */
    private int port;

    /**
     * 节点状态
     * @see State
     */
    private int state;

}
