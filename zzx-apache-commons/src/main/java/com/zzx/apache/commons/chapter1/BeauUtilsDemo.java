package com.zzx.apache.commons.chapter1;

import lombok.Data;
import org.apache.commons.beanutils.BeanUtils;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Copyright All Rights Reserved
 * @Company 创盛视联数码科技（北京）有限公司   https://www.bokecc.com/
 * @Author Zhou / zzx
 * @Date 2019-05-25 16:09
 **/
public class BeauUtilsDemo {

    @Data
    public static class Person {
        private String name;
        private String email;
        private int age;

    }

    @Test
    public void beanT1()
        throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Person person = new Person();
        Person person1 = new Person();
        person.setAge(22);
        person.setName("tom");
        person.setEmail(".....");
        Person o = (Person)BeanUtils.cloneBean(person);

        Map<String, Object> map = new HashMap<>();
        map.put("age", 23);
        map.put("name", "zhouzhixiang");
        map.put("email", "xxxxxxx");
        BeanUtils.populate(person1, map);
        System.out.println(person1.getAge());
    }
}
