package org.zzx.nk.nk05;

import java.util.*;

/**
* 全排列 II
* 问题：给定一个可包含重复数字的序列，返回所有不重复的全排列。
*/
public class PermutationsII {
    
    // 主方法，生成全排列
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<>(); // 保存结果的列表
        Arrays.sort(nums); // 将输入数组排序，以便于处理重复元素
        boolean[] used = new boolean[nums.length]; // 用于标记每个元素是否已被使用
        backtrack(result, new ArrayList<>(), nums, used); // 调用回溯方法生成全排列
        return result; // 返回结果列表
    }

    // 回溯方法
    private void backtrack(List<List<Integer>> result, List<Integer> tempList, int[] nums, boolean[] used) {
        // 当临时列表的大小等于输入数组的大小时，表示找到一个完整排列
        if (tempList.size() == nums.length) {
            result.add(new ArrayList<>(tempList)); // 将当前排列添加到结果列表中
        } else {
            // 遍历输入数组的每一个元素
            for (int i = 0; i < nums.length; i++) {
                // 跳过已使用的元素，或跳过重复元素（如果前一个相同元素未使用则跳过）
                if (used[i] || (i > 0 && nums[i] == nums[i - 1] && !used[i - 1])) continue;
                used[i] = true; // 标记当前元素为已使用
                tempList.add(nums[i]); // 将当前元素添加到临时列表
                backtrack(result, tempList, nums, used); // 递归调用回溯方法
                used[i] = false; // 回溯，撤销当前元素的使用标记
                tempList.remove(tempList.size() - 1); // 回溯，从临时列表中移除当前元素
            }
        }
    }

    // 主方法，测试代码
    public static void main(String[] args) {
        PermutationsII solver = new PermutationsII(); // 创建 PermutationsII 类的实例
        int[] nums = {1, 1, 1,2,2,2}; // 输入数组
        List<List<Integer>> result = solver.permuteUnique(nums); // 调用 permuteUnique 方法获取全排列结果
        // 输出结果
        for (List<Integer> list : result) {
            System.out.println(list); // 输出：[[1, 1, 2], [1, 2, 1], [2, 1, 1]]
        }
    }
}
