package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 罗马数字转整数
 * https://leetcode-cn.com/problems/roman-to-integer/
 */
public class RomanToInteger {

    public static void main(String[] args) {
        System.out.println(romanToInt("MCMXCIV"));
    }

    static Map<Character, Integer> TEMPLATE = new HashMap();
    static {
        TEMPLATE.put('I', 1);
        TEMPLATE.put('V', 5);
        TEMPLATE.put('X', 10);
        TEMPLATE.put('L', 50);
        TEMPLATE.put('C', 100);
        TEMPLATE.put('D', 500);
        TEMPLATE.put('M', 1000);
    }

    public static int romanToInt(String s) {
        char[] chars = s.toCharArray();
        int result = 0;
        int temp = 0;
        for (int i = chars.length - 1; i >= 0; i--) {
            int score = TEMPLATE.get(chars[i]);
            result += score;
            if (temp > score) {
                result -= 2 * score;
            }
            temp = score;
        }
        return result;
    }
}
