package com.zzx.apache.commons.chapter1;

import lombok.Data;

import java.io.StringWriter;

/**
 * @Copyright All Rights Reserved
 * @Company 创盛视联数码科技（北京）有限公司   https://www.bokecc.com/
 * @Author Zhou / zzx
 * @Date 2019-05-25 16:42
 **/
public class BetwixtDemo {

    @Data
    static class Person{
        private String name;
        private int age;
        /** Need to allow bean to be created via reflection */
        public Person() {
        }
        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }
    }

    public static void main(String[] args) {
        // 先创建一个StringWriter，我们将把它写入为一个字符串
        StringWriter outputWriter = new StringWriter();
        // Betwixt在这里仅仅是将Bean写入为一个片断
        // 所以如果要想完整的XML内容，我们应该写入头格式
        outputWriter.write("<?xml version=’1.0′ encoding=’UTF-8′ ?>\n");
        // 创建一个BeanWriter，其将写入到我们预备的stream中
        // BeanWriter beanWriter = new BeanWriter(outputWriter);

    }
}
