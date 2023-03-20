package src.leetcode.easy;


/**
 * https://leetcode.cn/problems/add-binary/
 * 二进制求和
 */


public class AddBinary {


    public static void main(String[] args) {
        String s = addBinary("1010", "1011");
        System.out.println(s);

    }

    public static String addBinary(String a, String b) {
        int[] result = new int[a.length() > b.length() ? a.length() : b.length()];
        int length = result.length - 1;
        char[] aChar = a.toCharArray();
        char[] bChar = b.toCharArray();
        int i = a.length() - 1;
        int j = b.length() - 1;
        int sum = 0;
        while (i >= 0 || j >= 0) {
            int temp;
            if (i >= 0 && j >= 0) {
                temp = aChar[i] - '0' + bChar[j] - '0' + sum;
                i--;
                j--;
            } else if (i >= 0) {
                temp = aChar[i] - '0' + sum;
                i--;
            } else {
                temp = bChar[j] - '0' + sum;
                j--;
            }

            if (temp == 0) {
                result[length--] = 0;
                sum = 0;
            } else if (temp == 1) {
                result[length--] = 1;
                sum = 0;
            } else if (temp == 2) {
                result[length--] = 0;
                sum = 1;
            } else if (temp == 3) {
                result[length--] = 1;
                sum = 1;
            }
        }
        StringBuilder s = new StringBuilder();
        for (int i1 : result) {
            s.append(i1);
        }
        if (sum == 0) {
            return s.toString();
        } else {
            return sum + s.toString();
        }
    }



}