package com.github.chenmingq.rpc.consumer;

import com.github.chenmingq.rpc.common.util.ExecutorsUtil;
import com.github.chenmingq.rpc.consumer.context.Context;
import lombok.extern.slf4j.Slf4j;

/**
 * @author : cmq
 * date : 2021/01
 * description :
 */

@Slf4j
public class ConsumerStart {

    public static void main(String[] args) {

        ExecutorsUtil.execute(new Context());
    }


}
