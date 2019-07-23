package src.othertest.thread;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ThreadPoolDemo{

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Future submit = executorService.submit(new CallableDemo());
        try {
            System.out.println(submit.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }
    }



    // 被回收的引用会放到queue中
    public static void testReference() {
        ArrayList a = new ArrayList();
        ReferenceQueue queue = new ReferenceQueue();
        for (int i = 0; i < 10; i++) {
            a.add(new PhantomReference<>(new String("asfd"), queue));
        }
        System.gc();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Reference ref;
        while ((ref = queue.poll()) != null) {
            System.out.println(ref);
        }
    }


}
