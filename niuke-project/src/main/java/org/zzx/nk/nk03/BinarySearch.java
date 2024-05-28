package org.zzx.nk.nk03;

/**
 * 6. 实现二分查找
 * 题目：编写一个函数来实现二分查找。
 */
public class BinarySearch {
    public static int binarySearch(int[] arr, int target) {
        int left = 0, right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == target) {
                return mid;
            }
            if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1; // Target not found
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        int target = 5;
        System.out.println(binarySearch(arr, target)); // Output: 4
    }
}