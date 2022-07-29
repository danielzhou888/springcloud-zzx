package com.zzx.algorithm.zzxalgorithm.shujujiegou;

import java.util.Arrays;

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
            while (arr[j] >= key && i < j) {
                j--;
            }while (arr[j] >= key && i < j) {
                j--;
            }
            while (arr[i] <= key && i < j) {
                i++;
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


    public void quickSort2(int arr[], int left, int right) {
        if (left >= right) return;
        int key = arr[left];
        int i = left;
        int j = right;
        while (i < j) {
            while (arr[j] >= key && i < j) {
                j--;
            }
            while (arr[i] <= key && i < j) {
                i++;
            }
            if (i < j) {
                int temp = arr[j];
                arr[j] = arr[i];
                arr[i] = temp;
            }
        }
        arr[left] = arr[i];
        arr[i] = key;
        quickSort2(arr, left, i - 1);
        quickSort2(arr, i + 1, right);
    }

    public static void main(String[] args) {
        QuickSort qs = new QuickSort();
        int[] arr = new int[]{8,9,6,3,4,2,1};
        qs.quickSort2(arr, 0, arr.length - 1);
        Arrays.stream(arr).forEach(System.out::print);
    }
}
