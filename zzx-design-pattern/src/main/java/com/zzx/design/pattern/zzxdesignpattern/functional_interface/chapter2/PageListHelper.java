package com.zzx.design.pattern.zzxdesignpattern.functional_interface.chapter2;

import javax.management.Query;
import javax.xml.transform.Result;
import java.util.List;

/**
 * @author zhouzhixiang
 * @company 叮当快药科技集团有限公司
 * @Date 2019-10-31
 */
@FunctionalInterface
public interface PageListHelper {
    List<Result> pageQuery(Query query);
}
