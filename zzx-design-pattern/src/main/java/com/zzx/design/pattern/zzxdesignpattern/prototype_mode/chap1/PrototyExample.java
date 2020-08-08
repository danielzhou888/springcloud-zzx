package com.zzx.design.pattern.zzxdesignpattern.prototype_mode.chap1;

import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * 原型模式
 * <p>
 * 我们先来了解下原型模式的实现。原型模式是通过给出一个原型对象来指明所创建的对象的类型，然后使用自身实现的克隆接口来复制这个原型对象，
 * 该模式就是用这种方式来创建出更多同类型的对象。使用这种方式创建新的对象的话，就无需再通过 new 实例化来创建对象了。
 * 这是因为 Object 类的 clone 方法是一个本地方法，它可以直接操作内存中的二进制流，所以性能相对 new 实例化来说，更佳。
 * </p>
 * @author zhouzhixiang
 * @Date 2020-08-03
 */
public class PrototyExample {

    private static final Long cnt = 10000000L;

    public static void main(String[] args) {
        PrototyExample example = new PrototyExample();
        example.testPrototypePerformance();
        //example.testNewPerformance();
    }

    /**
     * new 对象 测试耗时
     */
    public void testNewPerformance() {
        List list = new ArrayList<>();
        long start = System.currentTimeMillis();
        for (int i = 0; i < cnt; i++) {
            Student student = new Student();
            student.setId(Long.valueOf(i));
            student.setName("stu = " + i);
            list.add(student);
        }
        long end = System.currentTimeMillis();
        System.out.println("new contractor cost = " + (end - start));
    }

    /**
     * 原型模式：通过clone方法 测试耗时
     */
    public void testPrototypePerformance() {
        List list = new ArrayList<>();
        long start = System.currentTimeMillis();
        Student stu = new Student();
        for (int i = 0; i < cnt; i++) {
            Student student = stu.clone();
            student.setId(Long.valueOf(i));
            student.setName("stu = " + i);
            list.add(student);
        }
        long end = System.currentTimeMillis();
        System.out.println("prototype mode cost = " + (end - start));
    }

    @Data
    @ToString
    class Student implements Cloneable {
        private Long id;
        private String name;

        public Student(Long id, String name) {
            this.id = id;
            this.name = name;
        }

        public Student() {

        }

        @Override
        public Student clone() {
            try {
                return (Student) super.clone();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
            return null;
        }


    }
}
