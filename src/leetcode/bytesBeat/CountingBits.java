package src.leetcode.bytesBeat;



/**
 *
 * 动态规划
 * 比特位计数
 *
 * https://leetcode-cn.com/problems/counting-bits/
 *
 *
 * 奇数：二进制表示中，奇数一定比前面那个偶数多一个 1，因为多的就是最低位的 1。
 * 偶数：二进制表示中，偶数中 1 的个数一定和除以 2 之后的那个数一样多。因为最低位是 0，除以 2 就是右移一位，也就是把那个 0 抹掉而已，所以 1 的个数是不变的。
 *
 *
 */

public class CountingBits {



    public static void main(String[] args) {
        System.out.println(countBits2(5));
    }

    /**
     * Brian Kernighan 算法
     * 令 x=x&(x-1)，该运算将 xx 的二进制表示的最后一个 11 变成 00。因此，对 xx 重复该操作，直到 xx 变成 00，则操作次数即为 xx 的「一比特数」
     *
     * @param n
     * @return
     */
    public static int[] countBits(int n) {
        int[] result = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            result[i] = countOne(i);
        }

        return result;
    }

    public static int countOne(int x) {
        int result = 0;
        while (x > 0) {
            x = x & (x - 1);
            result++;
        }
        return result;
    }


    /**
     * 最高有效位
     * @param n
     * @return
     */
    public static int[] countBits2(int n) {
        int[] bits = new int[n + 1];
        int highBit = 0;
        for (int i = 1; i <= n; i++) {
            if ((i & (i - 1)) == 0) {
                highBit = i;
            }
            bits[i] = bits[i - highBit] + 1;
        }
        return bits;
    }

    /**
     * 最低有效位
     * @param n
     * @return
     */
    public int[] countBits3(int n) {
        int[] bits = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            bits[i] = bits[i >> 1] + (i & 1);
        }
        return bits;
    }


    /**
     * 最低设置位
     * @param n
     * @return
     */
    public int[] countBits4(int n) {
        int[] bits = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            bits[i] = bits[i & (i - 1)] + 1;
        }
        return bits;
    }


}
