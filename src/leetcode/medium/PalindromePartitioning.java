package src.leetcode.medium;


import java.util.ArrayList;
import java.util.List;

/**
 *
 * 动态规划
 *
 * 分割回文串
 *
 * https://leetcode-cn.com/problems/palindrome-partitioning/
 *
 *
 */

public class PalindromePartitioning {


    public static void main(String[] args) {
        System.out.println(partition("aab"));
    }

    /**
     * 深度优先遍历
     * @param s
     * @return
     */
    public static List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        List<String> temp = new ArrayList<>();
        dfs(s, 0, 1, temp, result);
        return result;
    }

    public static void dfs(String s, int index, int length, List<String> temp, List<List<String>> result) {
        if (index == s.length()) {
            result.add(new ArrayList<>(temp));
            return;
        }
        if (length + index <= s.length()) {
            if (checkPartition(s, index, length)) {
                temp.add(s.substring(index, length + index));
                dfs(s, length + index, 1, temp, result);
                temp.remove(temp.size() - 1);
            }
            dfs(s, index, length + 1, temp, result);
        }
    }

    public static boolean checkPartition(String s, int from, int length) {
       String temp = s.substring(from, length + from);
        for (int i = 0, j = temp.length() - 1; i <= j ; i++, j--) {
            if (temp.charAt(i) != temp.charAt(j)) {
                return false;
            }
        }
        return true;
    }

}
