package src.leetcode.easy;


import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.cn/problems/plus-one/
 * 加一
 */


public class PlusOne {


    public static void main(String[] args) {
        Integer[] ints = plusOne(new Integer[]{9, 9});
        List<Integer> array = Arrays.asList(ints);
        System.out.println(array);

    }


    public static Integer[] plusOne(Integer[] digits) {
        for (int length = digits.length - 1; length >= 0; length--) {
            int flag = digits[length] + 1;
            if (flag == 10) {
                if (length == 0) {
                    digits[length] = 0;
                    Integer[] ints = new Integer[digits.length + 1];
                    ints[0] = 1;
                    for (int i = digits.length; i > 0; i--) {
                        ints[i] = digits[i - 1];
                    }
                    return ints;
                } else {
                    digits[length] = 0;
                    continue;
                }
            } else {
                digits[length] = flag;
                return digits;
            }
        }
        return digits;
    }



}