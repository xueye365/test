package src.othertest.arithmetic.recursion;

/**
 * 递归
 */
public class Recursion {


    /**
     * 假如这里有 n 个台阶，每次你可以跨 1 个台阶或者 2 个台阶，请问走这 n 个台阶有多少种走法？
     * 可以根据第一步的走法把所有走法分为两类，第一类是第一步走了 1 个台阶，另一类是第一步走了 2 个台阶。所以 n 个台阶的走法就等于先走 1 阶后，n-1 个台阶的走法 加上先走 2 阶后，n-2 个台阶的走法
     * 因为递归本身就是借助栈来实现的，只不过我们使用的栈是系统或者虚拟机本身提供的，我们没有感知罢了
     *
     * @param n
     * @return
     */
    int f(int n) {
        if (n == 1) return 1;
        if (n == 2) return 2;
        return f(n-1) + f(n-2);
    }

    int f2(int n) {
        if (n == 1) return 1;
        if (n == 2) return 2;
        int ret = 0;
        int pre = 2;
        int prepre = 1;
        for (int i = 3; i <= n; ++i) {
            ret = pre + prepre;
            prepre = pre;
            pre = ret;
        }
        return ret;
    }

}
