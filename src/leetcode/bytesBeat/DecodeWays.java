package src.leetcode.bytesBeat;



/**
 *
 * 动态规划
 * 解码方法
 *
 * https://leetcode-cn.com/problems/decode-ways/
 *
 */

public class DecodeWays {



    public static void main(String[] args) {
        System.out.println(numDecodings("27"));
    }

    public static int numDecodings(String s) {
        if (s.length() == 0 || s.equals("0") || s.startsWith("0")) {
            return 0;
        }
        if (s.length() >= 2 && Integer.valueOf(s) > 10) {
            return decode(s) + 1;
        } else {
            return decode(s);
        }
    }

    public static int decode(String s) {
        Integer integer = Integer.valueOf(s);
        if (integer >= 1 && integer <= 26) {
            return 1;
        }
        String substring2 = s.substring(0, 2);
        int sum2 = 0;
        if (!substring2.startsWith("0")) {
            sum2 = decode(s.substring(substring2.length()));
        }
        String substring1 = s.substring(0, 1);
        int sum1 = 0;
        if (!substring1.startsWith("0")) {
            sum1 = decode(s.substring(substring1.length()));
        }
        return sum1 + sum2;
    }


}
