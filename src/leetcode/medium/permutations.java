package src.leetcode.medium;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *
 * 全排列
 *
 * https://leetcode-cn.com/problems/permutations/
 *
 */
public class permutations {


    public static void main(String[] args) {
        System.out.println(permute2(new int[]{1,2,3,4,5}));
    }

    // 
    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();

        List<Integer> output = new ArrayList<>();
        for (int num : nums) {
            output.add(num);
        }

        int n = nums.length;
        backtrack(n, output, res, 0);
        return res;
    }

    public static void backtrack(int n, List<Integer> output, List<List<Integer>> res, int first) {
        // 所有数都填完了
        if (first == n) {
            res.add(new ArrayList<Integer>(output));
        }
        for (int i = first; i < n; i++) {
            // 动态维护数组
            Collections.swap(output, first, i);
            // 继续递归填下一个数
            backtrack(n, output, res, first + 1);
            // 撤销操作
            Collections.swap(output, first, i);
        }
    }



    public static List<List<Integer>> permute2(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        for (int num : nums) {
            temp.add(num);
        }
        backtrack2(nums.length, 0, result, temp);
        return result;
    }

    public static void backtrack2(int n, int start, List<List<Integer>> result, List<Integer> temp) {
        if (n == start) {
            result.add(new ArrayList<>(temp));
        }
        for (int i = start; i < n; i++) {
            Collections.swap(temp, start, i);
            backtrack2(n, start + 1, result, temp);
            Collections.swap(temp, start, i);
        }
    }

}
