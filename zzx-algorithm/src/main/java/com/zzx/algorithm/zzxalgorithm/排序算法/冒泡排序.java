package com.zzx.algorithm.zzxalgorithm.排序算法;

/**
 * 1. 基本思想
 * 冒泡排序是一种交换排序，核心是冒泡，把数组中最小的那个往上冒，冒的过程就是和他相邻的元素交换。
 *
 * 重复走访要排序的数列，通过两两比较相邻记录的排序码。排序过程中每次从后往前冒一个最小值，且每次能确定一个数在序列中的最终位置。
 * 若发生逆序，则交换；有俩种方式进行冒泡，一种是先把小的冒泡到前边去，另一种是把大的元素冒泡到后边。
 *
 *
 **/
public class 冒泡排序 {

    /**
     * 普通版：冒泡排序
     * @param arr
     */
    public void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = arr.length - 1; j > i; j--) {
                if (arr[j] < arr[j - 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = temp;
                }
            }
        }
    }

    /**
     * 6.1 改进方法①
     * 场景一：
     *
     * 在某次遍历中如果没有数据交换，说明整个数组已经有序。若初始序列就是排序好的，如果用基础的冒泡排序方法，仍然还要比较O(N^2)次，但无交换次数。
     *
     * 改进思路：
     *
     * 通过设置标志位来记录此次遍历有无数据交换，进而可以判断是否要继续循环，设置一个flag标记，当在一趟序列中没有发生交换，则该序列已排序好，
     * 但优化后排序的时间复杂度没有发生量级的改变。
     */
    void bubble_sort(int arr[], int len) {
        for (int i = 0; i < len-1; i++){        //比较n-1次
            boolean exchange = true;               //冒泡的改进，若在一趟中没有发生逆序，则该序列已有序
            for (int j = len-1; j >i; j--){     //每次从后边冒出一个最小值
                if (arr[j] < arr[j - 1]){       //发生逆序，则交换
                    int temp = arr[j];
                    arr[j] = arr[j-1];
                    arr[j-1] = temp;
                    exchange = false;
                }
            }
            if (exchange){
                return;
            }
        }
    }


    public static void main(String[] args) {
        int[] arr = {63,73,12,1,5,22,55,11,22,38,99,6,9,0,45,2,34,57,89,100};
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + ", ");
        }
    }




}
