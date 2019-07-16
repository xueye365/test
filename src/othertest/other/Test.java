package src.othertest.other;


import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class Test {

    // 被回收的饮用会放到queue中
    public static void main(String[] args) {
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

