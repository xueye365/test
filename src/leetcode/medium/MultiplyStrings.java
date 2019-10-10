package leetcode.medium;

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

//    public static void main(String[] args) {
//        String num1 = "123456789", num2 = "987654321";
//        System.out.println(multiply2(num1, num2));
//    }

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




    private static String multiply2 (String num2, String num1) {
        if (num1 == null || num2 == null || "".equals(num1) || "".equals(num2) || "0".equals(num1) || "0".equals(num2)) {
            return "0";
        }
        long[] a = new long[num1.length()];
        for (int i = num1.length() - 1; i >= 0; i--) {
            a[num1.length() - i - 1] = (num1.charAt(i) - 48);
        }
        long[] b = new long[num2.length()];
        for (int i = num2.length() - 1; i >= 0; i--) {
            b[num2.length() - i - 1] = (num2.charAt(i) - 48);
        }
        int temp[][] = new int[b.length + a.length][a.length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b.length; j++) {
                temp[i + j][i] += (b[j] * a[i]);
            }
        }
        int[] result = new int[temp.length];
        // 进位
        int advance = 0;
        for (int i = 0; i < temp.length; i++) {
            int sum = 0;
            for (int i1 = 0; i1 < temp[i].length; i1++) {
                sum += temp[i][i1];
            }
            sum += advance;
            advance = sum / 10;
            result[i] = sum % 10;
        }
        String resultStr = "";
        for (int i = result.length - 1; i >= 0; i--) {
            if (i == result.length - 1 && result[result.length - 1] == 0) {
                continue;
            }
            resultStr = resultStr + result[i];
        }
        return resultStr;
    }



}
