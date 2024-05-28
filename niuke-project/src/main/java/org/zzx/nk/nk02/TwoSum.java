package org.zzx.nk.nk02;

import java.util.HashMap;
import java.util.Map;

/**
 * 8. 从数组中找出两个数之和为目标值
 * 题目描述：给定一个整数数组和一个目标值，找出数组中两个数的和为目标值的下标。
 */
public class TwoSum {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("No two sum solution");
    }

    public static void main(String[] args) {
        TwoSum twoSum = new TwoSum();
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        int[] result = twoSum.twoSum(nums, target);
        System.out.println("Indices: " + result[0] + ", " + result[1]); // Indices: 0, 1
    }
}