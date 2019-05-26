package com.zzx.guava.chapter1;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.primitives.Ints;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.util.*;
import java.util.stream.Collector;

/**
 * <p>Copyright: All Rights Reserved</p>
 * <p>Company: 创盛视联数码科技（北京）有限公司   https://www.bokecc.com/ </p>
 * <p>Description:  </p>
 */
public class ListAndMap {

    public void mapT1() {
        Map<String, Map<Long, List<String>>> map = Maps.newHashMap();
        HashMap<String, Map<Long, String>> objectObjectHashMap = newHash();

    }

    public static <K, V> HashMap<K, V> newHash() {
        return new HashMap();
    }

    public void immutalbeListT1() {
        List<String> list = new ArrayList<String>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");

        // replace
        ImmutableList<String> list1 = ImmutableList.of("a", "b", "c", "d");
        ImmutableMap<String, String> map = ImmutableMap.of("k1", "v1", "k2", "v2");

    }

    @Test
    public void listT2() {
        // 比较两个基本类型
        int compare = Ints.compare(1, 2);
        System.out.println(compare);

        // List转数组
        ImmutableList<String> of = ImmutableList.of("a", "b", "c", "d");
        String[] strings = (String[])of.toArray();

    }

    public void joinerT1() {
        String[] subdir = {"usr", "local", "lib"};
        String directory = Joiner.on("/").join(subdir);

        int[] numbers = {1, 2, 3, 4, 5};
        String numberAsString = Joiner.on(";").join(Ints.asList(numbers));
        // or
        String numberAsString2 = Ints.join(";", numbers);
    }

    public void splitterT1() {
        int[] numbers = {1, 2, 3, 4, 5};
        String numberAsString = Joiner.on(";").join(Ints.asList(numbers));

        Iterable<String> split = Splitter.on(";").split(numberAsString);
        // or
        String[] split1 = numberAsString.split(";");

        String testString = "foo , what,,,more,";
        Iterable<String> split2 = Splitter.on(",").omitEmptyStrings().trimResults().split(testString);

    }

    @Test
    public void intsT1() {
        // 假如我有一个整型数字数组，我们想知道数组中是否有特定的整型数字。传统的写法如下：
        int[] array = { 1, 2, 3, 4, 5 };
        int[] array2 = { 6, 7 };
        int a = 4;
        boolean hasA = false;
        for (int i : array) {
            if (i == a) {
                hasA = true;
            }
        }

        // guava
        boolean contains = Ints.contains(array, a);

        int index = Ints.indexOf(array, a);
        int max = Ints.max(array);
        int min = Ints.min(array);
        int[] concat = Ints.concat(array, array2);

    }

    public void transT1() {

        // 1	topMap = Maps.transformValues(fromMap, function);
        // 2	toList = Lists.transform(fromList, function);
        // 举个例子来说，假设你有一个Map，key是物品，value是对应的价格，单位是欧元。那么，你有个需求是将里面的价格都转换为美元，传统的做法是遍历整个Map，然后更新每个value值，将价格转换为美元价格，好麻烦...
        // 有了Functions，世界一下子变清净了
        Map<String, Double> doubleMap = Maps.newHashMap();
        Map<String, Double> map = Maps.transformValues(doubleMap, new Function<Double, Double>() {
            double enrToUsd = 1.4888;
            @Nullable
            @Override
            public Double apply(@Nullable Double aDouble) {
                return aDouble * enrToUsd;
            }
        });

    }


}
