package org.zzx.nk.nk04;

public class ArraySum {
    public static int sum(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int sum = 0;
        for (int num : arr) {
            sum += num;
        }
        return sum;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        System.out.println(sum(arr)); // Output: 15
    }
}