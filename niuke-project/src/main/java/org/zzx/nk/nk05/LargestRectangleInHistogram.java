package org.zzx.nk.nk05;

import java.util.Stack;

/**
 * 柱状图中最大的矩形
 * 问题： 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，找到柱状图中最大的矩形面积。
 *
 * 核心思路
 * - 使用栈来存储柱子的索引，利用单调递增栈的特性，保证栈中的柱子高度是递增的。当遇到一个比栈顶柱子低的柱子时，
 *   意味着可以计算以栈顶柱子高度为高度的矩形面积，因为当前柱子的高度限制了栈顶柱子的扩展。
 *
 * 步骤解析
 * 初始化：
 * - 使用一个栈来存储柱子的索引。
 * - 创建一个新的高度数组，比原数组多一个元素，并将这个新元素的高度设为0。这是为了方便处理最后剩余的柱子。
 *
 * 遍历高度数组：
 * - 对于每个柱子，如果当前柱子的高度小于栈顶柱子的高度，开始计算矩形面积：
 * - 弹出栈顶索引，并计算以这个柱子的高度为高度的矩形面积。
 * - 如果栈为空，说明这个柱子可以扩展到当前索引的位置，否则只能扩展到栈顶索引的位置。
 * - 将当前柱子的索引压入栈中。
 *
 * 计算矩形面积：
 * - 在计算矩形面积时，使用已弹出的栈顶索引所对应的柱子的高度，结合当前索引和新的栈顶索引之间的距离计算宽度。
 *
 * 返回结果：
 * - 遍历结束后，返回计算得到的最大矩形面积。
 */
public class LargestRectangleInHistogram {
    // 计算直方图中最大的矩形面积
    public int largestRectangleArea(int[] heights) {
        Stack<Integer> stack = new Stack<>(); // 栈用于存储柱子的索引
        int maxArea = 0; // 最大矩形面积
        int[] newHeights = new int[heights.length + 1]; // 新的高度数组，多加一个0高度的柱子
        System.arraycopy(heights, 0, newHeights, 0, heights.length); // 将原数组复制到新的数组中
        newHeights[heights.length] = 0; // 新数组的最后一个元素设为0，方便计算最后剩余的柱子

        // 遍历新的高度数组
        for (int i = 0; i < newHeights.length; i++) {
            // 当栈不为空且当前高度小于栈顶的高度时，计算矩形面积
            while (!stack.isEmpty() && newHeights[i] < newHeights[stack.peek()]) {
                int height = newHeights[stack.pop()]; // 获取并移除栈顶的高度
                int width = stack.isEmpty() ? i : i - stack.peek() - 1; // 计算矩形的宽度
                maxArea = Math.max(maxArea, height * width); // 更新最大矩形面积
            }
            stack.push(i); // 将当前索引压入栈中
        }

        return maxArea; // 返回最大矩形面积
    }

    public static void main(String[] args) {
        LargestRectangleInHistogram solver = new LargestRectangleInHistogram();
        int[] heights = {2, 1, 5, 6, 2, 3}; // 测试数组
        System.out.println(solver.largestRectangleArea(heights)); // 输出结果：10
    }
    //       *
    //     * *
    //     * *
    //     * *   *
    // *   * * * *
    // * * * * * *
}
