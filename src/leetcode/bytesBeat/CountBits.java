package src.leetcode.bytesBeat;



/**
 *
 * 比特位计数
 *
 * https://leetcode-cn.com/problems/counting-bits/
 *
 * 对于任意整数 xx，令 x=x&(x-1)，该运算将 x 的二进制表示的最后一个 1 变成 0。因此，对 x 重复该操作，直到 x 变成 0，则操作次数即为 x 的「一比特数」。
 *
 *
 * 0 --> 0
 * 1 --> 1
 * 2 --> 10
 * 3 --> 11
 * 4 --> 100
 * 5 --> 101
 * 6 --> 110
 *
 */

public class CountBits {




    public static void main(String[] args) {
        System.out.println(2>>1);
//        countBits2(8);
    }

    /**
     * 暴力解法
     * @param n
     * @return
     */
    public static int[] countBits(int n) {
        int[] result = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            result[i] = count(i);
        }
        return result;
    }


    public static int count(int n) {
        if (n == 0) {
            return 0;
        }
        int temp ;
        int count = 0;
        while (n > 0) {
            temp = n % 2;
            n = n / 2;
            if (temp == 1) {
                count++;
            }
        }
        return count;
    }


    /**
     * Brian Kernighan 算法
     * @param n
     * @return
     */
    public static int[] countBits2(int n) {
        int[] result = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            result[i] = count2(i);
        }
        return result;
    }

    public static int count2(int n) {
        int count = 0;
        while (n > 0) {
            n &= (n - 1);
            count++;
        }
        return count;
    }




}
