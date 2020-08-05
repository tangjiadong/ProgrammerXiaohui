package chapter4.part2;

import java.util.Arrays;

/**
 * Created by Tang on 2020/8/6
 */
public class BubbleSort {
    /**
     * 普通冒泡排序
     * @param array
     */
    public static void sort(int array[]) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                int tmp = 0;
                if (array[j] > array[j + 1]) {
                    tmp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = tmp;
                }
            }
        }
    }

    /**
     * 优化冒泡排序
     * @param array
     */
    public static void sort1(int array[]) {
        for (int i = 0; i < array.length - 1; i++) {
            //有序标记,每一轮的初始值都是true
            boolean isSorted = true;
            for (int j = 0; j < array.length - i - 1; j++) {
                int tmp = 0;
                if (array[j] > array[j + 1]) {
                    tmp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = tmp;
                    //因为有元素交换,所有不是有序的,标记变为false
                    isSorted = false;
                }
            }
            if (isSorted){
                break;
            }
        }
    }

    /**
     * 冒泡排序再优化
     * @param array
     */
    public static void sort2(int array[]) {
        //记录最后一次交换的位置
        int lastExchangeIndex = 0;
        //无序数列的边界,每个比较只需要比较到这里为止
        int sortBorder = array.length -1;
        for (int i = 0; i < array.length - 1; i++) {
            //有序标记,每一轮的初始值都是true
            boolean isSorted = true;
            for (int j = 0; j < sortBorder; j++) {
                int tmp = 0;
                if (array[j] > array[j + 1]) {
                    tmp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = tmp;
                    //因为有元素交换,所有不是有序的,标记变为false
                    isSorted = false;
                    //更新为最后一次交换元素的位置
                    lastExchangeIndex = j;
                }
            }
            sortBorder = lastExchangeIndex;
            if (isSorted){
                break;
            }
        }
    }

    public static void main(String[] args) {
        int[] array = new int[]{5, 8, 3, 7, 3, 1, 6};
        sort(array);
        System.out.println(Arrays.toString(array));
        sort1(array);
        System.out.println(Arrays.toString(array));
        sort2(array);
        System.out.println(Arrays.toString(array));
    }
}
