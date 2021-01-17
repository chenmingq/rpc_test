package com.github.chenmingq.rpc.register;

import com.github.chenmingq.rpc.common.channel.ChannelInitializerCommons;
import com.github.chenmingq.rpc.common.channel.coder.DeCoder;
import com.github.chenmingq.rpc.common.channel.coder.EnCoder;
import com.github.chenmingq.rpc.common.channel.session.Session;
import com.github.chenmingq.rpc.common.transport.options.OptionContext;

import java.util.Arrays;

/**
 * @author : cmq
 * date : 2021/01
 * description :
 */

public class ClientTest {

    public static void main(String[] args) {
        Session session = OptionContext.getInstance().connect("localhost", 1234, new ChannelInitializerCommons<>(EnCoder.class, DeCoder.class, Arrays.asList(new ClientHandler())));
    }
}
