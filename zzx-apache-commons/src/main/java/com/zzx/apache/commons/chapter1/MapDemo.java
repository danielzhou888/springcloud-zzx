package com.zzx.apache.commons.chapter1;

import org.apache.commons.collections.BidiMap;
import org.apache.commons.collections.OrderedMap;
import org.apache.commons.collections.bidimap.TreeBidiMap;
import org.apache.commons.collections.map.LinkedMap;

/**
 * @Copyright All Rights Reserved
 * @Company 叮当快药科技集团有限公司
 * @Author Zhou / zzx
 * @Date 2019-05-25 17:12
 **/
public class MapDemo {

    /**
     * 得到集合里按顺序存放的key之后的某一Key
     */
    public void orderedMapT1() {

        OrderedMap map = new LinkedMap();
        map.put("FIVE", "5");
        map.put("SIX", "6");
        map.put("SEVEN", "7");
        // return "FIVE"
        map.firstKey();
        // return "SIX"
        map.nextKey("FIVE");
        // return SEVEN
        map.nextKey("SIX");
    }
    /**
     * 通过key得到value
     * 通过value得到key
     * 将map里的key和value对调
     */
    public void bidiMapT1() {

        BidiMap bidiMap = new TreeBidiMap();
        bidiMap.put("SIX", "6");
        bidiMap.get("SIX");  // returns "6"
        bidiMap.get("6"); //return "SIX"
        // bidiMap.removeValue("6");
        BidiMap bidiMap1 = bidiMap.inverseBidiMap();
        System.out.println(bidiMap1);
    }
}
