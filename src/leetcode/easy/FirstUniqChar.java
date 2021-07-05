package src.leetcode.easy;


import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 *
 * 剑指offer
 * 第一个只出现一次的字符
 *
 * https://leetcode-cn.com/problems/di-yi-ge-zhi-chu-xian-yi-ci-de-zi-fu-lcof/
 *
 */
public class FirstUniqChar {

    public static void main(String[] args) {
        System.out.println(firstUniqChar3("loveleetcode"));
    }

    public static char firstUniqChar(String s) {
        Map<Character, Integer> map = new HashMap();
        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
        }
        for (int i = 0; i < s.length(); i++) {
            if (map.get(s.charAt(i)) == 1) {
                return s.charAt(i);
            }
        }
        return ' ';
    }


    /**
     * 这种还是遍历了全部的字符，如果字符特别多，运行时间还是会很长
     * @param s
     * @return
     */
    public static char firstUniqChar2(String s) {
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (s.indexOf(c) == s.lastIndexOf(c)) {
                return c;
            }
        }
        return ' ';
    }


    /**
     * 最优解
     * @param s
     * @return
     */
    public static char firstUniqChar3(String s) {
        if (s == null || s.length() == 0) {
            return ' ';
        }
        int min = s.length();
        for (char c = 'a'; c <= 'z'; c++) {
            int i = s.indexOf(c);
            if (i != -1 && i == s.lastIndexOf(c)) {
                min = Math.min(min, s.indexOf(c));
            }
        }
        return min == s.length() ? ' ' : s.charAt(min);
    }





}