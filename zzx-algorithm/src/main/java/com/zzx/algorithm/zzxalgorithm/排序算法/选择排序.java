package com.zzx.algorithm.zzxalgorithm.排序算法;

import java.util.Arrays;

/**
 * 1. 基本思想
 * 首先在未排序序列中找到最小（大）元素，存放到排序序列的起始位置，然后，再从剩余未排序元素中继续寻找最小（大）元素，然后放到已排序序列的末尾。以此类推，直到所有元素均排序完毕。
 *
 * 选择排序的思想其实和冒泡排序有点类似，都是在一次排序后把最小的元素放到最前面，或者将最大值放在最后面。但是过程不同，冒泡排序是通过相邻的比较和交换。
 * 而选择排序是通过对整体的选择，每一趟从前往后查找出无序区最小值，将最小值交换至无序区最前面的位置。
 *
 * 2. 实现逻辑
 * ① 第一轮从下标为 1 到下标为 n-1 的元素中选取最小值，若小于第一个数，则交换
 * ② 第二轮从下标为 2 到下标为 n-1 的元素中选取最小值，若小于第二个数，则交换
 * ③ 依次类推下去……
 *
 * 4. 复杂度分析
 *
 * 平均时间复杂度：O(N^2)
 * 最佳时间复杂度：O(N^2)
 * 最差时间复杂度：O(N^2)
 * 空间复杂度：O(1)
 * 排序方式：In-place
 * 稳定性：不稳定
 * 选择排序的交换操作介于和(n-1)次之间。选择排序的比较操作为n(n-1)/2次之间。选择排序的赋值操作介于0和3(n-1)次之间。
 *
 * 比较次数O(n^2)，比较次数与关键字的初始状态无关，总的比较次数N = (n-1) + (n-2) +…+ 1 = n x (n-1)/2。交换次数O(n)，最好情况是，已经有序，交换0次；
 * 最坏情况是，逆序，交换n-1次。
 */
public class 选择排序 {

    /**
     * 普通版-选择排序
     * @param arr
     */
    public void sort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        for (int i = 0; i < arr.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[min]) {
                    min = j;
                }
            }
            if (min != i) {
                int temp = arr[i];
                arr[i] = arr[min];
                arr[min] = temp;
            }
        }
    }

    /**
     * 二元选择排序算法
     *
     * 使用二元选择排序，每轮选择记录最小值和最大值，可以把数组需要遍历的最大值最小值缩小一倍。将最小值交换到首位，最大值交换到末尾
     * @param arr
     */
    public void sort_选择排序优化版_二元选择排序算法(int[] arr) {
        int maxIndex, minIndex;
        int len = arr.length;
        for (int i = 0; i < len / 2; i++) {
            maxIndex = i;
            minIndex = i;
            for (int j = i + 1; j < len; j++) {
                if (arr[j] > arr[maxIndex]) {
                    maxIndex = j;
                }
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            // 最大值和最小值相等
            if (maxIndex == minIndex) {
                break;
            }
            int temp1 = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp1;

            // 如果最大值索引刚好是i处，则需要更新maxIndex，因为i和minIndex的值交换了
            if (maxIndex == i) {
                maxIndex = minIndex;
            }
            // 交换最大值，保持右边最值有序
            int temp2 = arr[maxIndex];
            arr[maxIndex] = arr[len - i - 1];
            arr[len - i - 1] = temp2;
        }
    }

}
