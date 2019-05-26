package com.zzx.apache.commons.chapter2;

import org.apache.commons.collections.Predicate;

import java.util.Collection;
import java.util.Iterator;

/**
 * @Copyright All Rights Reserved
 * @Company 创盛视联数码科技（北京）有限公司   https://www.bokecc.com/
 * @Author Zhou / zzx
 * @Date 2019-05-25 18:55
 **/
public class PredicateDemo {

    public static void filter(Collection coll, Predicate predicate) {
        if (coll != null && predicate != null) {
            Iterator iterator = coll.iterator();
            while (iterator.hasNext()) {
                Object next = iterator.next();
                if (!predicate.evaluate(next)) {
                    iterator.remove();
                }
            }
        }
    }
}
