package org.zzx.nk.nk06;

/**
 *跳跃游戏 II
 *问题： 给定一个非负整数数组，你最初位于数组的第一个位置。数组中的每个元素代表你在该位置可以跳跃的最大长度。你的目标是使用最少的跳跃次数到达数组的最后一个位置。
 *
 * 算法思路
 * - 我们使用贪心算法来解决这个问题。核心思想是在遍历数组的过程中，不断更新当前可以到达的最远位置。当到达当前跳跃的边界时，增加跳跃次数，并更新新的跳跃边界。
 *
 * 详细步骤
 * 初始化变量：
 * - jumps：记录跳跃次数，初始值为0。
 * - currentEnd：当前跳跃的边界，初始值为0。
 * - farthest：当前可以到达的最远位置，初始值为0。
 *
 * 遍历数组：
 * - 我们只需要遍历到倒数第二个位置，因为在最后一个位置不需要再跳跃。
 * - 对于每个位置 i，更新 farthest 为 i + nums[i] 和当前 farthest 的最大值，即表示从当前位置能到达的最远位置。
 *
 * 检查是否到达当前跳跃的边界：
 * - 如果当前索引 i 等于 currentEnd，说明我们需要进行一次跳跃：
 * - 增加跳跃次数 jumps。
 * - 更新 currentEnd 为 farthest，即将当前跳跃的边界更新为目前能到达的最远位置。
 * - 如果 currentEnd 大于或等于数组的最后一个位置，则可以提前结束遍历，因为已经可以到达或超过最后一个位置。
 *
 * 返回跳跃次数：
 * - 遍历结束后，返回记录的跳跃次数 jumps。
*/
public class JumpGameII {
    // 计算到达数组最后一个位置的最少跳跃次数
    public int jump(int[] nums) {
        // 如果数组长度小于2，说明不需要跳跃，直接返回0
        if (nums.length < 2) {
            return 0;
        }
        
        int jumps = 0; // 跳跃次数
        int currentEnd = 0; // 当前跳跃的结束边界
        int farthest = 0; // 目前能到达的最远位置
        
        // 遍历数组，但不包括最后一个位置
        for (int i = 0; i < nums.length - 1; i++) {
            // 更新目前能到达的最远位置
            farthest = Math.max(farthest, i + nums[i]);
            
            // 如果到达当前跳跃的结束边界
            if (i == currentEnd) {
                jumps++; // 增加跳跃次数
                currentEnd = farthest; // 更新跳跃的结束边界
                
                // 如果当前跳跃的结束边界超过或到达最后一个位置，跳出循环
                if (currentEnd >= nums.length - 1) {
                    break;
                }
            }
        }
        return jumps; // 返回最少跳跃次数
    }

    public static void main(String[] args) {
        JumpGameII solver = new JumpGameII();
        int[] nums = {2, 3, 1, 1, 4}; // 测试数组
        System.out.println(solver.jump(nums)); // 输出结果：2
    }
}
