package org.zzx.nk.nk03;

import java.util.Arrays;

/**
 * 插入排序（Insertion Sort）是一种简单直观的排序算法，其基本思想是通过构建有序序列，对于未排序数据，
 * 在已排序序列中从后向前扫描，找到相应位置并插入。插入排序适合于少量数据的排序，时间复杂度为 O(n^2)。
 *
 * 插入排序的主要步骤如下：
 * 初始状态：假设数组长度为 n，待排序数组为 arr。
 * 从第一个元素开始，认为它是已经排好序的。
 * 从第二个元素开始，依次将每个元素插入到已排序部分的适当位置。
 * 重复上述步骤，直到所有元素都插入到正确的位置。
 */
public class InsertionSort {
    /**
     * 插入排序算法
     * @param arr 要排序的数组
     */
    public static void insertionSort(int[] arr) {
        // 检查数组是否为空或长度为0
        if (arr == null || arr.length == 0) {
            return; // 如果数组为空或长度为0，直接返回
        }

        int n = arr.length; // 获取数组的长度

        // 外层循环控制要插入的元素
        for (int i = 1; i < n; i++) {
            int key = arr[i]; // 当前要插入的元素
            int j = i - 1; // 已排序部分的末尾索引

            // 内层循环在已排序部分中寻找插入位置
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j]; // 向后移动元素
                j = j - 1; // 向前移动索引
            }
            arr[j + 1] = key; // 将key插入到正确位置
        }
    }

    public static void main(String[] args) {
        int[] arr = {12, 11, 13, 5, 6}; // 示例数组
        insertionSort(arr); // 调用插入排序算法对数组进行排序
        System.out.println(Arrays.toString(arr)); // 输出排序后的数组结果，输出：[5, 6, 11, 12, 13]
    }
}
