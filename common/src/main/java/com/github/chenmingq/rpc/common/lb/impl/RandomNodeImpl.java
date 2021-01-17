package com.github.chenmingq.rpc.common.lb.impl;

import com.github.chenmingq.rpc.common.lb.BaseLb;
import com.github.chenmingq.rpc.common.transport.action.State;
import com.github.chenmingq.rpc.common.transport.entity.NodeEntity;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * @author : cmq
 * date : 2021/1
 * description : 随机选择
 */

public class RandomNodeImpl implements BaseLb {
    @Override
    public NodeEntity randomNode(Map<Integer, NodeEntity> sessionMap) {
        List<NodeEntity> collect = sessionMap.values().stream().filter(nodeEntity -> nodeEntity.getState() == State.SessionState.ACTIVE).collect(Collectors.toList());
        if (collect.isEmpty()) {
            return null;
        }
        return collect.get(new Random().nextInt(collect.size()));
    }
}
