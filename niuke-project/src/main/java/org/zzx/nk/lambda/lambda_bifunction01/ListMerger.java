package org.zzx.nk.lambda.lambda_bifunction01;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.BiFunction;

/**
 * 案例5：合并两个列表
 * 问题：使用 BiFunction 合并两个列表。
 * 风格：将两个列表中的元素合并成一个新的列表
 */
public class ListMerger {
    public static void main(String[] args) {
        BiFunction<List<String>, List<String>, List<String>> mergeLists = (list1, list2) -> {
            List<String> mergedList = new ArrayList<>(list1);
            mergedList.addAll(list2);
            return mergedList;
        };

        List<String> list1 = Arrays.asList("apple", "banana");
        List<String> list2 = Arrays.asList("cherry", "date");
        List<String> mergedList = mergeLists.apply(list1, list2);

        System.out.println("Merged List: " + mergedList);
    }
    
    public void test() {
        List<String> list1 = Arrays.asList("apple", "banana");
        List<String> list2 = Arrays.asList("cherry", "date");
        
        List<String> mergedList = mergeTwoList(list1, list2);
        System.out.println("Merged List: "+mergedList);
    }

    private List<String> mergeTwoList(List<String> list1, List<String> list2) {
        return commonBiFunction((l1, l2) -> {
            List<String> mergedList = new ArrayList<>(l1);
            mergedList.addAll(l2);
            return mergedList;
        }, list1, list2);
    }

    private List<String> commonBiFunction(BiFunction<List<String>, List<String>, List<String>> biFunction, List<String> list1, List<String> list2) {
        return biFunction.apply(list1, list2);
    }

}