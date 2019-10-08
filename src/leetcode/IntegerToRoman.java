package leetcode;

import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.*;


/**
 * 整数转罗马数字
 * 使用貪心算法
 * https://leetcode-cn.com/problems/integer-to-roman/
 */
public class IntegerToRoman {


    public static void main(String[] args) {
        System.out.println(intToRoman(3));
    }

    public static String intToRoman(int num) {
        int[] nums = new int[]{1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] romans = new String[]{"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        int i = 0;
        String result = "";
        while (num > 0 && i < nums.length){
            if (num >= nums[i]) {
                result += romans[i];
                num -= nums[i];
            } else {
                i++;
            }
        }
        return result;
    }

}
