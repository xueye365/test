package src.leetcode.medium;


/**
 * 字符串相乘
 *
 * https://leetcode.cn/problems/multiply-strings/
 *
 */
public class MultiplyStrings2 {

    public static void main(String[] args) {
        System.out.println(multiply("999", "999"));
    }

    public static String multiply(String num1, String num2) {
        if ((num1 != null && num1.equals("0")) || (num2 != null && num2.equals("0"))) {
            return "0";
        }
        StringBuilder stringBuilder1 = new StringBuilder(num1);
        char[] chars1 = stringBuilder1.reverse().toString().toCharArray();
        StringBuilder stringBuilder2 = new StringBuilder(num2);
        char[] chars2  = stringBuilder2.reverse().toString().toCharArray();

//        char[] chars1 = num1.toCharArray();
//        char[] chars2 = num2.toCharArray();
        int length1 = chars1.length;
        int length2 = chars2.length;

        int[][] resultInt = new int[length1][length2 + length1];
        for (int i = 0; i < resultInt.length; i++) {
            for (int i1 = 0; i1 < resultInt[i].length; i1++) {
                resultInt[i][i1] = 0;
            }
        }

        int ten = 0;
        for (int i = 0; i < length1; i++) {
            int inc = 0;
            int inti = chars1[i] - '0';
            for (int j = 0; j < length2; j++) {
                int intj = chars2[j] - '0';
                int tmp = inti * intj + inc;
                if (tmp >= 10) {
                    inc = tmp / 10;
                    resultInt[i][j+ten] = tmp % 10;
                } else {
                    inc = 0;
                    resultInt[i][j+ten] = tmp;
                }
            }
            if (inc > 0) {
                resultInt[i][length2+ten] = inc;
            }
            ten++;
        }

        int[] result = new int[resultInt[0].length];
        int tmp = 0;
        for (int i1 = 0; i1 < resultInt[0].length; i1++) {
            int resultTmp = 0;
            for (int i = 0; i < resultInt.length; i++) {
                resultTmp = resultTmp + resultInt[i][i1];
            }
            resultTmp = resultTmp + tmp;
            if (resultTmp >= 10) {
                tmp = resultTmp / 10;
                result[i1] = resultTmp % 10;
            } else {
                tmp = 0;
                result[i1] = resultTmp;
            }
        }
        if (tmp > 0) {
            result[result.length - 1] = tmp;
        }

        char[] resultChar;
        if (result[result.length - 1] == 0) {
            resultChar = new char[result.length - 1];
        } else {
            resultChar = new char[result.length];
        }

        for (int i = 0; i < resultChar.length; i++) {
            resultChar[resultChar.length - i - 1] = (char) ('0' + result[i]);
        }

        return new String(resultChar);
    }


}
