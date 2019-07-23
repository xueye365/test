package src.othertest.thread;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 简单阻塞队列
 */
public class ProductAndConsumer<T> {

    int length;
    Object[] objects;
    int start;
    int end;
    int count;
    Lock lock = new ReentrantLock();
    Condition notNull = lock.newCondition();
    Condition notFull = lock.newCondition();

    ProductAndConsumer(int length) {
        this.length = length;
        this.objects = new Object[length];
        this.start = 0;
        this.end = 0;
        this.count = 0;
    }

    /**
     * 使用synchronized实现
     *
     * @param obj
     */
    synchronized public void put(T obj) {
        if (this.count == length) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            this.objects[end] = obj;
            if (++end == length) {
                end = 0;
            }
            count++;
            this.notifyAll();
        }

    }

    synchronized public T get() {
        if (this.count == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            T obj = (T) this.objects[start];
            if (++start == length) {
                start = 0;
            }
            count--;
            this.notifyAll();
            return obj;
        }
        return null;
    }


    /**
     * 使用lock实现
     *
     * @param obj
     */
    public void put2(T obj) {
        lock.lock();
        if (this.count == length) {
            try {
                notFull.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            this.objects[end] = obj;
            if (++end == length) {
                end = 0;
            }
            count++;
            notNull.signal();

        }

    }

    public T get2() {
        lock.lock();
        if (this.count == 0) {
            try {
                notNull.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            T obj = (T) this.objects[start];
            if (++start == length) {
                start = 0;
            }
            count--;
            notFull.signal();
            return obj;
        }
        return null;
    }

    /**
     * 验证interrupt是不会结束线程的
     */
    public static void test() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                int i = 0;
                try {
                    while (true) {
                        Thread.sleep(200);
                        i++;
                        System.out.println(i);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread t = new Thread(runnable);
        System.out.println(t.getState());
        t.start();
        System.out.println(t.getState());
        t.interrupt();
        // 这行会打印RUNNABLE，因为它会等待线程运行完，并不会直接退出
        System.out.println(t.getState());
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(t.getState());
    }

    public static void main(String[] args) {
//        ProductAndConsumer productAndConsumer = new ProductAndConsumer(3);
//        productAndConsumer.put2(1);
//        productAndConsumer.put2(2);
//        productAndConsumer.put2(3);
//        System.out.println(productAndConsumer.get2());
//        System.out.println(productAndConsumer.get2());
//        System.out.println(productAndConsumer.get2());
//        System.out.println(productAndConsumer.get2());

//        test();

    }


}


