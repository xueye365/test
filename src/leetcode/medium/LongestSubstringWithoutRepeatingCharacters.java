package leetcode.medium;

import java.util.*;

/**
 * 无重复字符的最长子串
 * <p>
 * https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/
 * <p>
 * 总结：用map可以记录两个信息，内容和index,所以大部分两个for循环可以变成一个for循环
 */
public class LongestSubstringWithoutRepeatingCharacters {

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring2("abcabcbb"));
    }

    /**
     * 我自己写的
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] a = s.toCharArray();
        int result = 0;
        List<Character> temp = new ArrayList();
        for (int i = 0; i <= a.length - 1; i++) {
            for (int j = i; j <= a.length - 1; j++) {
                if (!temp.contains(a[j])) {
                    temp.add(a[j]);
                    if (temp.size() > result) {
                        result = temp.size();
                    }
                } else {
                    temp.clear();
                    break;
                }
            }
        }
        return result;
    }


    /**
     * 优化版(还可以再优化)
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring2(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        HashMap<Character, Integer> map = new HashMap();
        int len = s.length();
        int start = 0;
        int max = 0;
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)) {
                start = Math.max(map.get(c) + 1,start);
            }
            max = Math.max(max, i - start + 1);
            map.put(c, i);
        }
        return max;
    }


    /**
     * 最优，耗时2毫秒
     *
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring3(String s) {
        int result = 0;
        // 用数组记录每个字符的下角标
        int[] index = new int[128];
        int i = 0;
        for (int j = 0; j < s.length(); j++) {
            i = Math.max(index[s.charAt(j)], i);
            result = Math.max(result, j - i + 1);
            index[s.charAt(j)] = j + 1;
        }
        return result;
    }


}
