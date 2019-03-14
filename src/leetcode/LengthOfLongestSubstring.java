package src.leetcode;

import java.util.*;

/**
 * 无重复字符的最长子串
 *
 * https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/
 *
 * 总结：用map可以记录两个信息，内容和index,所以大部分两个for循环可以变成一个for循环
 *
 */
public class LengthOfLongestSubstring {

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring2("abcadegkiu"));
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
        int result = 0;
        Map<Character, Integer> temp = new HashMap();
        for (int i = 0; i <= s.length() - 1; i++) {
            if (!temp.containsKey(s.charAt(i))) {
                temp.put(s.charAt(i), i);
                result = Math.max(result, temp.size());
            } else {
                i = temp.get(s.charAt(i));
                if (i >= s.length() - 1){
                    break;
                }
                temp.clear();
            }
        }
        return result;
    }


}
