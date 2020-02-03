package com.zzx.algorithm.zzxalgorithm.shujujiegou;

/**
 * <p><em>Copyright:</em> All Rights Reserved</p>
 * <p><em>Company:</em> 叮当快药科技集团有限公司</p>
 *
 * @author Daniel Zhou / zzx
 **/
public class QuickSort {

    public void quickSort(int[] arr, int left, int right) {
        int key = arr[left];
        int i = left;
        int j = right;
        while (i < j) {
            while (arr[i] <= key && i < j) {
                i++;
            }
            while (arr[j] >= key && i < j) {
                j++;
            }
            if (i < j) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        arr[left] = arr[i];
        arr[i] = key;
        quickSort(arr, left, i - 1);
        quickSort(arr, i + 1, right);
    }
}
