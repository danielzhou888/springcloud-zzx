package com.zzx.apache.commons.chapter1;

import lombok.Data;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Transformer;
import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * <p>
 * 通过apache-commons包中的org.apache.commons.collections.CollectionUtils集合操作工具类 对集合间进行合并union、交叉intersection、分离disjunction、减去subtract、任意包含containsAny、
 * 判断是否为子集isSubCollection、颠倒序列reverseArray及判断是否填满isFull等操作。
 * </p>
 **/
public class CollectionsUtilsDemo {

    /** Arrays.asList()返回的是Arrays内部类ArraysList，不可对其进行add、remove等操作，返回报UnsupportedOperationException */
    /** java.util.ArrayList和Arrays内部类ArraysList都继承AbstractList，remove、add等方法AbstractList中是默认throw UnsupportedOperationException而且不作任何操作。*/
    /** java.util.ArrayList重新了这些方法而Arrays的内部类ArrayList没有重新，所以会抛出异常。*/
    // @Deprecated
    // private static List<String> list1 = Arrays.asList(new String[] {"1", "2", "3", "1"});
    // @Deprecated
    // private static List<String> list2 = Arrays.asList(new String[] {"2", "3", "4"});
    // @Deprecated
    // private static List<String> list3 = Arrays.asList(new String[] {"1", "2"});
    /** 解决 */
    private static List<String> list1 = new ArrayList<>(Arrays.asList(new String[] {"1", "2", "3", "1", "5"}));
    private static List<String> list2 = new ArrayList<>(Arrays.asList(new String[] {"2", "3", "1"}));
    private static List<String> list3 = new ArrayList<>(Arrays.asList(new String[] {"1", "2"}));

    @Data
    class Employee {
        private String name;
        private String email;
        private int age;
        private double salary;
        /** 是否在职 */
        private boolean activeEmployee;

        public Employee(String name, String email, int age, double salary, boolean activeEmployee) {
            this.name = name;
            this.email = email;
            this.age = age;
            this.salary = salary;
            this.activeEmployee = activeEmployee;
        }
    }

    /**
     * 判断两个集合是否和相同元素
     */
    public void containsAnyT1() {
        // 判断两个集合是否和相同元素
        boolean b = CollectionUtils.containsAny(list1, list2);
        System.out.println(b);
    }

    /**
     * 得到两个集合中相同的元素
     */
    @Test
    public void intersectionT1() {
        Collection b = CollectionUtils.intersection(list1, list2);
        System.out.println(b);
    }

    /**
     * 合并两个集合，不去重
     */
    @Test
    public void unionT1() {
        Collection b = CollectionUtils.union(list1, list2);
        System.out.println(b);
    }

    /**
     * 取两个集合差集，不去重
     */
    @Test
    public void disjunctionT1() {
        Collection b = CollectionUtils.disjunction(list1, list2);
        System.out.println(b);
    }

    /**
     * list1 - list2 = 剩余元素组成的集合
     */
    @Test
    public void subtractT1() {
        // Collection b = CollectionUtils.subtract(list1, list2);
        Collection b = CollectionUtils.subtract(list2, list1);
        System.out.println(b);
    }

    /**
     * 统计集合中各元素出现的次数，并Map<Object, Integer>输出
     */
    @Test
    public void getCardinalityMapT1() {
        Map cardinalityMap = CollectionUtils.getCardinalityMap(list1);
        cardinalityMap.forEach((k, v) -> System.out.println(k + ":" + v));
    }

    /**
     * a是否b集合子集，a集合大小<=b集合大小
     */
    @Test
    public void isSubCollectionT1() {
        // boolean subCollection = CollectionUtils.isSubCollection(list3, list1);
        boolean subCollection = CollectionUtils.isSubCollection(list3, list2);
        System.out.println(subCollection);
    }

    /**
     * a是否b集合子集，a集合大小<b集合大小
     */
    @Test
    public void isProperSubCollectionT1() {
        // boolean subCollection = CollectionUtils.isSubCollection(list3, list1);
        boolean subCollection = CollectionUtils.isProperSubCollection(list3, list2);
        System.out.println(subCollection);
    }

    /**
     * 两个集合是否相同
     */
    @Test
    public void isEqualCollectionT1() {
        boolean subCollection = CollectionUtils.isSubCollection(list1, list1);
        // boolean subCollection = CollectionUtils.isEqualCollection(list3, list2);
        System.out.println(subCollection);
    }

    /**
     * 某元素在集合中出现的次数
     */
    @Test
    public void cardinalityT1() {
        int cardinality = CollectionUtils.cardinality("1", list1);
        System.out.println(cardinality);
    }

    /**
     * 返回集合中满足函数式的唯一元素，只返回最先处理符合条件的唯一元素
     */
    @Test
    public void findT1() {
        Object o = CollectionUtils.find(list1, e -> Integer.parseInt(e.toString()) > 2);
        System.out.println(o.toString());
    }

    /**
     * 对集合中的对象中的某一属性进行批量更新，closure为需要更新的属性对象
     * 如对集合中所有员工的加薪20%
     */
    @Test
    public void forAllDoT1() {
        // // create the closure
        // List<Employee> employees = new ArrayList<>();
        // Employee e1 = new Employee("e1", "e1.com", 21, 10000, true);
        // Employee e2 = new Employee("e2", "e2.com", 22, 14000, false);
        // Employee e3 = new Employee("e3", "e3.com", 23, 12000, true);
        // Employee e4 = new Employee("e4", "e4.com", 21, 12000, true);
        // Closure<E> closure = new Closure() {
        //     @Override
        //     public void execute(Employee e) {
        //         e.setSalary(e.getSalary() * 1.2);
        //     }
        // };

    }

