package com.zzx.apache.commons.chapter1;

import org.apache.commons.beanutils.ConvertUtils;
import org.junit.jupiter.api.Test;

import java.util.Date;

/**
 * @Copyright All Rights Reserved
 * @Company 创盛视联数码科技（北京）有限公司   https://www.bokecc.com/
 * @Author Zhou / zzx
 * @Date 2019-05-25 19:29
 **/
public class ConvertUtilsDemo {


    @Test
    public void test1() {
        Date convert = (Date)ConvertUtils.convert("2019-05-25", Date.class);
        System.out.println(convert.getTime());
    }
}
