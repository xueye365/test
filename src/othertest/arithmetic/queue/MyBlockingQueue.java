package src.othertest.arithmetic.queue;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 阻塞队列
 * @param <E>
 */
public class MyBlockingQueue<E> {
    final ReentrantLock lock ;
    final Condition notFull;
    final Condition notEmpty;
    private int takeIndex = 0;
    private int putIndex = 0;
    //计数器
    private int count = 0;
    //容量
    private final int capacity;
    private final Object[] items;
    //默认按照公平锁实现简易版
    public MyBlockingQueue(int capacity) {
        this.capacity = capacity;
        items = new Object[capacity];
        lock = new ReentrantLock();
        notFull = lock.newCondition();
        notEmpty = lock.newCondition();
    }

    public void put(E e) throws InterruptedException {
        //响应中断
        lock.lockInterruptibly();
        try {
            //元素个数等于数组长度
            while (items.length == count) {
                notFull.await();
            }
            items[putIndex] = e;
            ++putIndex;
            System.out.println("生产者线程:" + count + ",putIndex=" + putIndex);
            if (putIndex == items.length) {
                putIndex = 0;
            }
            count++;
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public E take() throws InterruptedException {
        lock.lockInterruptibly();
        try {
            //元素个数为0
            while (count == 0) {
                notEmpty.await();
            }
            E e = (E)items[takeIndex];
            items[takeIndex] = null;
            System.out.println("消费者线程count：" + count + ",takeIndex=" + takeIndex);
            //刚开始写成了++takeIndex == count
            if (++takeIndex == items.length) {
                System.out.println("居然为0");
                takeIndex = 0;
            }

            count--;
            notFull.signal();
            return e;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyBlockingQueue<String> queue = new MyBlockingQueue<>(10);

        Thread producer1 = new Thread(new Runnable() {
            @Override
            public void run() {
                int i=0;
                while (true) {
                    try {
                        queue.put((i++) + "");
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

       /* Thread producer2 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        queue.put(Math.random() + "");
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });*/

        Thread consumer = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        String ele = queue.take();
                        System.out.println("消费者线程获取元素" + ele);
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        producer1.start();
        // producer2.start();
        consumer.start();

        producer1.join();
        //producer2.join();
        consumer.join();
    }
}

