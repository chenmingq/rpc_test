package com.github.chenmingq.rpc.common.channel.session;

import lombok.Getter;
import lombok.Setter;

/**
 * @author : cmq
 * date : 2021/01
 * description :
 */

@Getter
@Setter
public class Address {
    public Address(String ip, int port, int nodeId) {
        this.ip = ip;
        this.port = port;
        this.nodeId = nodeId;
    }
    public Address(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    public Address() {
    }

    private String ip;

    private int port;

    private int nodeId;
}
