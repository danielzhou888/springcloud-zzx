package com.zzx.algorithm.zzxalgorithm.shujujiegou;

/**
 * <p><em>Copyright:</em> All Rights Reserved</p>
 * <p><em>Company:</em> 创盛视联数码科技（北京）有限公司   https://www.bokecc.com/</p>
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
