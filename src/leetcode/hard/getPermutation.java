package src.leetcode.hard;


import java.util.Arrays;

/**
 * 字节跳动
 *
 * 排列序列
 *
 * https://leetcode-cn.com/problems/permutation-sequence/
 * https://leetcode-cn.com/explore/interview/card/bytedance/243/array-and-sorting/1021/
 *
 */
public class getPermutation {


    public static void main(String[] args) {
        System.out.println(getPermutation(3,3));
    }

    private static String getPermutation(int n, int k) {
        boolean[] used = new boolean[n + 1];
        Arrays.fill(used, false);
        int[] tree = new int[n + 1];
        tree[0] = 1;
        for (int i = 1; i <= n; i++) {
            tree[i] = tree[i - 1] * i;
        }
        StringBuilder stringBuilder = new StringBuilder();
        dfs(n, k, used, tree, 0, stringBuilder);
        return stringBuilder.toString();
    }

    private static void dfs(int n, int k, boolean[] used, int[] tree, int index, StringBuilder stringBuilder) {
        if (index == n) {
            return;
        }
        for (int i = 1; i < n + 1; i++) {
            if (used[i]) {
                continue;
            }
            if (k > tree[n - 1 - index]) {
                k = k - tree[n - 1 - index];
                continue;
            }
            used[i] = true;
            stringBuilder.append(i);
            dfs(n, k, used, tree, index + 1, stringBuilder);
        }

    }



}
