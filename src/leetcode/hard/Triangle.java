package src.leetcode.hard;


import java.util.Arrays;
import java.util.Comparator;

/**
 * 字节跳动
 *
 * 俄罗斯套娃信封问题
 *
 * https://leetcode-cn.com/problems/russian-doll-envelopes/
 * https://leetcode-cn.com/explore/interview/card/bytedance/246/dynamic-programming-or-greedy/1031/
 *
 */
public class Triangle {


    public static void main(String[] args) {
        int[][] ints = {{5, 4}, {6, 4}, {6, 7}, {2, 3}};
        System.out.println(maxEnvelopes(ints));
    }


    /**
     * 宽度递增，高度递减来排序，这是为了防止计算最长子序列长度时对相同宽度不同高度的信封计算多次。
     * 后面的问题可以简化成求一维数组最长子序列长度。
     *
     * @param envelopes
     * @return
     */


    public static int maxEnvelopes(int[][] envelopes) {
        int n = envelopes.length;
        if (n == 0) {
            return 0;
        }
        // 排序，按宽度升序排列，如果宽度一样，则按高度降序排列
        Arrays.sort(envelopes, (o1, o2) -> o1[0] != o2[0] ? o1[0] - o2[0] : o2[1] - o1[1]);

        // 对高度数组寻找最长递增子序列
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (envelopes[j][1] < envelopes[i][1]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }




    public static int cpmpare() {
        Test[] test = new Test[6];
        test[0] = new Test(1, 1);
        test[1] = new Test(2, 3);
        test[2] = new Test(1, 2);
        test[3] = new Test(2, 2);
        test[4] = new Test(2, 2);
        test[5] = new Test(1, 2);
        for (Test test1 : test) {
            System.out.print(test1.param1 + " " + test1.param2);
            System.out.println();
        }
        Arrays.sort(test, new Comparator<Test>() {
            @Override
            public int compare(Test o1, Test o2) {
                if (o1 == o2) {
                    return 0;
                } else {
                    if (o1.param1 - o2.param1 > 0) return 1;
                    if (o1.param1 - o2.param1 < 0) return -1;
                    if (o1.param1 - o2.param1 == 0) {
                        if (o1.param2 - o2.param2 > 0) return 1;
                        if (o1.param2 - o2.param2 < 0) return -1;
                        if (o1.param2 - o2.param2 == 0) return 0;
                    }
                }
                return 0;
            }
        });
        System.out.println();
        for (Test test1 : test) {
            System.out.print(test1.param1 + " " + test1.param2);
            System.out.println();
        }

        Arrays.sort(test, (o1, o2) -> o1.param1 != o2.param1 ? o1.param1 - o2.param1 : o1.param2 - o2.param2);
        return 0;
    }

    static class Test{
        Integer param1;
        Integer param2;

        public Test(Integer param1, Integer param2) {
            this.param1 = param1;
            this.param2 = param2;
        }
    }


}
