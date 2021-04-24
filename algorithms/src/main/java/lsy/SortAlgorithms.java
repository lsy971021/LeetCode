package lsy;

import java.util.Arrays;

public class SortAlgorithms {
    public static void main(String[] args) {
        int[] arr = {11, 2, 3, 12, 4, 66, 12};
        quick(arr,0,6);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 冒泡排序
     * @param arr
     * @return
     */
    public static int[] bubble(int[] arr) {
        for (int i = arr.length - 1; i > 0; i--) {
            for (int o = 0; o < i; o++) {
                if (arr[o] > arr[o + 1]) {
                    int re = arr[o];
                    arr[o] = arr[o + 1];
                    arr[o + 1] = re;
                }
            }
        }
        return arr;
    }

    /**
     * 选择排序
     * @param arr
     * @return
     */
    public static int[] selection(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int o = i + 1; o < arr.length; o++) {
                if (arr[i] > arr[o]) {
                    int re = arr[i];
                    arr[i] = arr[o];
                    arr[o] = re;
                }
            }
        }
        return arr;
    }

    /**
     * 插入排序
     * @param arr
     * @return
     */
    public static int[] insertion(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            for (int o = i; o > 0; o--) {
                if (arr[o - 1] > arr[o]) {
                    int re = arr[o - 1];
                    arr[o - 1] = arr[o];
                    arr[o] = re;
                } else {
                    break;
                }
            }
        }
        return arr;
    }

    /**
     * 快速排序
     * @param arr
     * @param left
     * @param right
     */
    public static void quick(int[] arr,int left,int right) {
        if(left>=right){
            return;
        }
        int help = help(arr, left, right);
        quick(arr,left,help-1);
        quick(arr,help+1,right);
    }

    public static int help(int[] arr, int lift, int right) {
        int index = arr[lift];
        int r = right+1;
        int l = lift;
        int re;
        right++;
        while (true) {
            while (arr[--r]>index) {
                if(r==lift){
                    break;
                }
            }
            while (arr[++l]<index){
                if(l==right){
                    break;
                }
            }
            if(l>=r){
                break;
            }else {
                re = arr[l];
                arr[l] = arr[r];
                arr[r] = re;
            }
        }
        re = arr[r];
        arr[r] = arr[lift];
        arr[lift] = re;
        return r;
    }
}
