package src.leetcode.medium;

/**
 *
 * https://leetcode-cn.com/explore/interview/card/bytedance/243/array-and-sorting/1018/
 *
 * https://leetcode-cn.com/problems/kth-largest-element-in-an-array/
 *
 * 数组中的第K个最大元素
 */
public class FindKthLargest {

    public static void main(String[] args) {
//        System.out.println(findKthLargest(new int[]{3,2,1,5,6,4}, 2));
        System.out.println(findKthLargest(new int[]{3,2,3,1,2,4,5,5,6}, 4));
    }

    /**
     * 基于快速排序的选择方法
     * @return
     */
    public static int findKthLargest(int[] nums, int k) {
        return check(nums, 0, nums.length - 1, nums.length - k);
    }

    public static int check(int[] nums, int start, int end, int k) {
        int standard = nums[start];
        int standardIndex = start;
        for (int i = start + 1; i <= end; i++) {
            if (nums[i] < standard) {
                // 这个参数是为了寻找标准点的位置，每次有比标准小的数据，标准就往后移动一位
                standardIndex++;
                // 调换比校准点小的数据到标准点位置
                swap(nums, standardIndex, i);
            }
        }
        // 将标准点移动到中间
        swap(nums, standardIndex, start);


        if (standardIndex == k) {
            return nums[k];
        } else if (standardIndex > k) {
            return check(nums, start, standardIndex - 1, k);
        } else {
            return check(nums, standardIndex + 1, end, k);
        }
    }

    public static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }




    /**
     * 基于堆排序的选择方法
     * @return
     */
    class Solution {
        public int findKthLargest(int[] nums, int k) {
            int heapSize = nums.length;
            buildMaxHeap(nums, heapSize);
            for (int i = nums.length - 1; i >= nums.length - k + 1; --i) {
                swap(nums, 0, i);
                --heapSize;
                maxHeapify(nums, 0, heapSize);
            }
            return nums[0];
        }

        public void buildMaxHeap(int[] a, int heapSize) {
            for (int i = heapSize / 2; i >= 0; --i) {
                maxHeapify(a, i, heapSize);
            }
        }

        public void maxHeapify(int[] a, int i, int heapSize) {
            int l = i * 2 + 1, r = i * 2 + 2, largest = i;
            if (l < heapSize && a[l] > a[largest]) {
                largest = l;
            }
            if (r < heapSize && a[r] > a[largest]) {
                largest = r;
            }
            if (largest != i) {
                swap(a, i, largest);
                maxHeapify(a, largest, heapSize);
            }
        }

        public void swap(int[] a, int i, int j) {
            int temp = a[i];
            a[i] = a[j];
            a[j] = temp;
        }
    }

}
