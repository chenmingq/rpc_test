package com.github.chenmingq.rpc.common.lb;

import com.github.chenmingq.rpc.common.transport.entity.NodeEntity;

import java.util.Map;

/**
 * @author : cmq
 * date : 2021/1
 * description : 负载均衡查找
 */

public interface BaseLb {

    /**
     * 返回节点
     *
     * @param nodeEntityMap
     * @return
     */
    NodeEntity randomNode(Map<Integer, NodeEntity> nodeEntityMap);

}
