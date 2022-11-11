package com.zzx.algorithm.zzxalgorithm.test1;

/**
 * 01.题目要求
 * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有的奇数位于数组的前半部分，所有的偶数位于位于数组的后半部分，并保证奇数和奇数，偶数和偶数之间的相对位置不变。
 * 02.问题分析
 * 我们首先统计奇数的个数假设为n,然后新建一个等长数组，然后通过循环判断原数组中的元素为偶数还是奇数。如果是则从数组下标0的元素开始，把该奇数添加到新数组；如果是偶数则从数组下标为n的元素开始把该偶数添加到新数组中。
 */
public class 调整数组顺序 {

    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7,8,9};
        sortOddArr(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + ",");
        }
    }

    private static void sortOddArr(int[] arr) {
        if (arr == null || arr.length <= 0) {
            return;
        }
        int oddCount = 0;
        int oddIndex = 0;
        for (int i = 0; i < arr.length; i++) {
            if ((arr[i] & 1) == 1) {
                oddCount++;
            }
        }
        int[] newArr = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            if ((arr[i] & 1) == 1) {
                newArr[oddIndex++] = arr[i];
            } else {
                newArr[oddCount++] = arr[i];
            }
        }
        for (int i = 0; i < newArr.length; i++) {
            arr[i] = newArr[i];
        }
    }
}
