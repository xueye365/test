package src.leetcode.easy;


import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 *
 * 剑指offer
 * 最小的k个数
 *
 * https://leetcode-cn.com/problems/zui-xiao-de-kge-shu-lcof/
 *
 */


public class GetLeastNumbers {

    /**
     * 堆方法，优先队列
     * @param args
     */
    public static void main(String[] args) {
        int[] pushed = new int[]{0,0,0,2,0,5};
        int[] leastNumbers = getLeastNumbers2(pushed, 0);
        for (int i = 0; i < leastNumbers.length; i++) {
            System.out.println(leastNumbers[i]);
        }
    }


    public static int[] getLeastNumbers(int[] arr, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        int[] result = new int[k];

        for (int i = 0; i < arr.length; i++) {
            if (queue.size() < k) {
                queue.add(arr[i]);
            } else {
                if (queue.size() > 0 && queue.peek() > arr[i]) {
                    queue.poll();
                    queue.add(arr[i]);
                }
            }
        }
        for (int i = 0; i < k; ++i) {
            result[i] = queue.poll();
        }
        return result;
    }


    /**
     * 用快排的方式
     * @param arr
     * @param k
     * @return
     */
    public static int[] getLeastNumbers2(int[] arr, int k) {
        int[] ints = new int[k];
        if (k == 0) {
            return ints;
        }
        int middle = getLeastNumbers2(arr, 0, arr.length - 1, k);
        for (int i = 0; i <= middle; i++) {
            ints[i] = arr[i];
        }
        return ints;
    }

    public static int getLeastNumbers2(int[] arr, int left, int right, int k) {
        int middle = partition(arr, left, right);
        if (middle != k - 1) {
            if (middle > k - 1) {
                middle = getLeastNumbers2(arr, left, middle - 1, k);
            } else {
                middle = getLeastNumbers2(arr, middle + 1, right, k);
            }
        }
        return middle;
    }


    public static int partition(int[] arr,int left,int right){
        int pivot = arr[left];
        while(left < right){
            while(left<right && arr[right] >= pivot)
                right--;
            arr[left] = arr[right];
            while(left < right && arr[left]<= pivot)
                left++;
            arr[right] = arr[left];
        }
        arr[left] = pivot;
        return left;
    }






}