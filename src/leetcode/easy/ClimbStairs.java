package src.leetcode.easy;


import java.util.HashMap;
import java.util.Map;

/**
 *
 * 动态规划
 * 解码方法
 *
 * https://leetcode-cn.com/problems/climbing-stairs/
 *
 */

public class ClimbStairs {



    public static void main(String[] args) {
        System.out.println(climbStairs3(2));
    }

    /**
     * 递归超时
     * @param n
     * @return
     */
    public static int climbStairs(int n) {
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        return climbStairs(n - 1) + climbStairs(n - 2);
    }

    private static Map<Integer, Integer> map = new HashMap();

    static {
        map.put(1,1);
        map.put(2,2);
    }


    public static int climbStairs2(int n) {
        if (map.containsKey(n)) {
            return map.get(n);
        }
        int a = 0;
        if (map.containsKey(n - 1)) {
            a = map.get(n - 1);
        } else {
            a = climbStairs(n - 1);
            map.put(n - 1, a);
        }
        int b = 0;
        if (map.containsKey(n - 2)) {
            b = map.get(n - 2);
        } else {
            b = climbStairs(n - 2);
            map.put(n - 2, b);
        }
        return a + b;
    }



    public static int climbStairs3(int n) {
        int a = 0, b = 1, sum = 1;
        for (int i = 1; i < n; i++) {
            a = b;
            b = sum;
            sum = a + b;
        }
        return sum;
    }


}
