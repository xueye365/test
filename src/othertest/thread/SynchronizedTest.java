package src.othertest.thread;

public class SynchronizedTest {
    /**
     * 类锁和对象锁互不干扰，因为上的是两个对象的锁
     */

    public void test() {
        synchronized(this){
            System.out.println("锁该对象实例");
        }
    }

    public synchronized void test2() {
        System.out.println("锁该对象实例");
    }

    public void test3() {
        synchronized(SynchronizedTest.class){
            System.out.println("锁该类对象");
        }
    }

    public static synchronized void test4() {
        System.out.println("锁该类对象");
    }


}
