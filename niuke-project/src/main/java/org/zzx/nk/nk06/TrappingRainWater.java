package org.zzx.nk.nk06;

/**
 * 接雨水
 * 问题： 给定一个非负整数数组表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 *
 * 代码思路
 * - 该算法的核心思想是使用双指针法，从数组的两端向中间移动，通过比较当前左右指针所指位置的高度，决定向哪一边移动。并在移动过程中更新左右两边的最大高度，并计算当前可以接住的雨水量。
 *
 * 详细步骤
 * 输入检查：
 * - 首先检查输入数组是否为空或长度为0。如果是，直接返回0，因为无法接住任何雨水。
 *
 * 初始化变量：
 * - left 和 right 分别指向数组的最左端和最右端。
 * - leftMax 和 rightMax 用于记录从左端和右端向中间移动过程中遇到的最大高度。
 * - water 用于累积计算接住的雨水总量。
 *
 * 循环遍历：
 * - 使用 while 循环，当 left 指针小于 right 指针时继续循环。
 *
 * 双指针移动和计算雨水量：
 * - 在每次循环中，比较 height[left] 和 height[right]。
 * - 如果 height[left] < height[right]：
 *    - 如果 height[left] 大于或等于 leftMax，更新 leftMax。
 *    - 否则，计算 leftMax 和 height[left] 之间的差值，这个差值就是当前位置能接住的雨水量，将其累加到 water 中。
 *    - 移动 left 指针向右。
 * - 如果 height[left] >= height[right]：
 *    - 如果 height[right] 大于或等于 rightMax，更新 rightMax。
 *    - 否则，计算 rightMax 和 height[right] 之间的差值，这个差值就是当前位置能接住的雨水量，将其累加到 water 中。
 *    - 移动 right 指针向左。
 *
 * 返回结果：
 * - 当 left 指针不再小于 right 指针时，循环结束，返回累积的 water 值，即接住的雨水总量。
 */
public class TrappingRainWater {
    // 计算可以接住的雨水总量
    public int trap(int[] height) {
        // 如果数组为空或长度为0，返回0
        if (height == null || height.length == 0) return 0;
        
        int left = 0, right = height.length - 1; // 初始化左右指针
        int leftMax = 0, rightMax = 0; // 初始化左边和右边的最大高度
        int water = 0; // 用于存储接住的雨水总量
        
        // 当左右指针未相遇时进行计算
        while (left < right) {
            // 如果左边的高度小于右边的高度
            if (height[left] < height[right]) {
                // 如果当前左边的高度大于或等于左边最大高度，更新左边最大高度
                if (height[left] >= leftMax) {
                    leftMax = height[left];
                } else {
                    // 否则，计算左边可以接的雨水量，并加到总水量中
                    water += leftMax - height[left];
                }
                left++; // 移动左指针
            } else {
                // 如果当前右边的高度大于或等于右边最大高度，更新右边最大高度
                if (height[right] >= rightMax) {
                    rightMax = height[right];
                } else {
                    // 否则，计算右边可以接的雨水量，并加到总水量中
                    water += rightMax - height[right];
                }
                right--; // 移动右指针
            }
        }
        return water; // 返回接住的雨水总量
    }

    public static void main(String[] args) {
        TrappingRainWater solver = new TrappingRainWater();
        int[] height = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}; // 测试数组
        System.out.println(solver.trap(height)); // 输出结果：6
    }
}
