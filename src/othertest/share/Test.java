package src.othertest.share;


import java.util.HashSet;
import java.util.TreeSet;

public class Test {

    public static int a = 0;

    public static void testStackOverflowError() {
        a++;
        testStackOverflowError();
    }

    public static void testOutOfMemoryError() {
        while (true) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                    }
                }
            });
            thread.start();
        }
    }

    public static void main(String[] args) {
//        try {
            testStackOverflowError();
//        } catch (Throwable e) {
//            System.out.println("深度：" + a);
//        }
//        testOutOfMemoryError();
    }



}
