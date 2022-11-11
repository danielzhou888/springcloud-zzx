package com.zzx.algorithm.zzxalgorithm.排序算法;

import java.util.Arrays;

/**
 * 1.基本思想
 * 通过一趟排序将待排记录分隔成独立的两部分，其中一部分记录的关键字均比另一部分的关键字小，则可分别对这两部分记录继续进行排序，以达到整个序列有序。
 *
 * 2. 实现逻辑
 * 快速排序使用分治法（Divide and conquer）策略来把一个序列（list）分为两个子序列（sub-lists）。
 *
 * ① 从数列中挑出一个元素，称为 “基准”（pivot），
 * ② 重新排序数列，所有元素比基准值小的摆放在基准前面，所有元素比基准值大的摆在基准的后面（相同的数可以到任一边）。在这个分区退出之后，
 *      该基准就处于数列的中间位置。这个称为分区（partition）操作。
 * ③ 递归地（recursive）把小于基准值元素的子数列和大于基准值元素的子数列排序。
 * 递归到最底部时，数列的大小是零或一，也就是已经排序好了。这个算法一定会结束，因为在每次的迭代（iteration）中，它至少会把一个元素摆到它最后的位置去。
 **/
public class 快速排序 {


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
        快速排序 qs = new 快速排序();
        int[] arr = new int[]{8,9,6,3,4,2,1};
        qs.quickSort2(arr, 0, arr.length - 1);
        Arrays.stream(arr).forEach(System.out::print);
    }

//    int[] arr;
//    private void swap(int x, int y) {
//        int temp = arr[x];
//        arr[x] = arr[y];
//        arr[y] = temp;
//    }
//    private void quick_sort_recursive(int start, int end) {
//        if (start >= end)
//            return;
//        int mid = arr[end];
//        int left = start, right = end - 1;
//        while (left < right) {
//            while (arr[left] <= mid && left < right)
//                left++;
//            while (arr[right] >= mid && left < right)
//                right--;
//            swap(left, right);
//        }
//        if (arr[left] >= arr[end])
//            swap(left, end);
//        else
//            left++;
//        quick_sort_recursive(start, left - 1);
//        quick_sort_recursive(left + 1, end);
//    }
//    public void sort(int[] arrin) {
//        arr = arrin;
//        quick_sort_recursive(0, arr.length - 1);
//    }


    /**
     * 6. 优化改进
     * 场景分析： 递归是一种使用相同的方法，通过解决问题的子集以达到解决整个问题的方法，是一种使用有限代码解决“无限”计算的方法。
     * 在C/C++语言中递归表现在函数对自身的直接/间接的调用上，在实现上，递归依赖于语言的运行时调用堆栈，使用堆栈来保存每一次递归调用返回时所需要的条件。
     * 递归通常具有简洁的编码和清晰的思路，但这种简洁是有代价的。一方面，是函数调用的负担；另一方面，是堆栈占用的负担（堆栈的大小是有限的）。
     *
     * 改进思路：递归转化为迭代。迭代的思想主要在于，在同一栈帧中不断使用现有数据计算出新的数据，然后使用新的数据来替换原有数据。
     * @param arr
     * @param left
     * @param right
     */
//    typedef struct _Range {
//        int start, end;
//    } Range;
//    Range new_Range(int s, int e) {
//        Range r;
//        r.start = s;
//        r.end = e;
//        return r;
//    }
//    void swap(int *x, int *y) {
//        int t = *x;
//    *x = *y;
//    *y = t;
//    }
//    void quick_sort(int arr[], const int len) {
//        if (len <= 0)
//            return; //避免len等于负值时错误
//        //r[]模拟堆疊,p为数量,r[p++]为push,r[--p]为pop且取得元素
//        Range r[len];
//        int p = 0;
//        r[p++] = new_Range(0, len - 1);
//        while (p) {
//            Range range = r[--p];
//            if (range.start >= range.end)
//                continue;
//            int mid = arr[range.end];
//            int left = range.start, right = range.end - 1;
//            while (left < right) {
//                while (arr[left] < mid && left < right)
//                    left++;
//                while (arr[right] >= mid && left < right)
//                    right--;
//                swap(&arr[left], &arr[right]);
//            }
//            if (arr[left] >= arr[range.end])
//                swap(&arr[left], &arr[range.end]);
//        else
//            left++;
//            r[p++] = new_Range(range.start, left - 1);
//            r[p++] = new_Range(left + 1, range.end);
//        }
//    }



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



}
