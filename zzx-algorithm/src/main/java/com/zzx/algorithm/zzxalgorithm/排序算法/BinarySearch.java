package com.zzx.algorithm.zzxalgorithm.排序算法;

/**
 *
 * <p><em>Copyright:</em> All Rights Reserved</p>
 * <p><em>Company:</em> 叮当快药科技集团有限公司</p>
 *
 * @author Daniel Zhou / zzx
 **/
public class BinarySearch {

    public int binarySearch(int[] arr, int value) {
        if (arr == null || arr.length <= 0)
            return -1;
        int low = 0;
        int pow = arr.length - 1;
        int middle = 0;
        while (true) {
            middle = (low + pow) / 2;
            if (arr[middle] == value)
                return middle;
            else if(low > pow)
                return -1;
            else
                if (arr[middle] > value)
                    pow = middle - 1;
                else
                    low = middle + 1;
        }
    }
}
