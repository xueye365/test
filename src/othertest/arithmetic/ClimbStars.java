package src.othertest.arithmetic;

/**
 *
 * 动态规划
 * @Author:lalt
 * @Date:2019/3/6
 * @Desc:爬楼梯假设你正在爬楼梯。需要n步你才能到达楼顶。每次你可以爬1或2个台阶。
 * 你有多少种不同的方法可以爬到楼顶
 **/
public class ClimbStars {

    public static void main(String[] args) {
        int n = 22;
        long timeStime = System.currentTimeMillis();
        System.out.println(ClimbStars.climb(n));
        System.out.println("递归task："+(System.currentTimeMillis()-timeStime));
        System.out.println(ClimbStars.climbStairs(n));
    }

    /**
     * 递归实现
     * @param n
     * @return
     */
    public static int climb(int n){
        if(n == 1 || n == 2){
            return  n;
        }else{
            return climb(n-1)+climb(n-2);
        }
    }

    /**
     * 动态规划
     * @param n
     * @return
     */
    public static int climbStairs(int n) {
        int a = 1, b = 1;
        long timeStime = System.currentTimeMillis();
        while (--n > 0) {
            b += a;
            a = b - a;
        }
        System.out.println("动态规划耗时："+(System.currentTimeMillis()-timeStime));
        return b;
    }

}
