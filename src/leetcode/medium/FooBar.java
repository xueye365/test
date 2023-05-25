package src.leetcode.medium;

/**
 * https://leetcode.cn/problems/print-foobar-alternately/
 * 1115. 交替打印 FooBar
 */
public class FooBar {
    private int n;

    Object object = new Object();
    private volatile boolean type = true;

    public FooBar(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            synchronized (object) {
                while (!type) {
                    object.wait();
                }
                printFoo.run();
                type = false;
                object.notify();
            }
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            synchronized (object) {
                while (type) {
                    object.wait();
                }
                printBar.run();
                type = true;
                object.notify();
            }
        }
    }
}
