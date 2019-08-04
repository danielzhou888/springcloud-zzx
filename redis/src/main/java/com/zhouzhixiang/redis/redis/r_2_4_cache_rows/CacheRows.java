package com.zhouzhixiang.redis.redis.r_2_4_cache_rows;

import com.alibaba.fastjson.JSONObject;
import com.zhouzhixiang.redis.redis.utils.RedisUtil;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * 数据行缓存：通过缓存页面载入时所需的数据库行来减少载入页面所需的时间<br>
 * 1. 促销活动，用户非常多的情况下，建议每隔几秒更新一次数据行缓存；<br>
 * 2. 并发不高，商品显示缺货可以接受，建议每隔一分钟更新一次数据行缓存
 * @Copyright All Rights Reserved
 * @Company 创盛视联数码科技（北京）有限公司   https://www.bokecc.com/
 * @Author Daniel Zhou / zzx
 * @Date 2019-07-14 21:38
 **/
@Component
public class CacheRows {

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 调度
     * @param rowId
     * @param delay
     */
    public void scheduleRowCache(String rowId, Long delay) {
        redisUtil.zAdd("delay:", rowId, delay);
        redisUtil.zAdd("schedule:", rowId, System.currentTimeMillis());
    }

    /**
     * 守护进程，轮询更新缓存
     */
    public void cacheRowsScheduled() {
        while (true) {
            Set<ZSetOperations.TypedTuple<String>> typedTuples = redisUtil.zRangeWithScores("schedule:", 0, -1);
            if (typedTuples == null || typedTuples.size() == 0) {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                continue;
            }
            typedTuples.forEach(c -> {
                String rowId = c.getValue();
                Double delay = redisUtil.zScore("delay:", rowId);
                if (delay <= 0) {
                    redisUtil.zRemove("delay:", rowId);
                    redisUtil.zRemove("schedule:", rowId);
                    redisUtil.delete("inv:" + rowId);
                }
                // Row row = db.getRow(rowId);
                Row row = new Row();
                redisUtil.zAdd("schedule:", rowId, System.currentTimeMillis() + delay);
                redisUtil.set("inv:" + rowId, JSONObject.toJSONString(row));
            });

        }
    }

    @Data
    class Row {
        private String rowId;
        private String cartId;
        private Integer type;

        public Row() {
        }
    }
}
