package com.java8.chapter1;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main1 {

    /**
     * 将实体集合转成Map<goodsId,GoodsVolume> map类型
     * 对于x -> x表示value为实体GoodsVolume
     * (k1, k2) -> k2，用于解决重复key问题，如果出现重复key，直接拿后者k2覆盖前者k1
     */
    public void test1() {
        List<GoodsVolume> list = new ArrayList<>();
//        list = queryFromXXX();
        list.stream().collect(Collectors.toMap(GoodsVolume::getGoodsId, x-> x,(k1, k2)->k2));
    }
}
