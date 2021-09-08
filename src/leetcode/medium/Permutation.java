package src.leetcode.medium;


import java.util.*;

/**
 *
 * 剑指offer
 * 字符串的排列
 *
 * https://leetcode-cn.com/problems/zi-fu-chuan-de-pai-lie-lcof/
 *
 */


public class Permutation {


    public static void main(String[] args) {
        String[] abcs = permutation("kzfxxx");
//        String[] abcs = permutation("aab");
        for (String abc : abcs) {
            System.out.println(abc);
        }
    }

    /**
     * 回溯
     * 深度优先遍历
     * @param s
     * @return
     */
    public static String[] permutation(String s) {
        char[] chars = s.toCharArray();
        List<String> result = new ArrayList<>();
        dfs(0, s.length(), chars, result);
        return result.toArray(new String[s.length()]);
    }
    public static void dfs(int i, int n, char[] temp, List<String> result) {
        if (i == n) {
            result.add(new String(temp));
        }
        Set<Character> set = new HashSet<>();
        for (int i1 = i; i1 < n; i1++) {
            if (set.contains(temp[i1])) {
                continue;
            }
            set.add(temp[i1]);
            swap(i, i1, temp);
            dfs(i + 1, n, temp, result);
            swap(i, i1, temp);
        }
    }
    public static void swap(int i, int j, char[] temp) {
        char tempchar = temp[i];
        temp[i] = temp[j];
        temp[j] = tempchar;
    }







}
