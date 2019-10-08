package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 字符串相乘
 *
 * https://leetcode-cn.com/explore/interview/card/tencent/221/array-and-strings/904/
 *
 */
public class MultiplyStrings {

    public static void main(String[] args) {
        System.out.println(multiply("1", ""));
    }
    public static String multiply(String num1, String num2) {
        if (num1.charAt(0) == '0' || num2.charAt(0) == '0') {
            return "0";
        }
        List<Integer> a = new ArrayList();
        List<Integer> b = new ArrayList();
        for (int i = 0; i < num1.length(); i++) {
            a.add(num1.charAt(0) - '0');
        }
        for (int i = 0; i < num2.length(); i++) {
            b.add(num2.charAt(0) - '0');
        }

        List<Integer> c = new ArrayList();
        for (int i = a.size() - 1; i > 0; i--) {
            List<Integer> d = new ArrayList();
            for (int j = b.size(); j > 0; j--) {
                double temp = b.get(j) * Math.pow(10, a.size() - 1 - i) * a.get(i);

            }
        }



        return null;
    }


}
