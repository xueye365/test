package src.othertest.thread;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class UnsafeAndReadWriteLock {

    public static void main(String[] args) {
        Class<Unsafe> unsafeClass = Unsafe.class;
        try {
            Field theUnsafe = unsafeClass.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            Unsafe unsafe = (Unsafe)theUnsafe.get(null);
            unsafe.park(false, 5000000000L);
            System.out.println(1);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        ReentrantLock reentrantLock = new ReentrantLock();

        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
        ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();

        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000_0000; i++) {
            readLock.lock();
            readLock.unlock();
        }
        long end = System.currentTimeMillis();
        System.out.println("读锁：" + (end - start));


        long start2 = System.currentTimeMillis();
        for (int i = 0; i < 1000_0000; i++) {
            writeLock.lock();
            writeLock.unlock();
        }
        long end2 = System.currentTimeMillis();
        System.out.println("写锁：" + (end2 - start2));


        long start3 = System.currentTimeMillis();
        for (int i = 0; i < 1000_0000; i++) {
            reentrantLock.lock();
            reentrantLock.unlock();
        }
        long end3 = System.currentTimeMillis();
        System.out.println("lock：" + (end3 - start3));



    }
}

