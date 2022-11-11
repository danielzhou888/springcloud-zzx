package com.zzx.algorithm.zzxalgorithm.排序算法;

import java.util.Arrays;

/**
 *1. 基本思想
 * 插入排序的工作原理是通过构建有序序列，对于未排序数据，在已排序序列中从后向前扫描，找到相应位置并插入。
 * 插入排序在实现上，通常采用in-place排序（即只需用到O(1)的额外空间的排序），因而在从后向前扫描过程中，需要反复把已排序元素逐步向后挪位，为最新元素提供插入空间。
 *
 *2.实现逻辑
 * ① 从第一个元素开始，该元素可以认为已经被排序
 * ② 取出下一个元素，在已经排序的元素序列中从后向前扫描
 * ③如果该元素（已排序）大于新元素，将该元素移到下一位置
 * ④ 重复步骤③，直到找到已排序的元素小于或者等于新元素的位置
 * ⑤将新元素插入到该位置后
 * ⑥ 重复步骤②~⑤
 *
 * 性能分析
 * 平均时间复杂度：O(N^2)
 * 最差时间复杂度：O(N^2)
 * 空间复杂度：O(1)
 * 排序方式：In-place
 * 稳定性：稳定
 *
 * 1. 算法步骤
 * 将第一待排序序列第一个元素看做一个有序序列，把第二个元素到最后一个元素当成是未排序序列。
 *
 * 从头到尾依次扫描未排序序列，将扫描到的每个元素插入有序序列的适当位置。（如果待插入的元素与有序序列中的某个元素相等，则将待插入元素插入到相等元素的后面。）
 */
public class 插入排序 {

    /**
     * 优化版：插入排序
     * 插入排序改进：二分插入排序
     *
     * 改进方法①
     * 场景分析：
     *
     * 直接插入排序每次往前插入时，是按顺序依次往前查找，数据量较大时，必然比较耗时，效率低。
     *
     * 改进思路： 在往前找合适的插入位置时采用二分查找的方式，即折半插入。
     *
     * 二分插入排序相对直接插入排序而言：平均性能更快，时间复杂度降至O(NlogN)，排序是稳定的，但排序的比较次数与初始序列无关，相比直接插入排序，在速度上有一定提升。逻辑步骤：
     *
     * ① 从第一个元素开始，该元素可以认为已经被排序
     * ② 取出下一个元素，在已经排序的元素序列中二分查找到第一个比它大的数的位置
     * ③将新元素插入到该位置后
     * ④ 重复上述两步
     * @param arr
     */
    public static void insertSort_优化版(int[] arr) {
        int key, left, right, middle;
        for (int i = 1; i < arr.length; i++) {
            key = arr[i];
            left = 0;
            right = i - 1;
            while (left <= right) {
                middle = (left + right) / 2;
                if (key < arr[middle])
                    right = middle - 1;
                else
                    left = middle +1;
            }

            for (int j = i - 1; j >= left; j--) {
                arr[j + 1] = arr[j];
            }
            arr[left] = key;
        }
    }

    /**
     * 普通版：插入排序
     * @param arr
     */
    public void insertSort_普通版(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            int j;
            for (j = i - 1; j >= 0; j--) {
                if (arr[j] > temp) {
                    arr[j+1] = arr[j];
                } else {
                    break;
                }
            }
            arr[j+1] = temp;
        }
    }

    public static void main(String[] args) {
        int[] arr = {63,73,12,1,5,6,9,0,45,2,34,57,89,100};
        insertSort_优化版(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + ", ");
        }
    }
}

