package org.zzx.nk.nk05;

import java.util.*;

/**
 * 合并区间
 * 问题：以数组 intervals 表示若干个区间的集合，请合并所有重叠的区间。
 */
public class MergeIntervals {
    // 主方法，用于合并重叠的区间
    public int[][] merge(int[][] intervals) {
        // 如果输入的区间为空，返回空的二维数组
        if (intervals.length == 0) {
            return new int[0][];
        }
        // 按照每个区间的起始位置进行排序
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        // 用于存储合并后的区间
        List<int[]> merged = new ArrayList<>();
        // 当前处理的区间
        int[] currentInterval = intervals[0];
        // 将第一个区间添加到合并后的列表中
        merged.add(currentInterval);
        // 遍历所有区间
        for (int[] interval : intervals) {
            // 如果当前区间的起始位置小于或等于上一个区间的结束位置，说明区间有重叠
            if (interval[0] <= currentInterval[1]) {
                // 合并区间，更新当前区间的结束位置
                currentInterval[1] = Math.max(currentInterval[1], interval[1]);
            } else {
                // 如果没有重叠，更新当前区间并添加到合并后的列表中
                currentInterval = interval;
                merged.add(currentInterval);
            }
        }
        // 将合并后的区间列表转换为二维数组并返回
        return merged.toArray(new int[merged.size()][]);
    }

    // 测试主方法
    public static void main(String[] args) {
        MergeIntervals solver = new MergeIntervals();
        // 示例测试用例
        int[][] intervals = {
            {1, 3},
            {2, 6},
            {8, 10},
            {15, 18}
        };
        // 调用合并区间方法
        int[][] result = solver.merge(intervals);
        // 输出结果
        System.out.println("合并后的区间：");
        for (int[] interval : result) {
            System.out.println(Arrays.toString(interval));
        }
    }
}