    /**
     * 过滤集合中满足函数式的所有元素
     */
    @Test
    public void filterT1() {
        CollectionUtils.filter(list1, e -> Integer.parseInt(e.toString()) > 1);
        list1.forEach(s -> {
            System.out.println(s);
        });
    }

    /**
     * 转换新的集合，对集合中元素进行操作，如每个元素都累加1
     */
    @Test
    public void transformT1() {

        CollectionUtils.transform(list1, new Transformer() {
            @Override
            public Object transform(Object o) {
                Integer num = Integer.parseInt((String)o);
                return String.valueOf(++num);
            }
        });
        list1.forEach(s -> {
            System.out.println(s);
        });

        System.out.println("============================");

        // JDK8
        List<String> temp = new ArrayList<>();
        list1.stream().forEach(i -> {
            int num = Integer.parseInt(i);
            temp.add(String.valueOf(num));
        });
        temp.forEach(System.out::println);
    }

    /**
     * 返回集合中满足函数式的数量
     */
    @Test
    public void countMatchesT1() {
        int num = CollectionUtils.countMatches(list1, i -> Integer.parseInt((String)i) > 0);
        System.out.println(num);
    }

    /**
     * 将满足表达式的元素存入新集合中并返回新集合元素对象
     */
    @Test
    public void selectT1() {
        Collection select = CollectionUtils.select(list1, i -> Integer.parseInt((String)i) > 2);
        select.forEach(System.out::println);
    }

    /**
     * 将不满足表达式的元素存入新集合中并返回新集合元素对象
     */
    @Test
    public void selectRejectedT1() {
        Collection select = CollectionUtils.selectRejected(list1, i -> Integer.parseInt((String)i) > 2);
        select.forEach(System.out::println);
    }

    /**
     * collect底层调用的transform方法
     * 将所有元素进行处理，并返回新的集合
     */
    @Test
    public void collectT1() {
        Collection collecttion = CollectionUtils.collect(list1, new Transformer() {
            @Override
            public Object transform(Object o) {
                int i = Integer.parseInt((String)o);
                return ++i;
            }
        });
        collecttion.forEach(System.out::println);
    }

    /**
     * 将一个数组或集合中的元素全部添加到另一个集合中
     */
    @Test
    public void adAllT1() {
        CollectionUtils.addAll(list1, new String[]{"5", "6"});
        CollectionUtils.addAll(list1, list2.toArray());
        list1.forEach(System.out::println);
    }

    /**
     * 返回集合中指定下标元素
     */
    @Test
    public void indexT1() {
        String index = (String)CollectionUtils.index(list1, 2);
        System.out.println(index);
    }

    /**
     * 返回集合中指定下标元素
     */
    @Test
    public void getT1() {
        String index = (String)CollectionUtils.get(list1, 2);
        System.out.println(index);
    }

    /**
     * 判断集合是否为空
     */
    @Test
    public void isEmptyT1() {
        int[] arr = new int[5];
        arr[0] = 1;
        arr[1] = 1;
        arr[2] = 1;
        arr[3] = 1;
        arr[4] = 1;
        boolean empty = CollectionUtils.isFull(new ArrayList(Arrays.asList(arr)));
        System.out.println(empty);
    }

    /**
     * 判断集合是否为空
     */
    @Test
    public void isFullT1() {
        boolean full = CollectionUtils.isFull(list1);
        System.out.println(full);
    }

    /**
     * 返回集合最大空间
     */
    @Test
    public void maxSizeT1() {
        // List<Integer> boundedList = new ArrayList<>(8);
        // int i = CollectionUtils.maxSize(boundedList);
        // System.out.println(i);
    }

    /**
     * 只要集合中元素不满足表达式就抛出异常
     */
    @Test
    public void predicatedCollectionT1() {
        Collection collection = CollectionUtils.predicatedCollection(list1, i -> Integer.parseInt((String)i) > 1);
        collection.forEach(System.out::println);
    }

    /**
     * 只要集合中元素不满足表达式就抛出异常
     */
    @Test
    public void removeAllT1() {
        boolean b = list1.removeAll(list2);
        System.out.println(b);
        list1.forEach(System.out::println);
    }

    /**
     * 只要集合中元素不满足表达式就抛出异常
     */
    @Test
    public void synchronizedCollectionT1() {
        Collection collection = CollectionUtils.synchronizedCollection(list1);
        collection.forEach(System.out::println);
    }

    /**
     * 只要集合中元素不满足表达式就抛出异常
     */
    @Test
    public void unmodifiedCollectionT1() {
        Collection collection = CollectionUtils.unmodifiableCollection(list1);
        collection.forEach(System.out::println);
    }

    /**
     * 只要集合中元素不满足表达式就抛出异常
     */
    @Test
    public void predicatedCollectionT2() {
        // Collection collection = CollectionUtils.predicatedCollection(list1, i -> Integer.parseInt((String)i) > 0);
        // Collection collection = CollectionUtils.typedCollection(list1, String.class);
        Collection collection = CollectionUtils.transformedCollection(list1, new Transformer() {
            @Override
            public Object transform(Object o) {
                int n = Integer.parseInt((String)o);
                return n + n;
            }
        });
        collection.forEach(System.out::println);
    }

}
