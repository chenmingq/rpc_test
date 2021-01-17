package com.github.chenmingq.rpc.common.util;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author : cmq
 * date : 2020/12
 * description : 随机
 */

public class RandomUtil {


    /**
     * 获取list权重索引
     *
     * @param list
     * @return
     */
    public static int getWeight(List<Integer> list) {
        if (null == list || list.isEmpty()) {
            return -1;
        }
        int sum = list.stream().mapToInt(Integer::intValue).sum();
        if (sum < 1) {
            return -1;
        }
        int count = ThreadLocalRandom.current().nextInt(sum);
        for (int i = 0; i < list.size(); i++) {
            count -= list.get(i);
            if (count < 0) {
                return i;
            }
        }
        return -1;
    }
}
