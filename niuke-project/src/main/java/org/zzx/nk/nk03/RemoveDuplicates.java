package org.zzx.nk.nk03;

import java.util.Arrays;

/**
 * 5. 删除数组中的重复元素
 * 题目：编写一个函数来删除数组中的重复元素，并返回新的数组长度。
 */
public class RemoveDuplicates {
    // 定义静态方法removeDuplicates，用于移除数组中的重复元素
    public static int removeDuplicates(int[] nums) {
        // 若数组为空或长度为0，则直接返回0
        if (nums == null || nums.length == 0) {
            return 0;
        }
        // 定义变量i表示不重复元素的索引，初始值为0
        int i = 0;
        // 遍历数组，从索引1开始，索引0已经是不重复的
        for (int j = 1; j < nums.length; j++) {
            // 若当前元素与前一个元素不相等，则表示找到了新的不重复元素
            if (nums[j] != nums[i]) {
                // 将该不重复元素放到数组中不重复元素的下一个位置
                i++;
                nums[i] = nums[j];
            }
        }
        // 返回不重复元素的个数，因为索引是从0开始的，所以需要加1
        return i + 1;
    }

    public static void main(String[] args) {
        // 定义测试数组
        int[] nums = {1, 1, 2, 2, 3, 4, 4};
        // 调用removeDuplicates方法，获取不重复元素的个数
        int length = removeDuplicates(nums);
        // 输出不重复元素的个数
        System.out.println(length); // 输出: 4
        // 输出去重后的数组
        System.out.println(Arrays.toString(Arrays.copyOf(nums, length))); // 输出: [1, 2, 3, 4]
    }
}
