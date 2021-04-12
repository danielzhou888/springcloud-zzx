package com.zzx.custom.strategy;

import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

import java.util.Collection;

/**
 * @author zhouzhixiang
 * @Date 2020-10-28
 */
public class CustomInlineStrategy implements PreciseShardingAlgorithm<Long> {

    private static String PREFIX_TABLE_NAME = "c_pharmacy_goods_temp_";
    //private static String PREFIX_TABLE_NAME = "c_pharmacy_goods_";

    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<Long> preciseShardingValue) {
        StringBuilder sb = new StringBuilder();
        if (preciseShardingValue != null) {
            Long value = preciseShardingValue.getValue();
            if (value != null && value < 0) {
                value = Math.abs(value);
            }
            return sb.append(PREFIX_TABLE_NAME).append(value % 10).toString();
        } else {
            throw new UnsupportedOperationException("分片列为空");
        }
    }
}
