package com.github.chenmingq.rpc.register.transport;

import com.github.chenmingq.rpc.common.channel.BaseMessage;
import com.github.chenmingq.rpc.common.channel.ChannelInitializerCommons;
import com.github.chenmingq.rpc.common.channel.coder.DeCoder;
import com.github.chenmingq.rpc.common.channel.coder.EnCoder;
import com.github.chenmingq.rpc.common.channel.session.Session;
import com.github.chenmingq.rpc.common.net.NetWorkOption;
import com.github.chenmingq.rpc.common.transport.options.OptionContext;
import com.github.chenmingq.rpc.common.util.ExecutorsUtil;
import com.github.chenmingq.rpc.common.util.Util;
import com.github.chenmingq.rpc.register.channel.handler.RegisterServerHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @author : cmq
 * date : 2021/01
 * description :
 */

@Slf4j
public class RegisterOptionImpl implements NetWorkOption {

    @Override
    public void start(int port) {
        Future<Session> submit = ExecutorsUtil.submit(new Callable<Session>() {
            @Override
            public Session call() throws Exception {
                return OptionContext.getInstance().initServer(port, new ChannelInitializerCommons<>(EnCoder.class, DeCoder.class, Arrays.asList(new RegisterServerHandler())));
            }
        });
        try {
            Session session = submit.get();
            if (null != session) {
                log.info("注册中心服务 [{}] 启动成功 PID - {} , 端口 - {}", "REGISTER", Util.fetchProcessId(), port);
            } else {
                log.error("注册中心服务启动失败 [{}]", "REGISTER");
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start(int port, TimeUnit unit, long value) {
        ExecutorsUtil.schedule(new Runnable() {
            @Override
            public void run() {
                start(port);
            }
        }, value, unit);
    }

    @Override
    public void stop() {

    }

    @Override
    public void stop(TimeUnit unit, long value) {
        ExecutorsUtil.schedule(new Runnable() {
            @Override
            public void run() {
                stop();
            }
        }, value, unit);
    }

    @Override
    public void onEventState() {

    }

    @Override
    public Session connect(String ip, int port) {
        return null;
    }

    @Override
    public Session connect() {
        return null;
    }

    @Override
    public void onMessage(Session session, BaseMessage baseMessage) {

    }
}
