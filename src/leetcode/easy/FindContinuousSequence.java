package src.leetcode.easy;

import java.util.*;

/**
 * 剑指offer
 *
 * 和为s的连续正数序列
 * https://leetcode-cn.com/problems/he-wei-sde-lian-xu-zheng-shu-xu-lie-lcof/
 *
 */
public class FindContinuousSequence {

    public static void main(String[] args) {
        int[][] continuousSequence = findContinuousSequence(15);
        for (int[] ints : continuousSequence) {
            for (int i : ints) {
                System.out.println(i);
            }
            System.out.println();
        }
    }


    /**
     * 滑动窗口方法
     * @param target
     * @return
     */
    public static int[][] findContinuousSequence(int target) {
        if (target == 1) {
            return new int[][]{new int[]{1}};
        }
        List<int[]> res = new ArrayList<>();
        int i = 1, j = i + 1;
        while (j < target) {
            int sum = (i + j) * (j - i + 1) / 2;
            if (sum < target) {
                j++;
            } else if (sum > target) {
                i++;
            } else {
                int[] ints = new int[j - i + 1];
                for (int i1 = i, i2 = 0; i1 <= j; i1++, i2++) {
                    ints[i2] = i1;
                }
                res.add(ints);
                j++;
                i++;
            }
        }
        return res.toArray(new int[res.size()][]);
    }



    /**
     * 2.数学方法解决
     *
     * a, a+1, a+2, a+3 …… a+(n-1)
     * na + n(n-1)/2 = t
     * a = (t - n(n-1)/2) / n
     * 只有a是正整数的时候才满足条件，那么这个循环什么时候终止呢，其实很简单，当分子t-n*(n-1)/2小于等于0的时候就可以终止了。
     *
     * @param target
     * @return
     */
    public int[][] findContinuousSequence2(int target) {
        List<int[]> res = new ArrayList<>();
        int n = 2;
        //死循环
        while (true) {
            int total = target - n * (n - 1) / 2;
            //当分子小于等于0的时候，退出循环
            if (total <= 0)
                break;
            //如果首项是正整数，满足条件
            if (total % n == 0) {
                int[] arr = new int[n];
                //找出首项的值
                int startValue = total / n;
                for (int k = 0; k < n; k++) {
                    arr[k] = startValue + k;
                }
                res.add(arr);
            }
            //继续找
            n++;
        }
        //反转，比如当target等于9的时候，结果是
        //[[4,5],[2,3,4]]，但题中要求的是不同
        // 序列按照首个数字从小到大排列，所以这里反转一下
        Collections.reverse(res);
        //把list转化为数组
        return res.toArray(new int[res.size()][]);
    }


    /**
     * 假如target是两个连续数字的和，那么这个序列的首项就是(target-1)/2。
     * 假如target是三个连续数字的和，那么这个序列的首项就是(target-1-2)/3。
     * 假如target是四个连续数字的和，那么这个序列的首项就是(target-1-2-3)/4。
     *
     */
    public int[][] findContinuousSequence3(int target) {
        List<int[]> res = new ArrayList<>();
        //因为至少是两个数，所以target先减1
        target--;
        for (int n = 2; target > 0; n++) {
            //找到了一组满足条件的序列
            if (target % n == 0) {
                int[] arr = new int[n];
                //找出首项的值
                int startValue = target / n;
                for (int k = 0; k < n; k++) {
                    arr[k] = startValue + k;
                }
                res.add(arr);
            }
            target -= n;
        }
        Collections.reverse(res);
        //把list转化为数组
        return res.toArray(new int[res.size()][]);
    }
}
