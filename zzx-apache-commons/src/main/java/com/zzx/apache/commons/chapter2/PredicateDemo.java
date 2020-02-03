package com.zzx.apache.commons.chapter2;

import org.apache.commons.collections.Predicate;

import java.util.Collection;
import java.util.Iterator;

/**
 * @Copyright All Rights Reserved
 * @Company 叮当快药科技集团有限公司
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
