package org.zzx.nk.nk05;

/**
 * 在排序数组中查找元素的第一个和最后一个位置
 * 问题： 给定一个按照升序排列的整数数组 nums 和一个目标值 target，找出目标值在数组中的第一个和最后一个位置。
 */
public class FindFirstAndLastPosition {
    // 主方法，返回目标值在排序数组中的起始和结束位置
    public int[] searchRange(int[] nums, int target) {
        // 初始化结果数组，默认值为-1
        int[] result = {-1, -1};
        // 查找目标值的起始位置
        int left = findBound(nums, target, true);
        // 如果起始位置为-1，说明数组中没有目标值，直接返回结果
        if (left == -1) return result;
        // 查找目标值的结束位置
        int right = findBound(nums, target, false);
        // 设置结果数组的起始和结束位置
        result[0] = left;
        result[1] = right;
        // 返回结果数组
        return result;
    }

    // 辅助方法，查找目标值的边界位置
    private int findBound(int[] nums, int target, boolean isFirst) {
        int left = 0, right = nums.length - 1;
        // 二分查找
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                if (isFirst) {
                    // 查找起始位置
                    if (mid == left || nums[mid - 1] != target) {
                        return mid;
                    }
                    // 继续在左半部分查找
                    right = mid - 1;
                } else {
                    // 查找结束位置
                    if (mid == right || nums[mid + 1] != target) {
                        return mid;
                    }
                    // 继续在右半部分查找
                    left = mid + 1;
                }
            } else if (nums[mid] < target) {
                // 目标值在右半部分
                left = mid + 1;
            } else {
                // 目标值在左半部分
                right = mid - 1;
            }
        }
        // 如果没有找到目标值，返回-1
        return -1;
    }
}
