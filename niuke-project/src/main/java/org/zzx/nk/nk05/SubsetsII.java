package org.zzx.nk.nk05;

import java.util.*;

/**
 * 子集 II
 * 问题： 给定一个可能包含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 */
public class SubsetsII {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(result, new ArrayList<>(), nums, 0);
        return result;
    }

    private void backtrack(List<List<Integer>> result, List<Integer> tempList, int[] nums, int start) {
        result.add(new ArrayList<>(tempList));
        for (int i = start; i < nums.length; i++) {
            if (i > start && nums[i] == nums[i - 1]) {
                continue;
            }
            tempList.add(nums[i]);
            backtrack(result, tempList, nums, i + 1);
            tempList.remove(tempList.size() - 1);
        }

    }

    public static void main(String[] args) {
        SubsetsII solver = new SubsetsII();
        int[] nums = {1, 2, 2};
        List<List<Integer>> result = solver.subsetsWithDup(nums);
        for (List<Integer> list : result) {
            System.out.println(list);
            // Output: [[], [1], [1, 2], [1, 2, 2], [2], [2, 2]]
        }
    }
}
