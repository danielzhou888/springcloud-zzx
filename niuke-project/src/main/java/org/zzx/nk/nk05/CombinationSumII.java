package org.zzx.nk.nk05;

import java.util.*;

/**
 * 组合总和 II
 * 问题： 给定一个数组 candidates 和一个目标数 target，找出 candidates 中所有可以使数字和为 target 的组合。candidates 中的每个数字在每个组合中只能使用一次。
 */
public class CombinationSumII {
    
    // 主方法，找到所有和为目标值的组合
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>(); // 保存结果的列表
        Arrays.sort(candidates); // 将候选数组排序，以便于处理重复元素
        backtrack(result, new ArrayList<>(), candidates, target, 0); // 调用回溯方法找到所有组合
        return result; // 返回结果列表
    }

    // 回溯方法
    private void backtrack(List<List<Integer>> result, List<Integer> tempList, int[] candidates, int remain, int start) {
        // 如果剩余值小于0，直接返回
        if (remain < 0) return;
        // 如果剩余值为0，找到一个有效组合，加入结果列表
        else if (remain == 0) result.add(new ArrayList<>(tempList));
        else {
            // 遍历候选数组，从当前索引开始
            for (int i = start; i < candidates.length; i++) {
                // 跳过重复元素
                if (i > start && candidates[i] == candidates[i - 1]) continue;
                tempList.add(candidates[i]); // 将当前元素添加到临时列表
                // 递归调用回溯方法，更新剩余值和起始索引
                backtrack(result, tempList, candidates, remain - candidates[i], i + 1);
                tempList.remove(tempList.size() - 1); // 回溯，从临时列表中移除当前元素
            }
        }
    }

    // 主方法，测试代码
    public static void main(String[] args) {
        CombinationSumII solver = new CombinationSumII(); // 创建 CombinationSumII 类的实例
        int[] candidates = {10, 1, 2, 7, 6, 1, 5}; // 输入数组
        int target = 8; // 目标值
        List<List<Integer>> result = solver.combinationSum2(candidates, target); // 调用 combinationSum2 方法获取组合结果
        // 输出结果
        for (List<Integer> list : result) {
            System.out.println(list); // 输出：[[1, 1, 6], [1, 2, 5], [1, 7], [2, 6]]
        }
    }
}
