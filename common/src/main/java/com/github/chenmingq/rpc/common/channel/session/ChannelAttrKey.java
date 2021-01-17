package com.github.chenmingq.rpc.common.channel.session;

import io.netty.util.AttributeKey;

public class ChannelAttrKey {

    public static final AttributeKey<Session> SESSION = AttributeKey.newInstance("SESSION");
}
