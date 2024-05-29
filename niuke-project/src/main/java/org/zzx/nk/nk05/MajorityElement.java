package org.zzx.nk.nk05;

/**
 * 求众数
 * 问题： 给定一个大小为 n 的数组，找出其中的众数。众数是指在数组中出现次数大于 n/2 的元素。
 */
public class MajorityElement {
    // 主方法，找到数组中的众数
    public int majorityElement(int[] nums) {
        int count = 0; // 计数器，用于记录当前候选元素的出现次数
        Integer candidate = null; // 当前候选的众数
        // 遍历数组中的每个元素
        for (int num : nums) {
            // 如果计数器为0，则将当前元素作为候选元素
            if (count == 0) {
                candidate = num;
            }
            // 如果当前元素等于候选元素，计数器加1，否则计数器减1
            count += (num == candidate) ? 1 : -1;
        }
        // 返回候选元素，即为众数
        return candidate;
    }

    // 测试主方法
    public static void main(String[] args) {
        MajorityElement solver = new MajorityElement();
        // 示例测试用例
        int[] nums = {2, 1, 3, 2, 2,1,1,1, 2, 1, 1, 2, 2};
        // 调用众数方法
        int result = solver.majorityElement(nums);
        // 输出结果
        System.out.println("数组中的众数是：" + result); // 输出: 2
    }
}
