package src.othertest.thread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CycleWait {

    public static void main(String[] args) {
        src.othertest.thread.ThreadDemo demo = new src.othertest.thread.ThreadDemo();
        Thread thread = new Thread(demo);
        thread.start();
        // 循环主线程以等待参数被赋值
//        while (demo.value == null){
//            try {
//                thread.sleep(200);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }

        // join
        // 阻塞当前线程以等待子线程处理完毕
//        try {
//            thread.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.println(demo.value);

        // FutureTask 的方式
        FutureTask task = new FutureTask(new src.othertest.thread.CallableDemo());
        new Thread(task).start();

        if (!task.isDone()){
            System.out.println("not finish, please wait");
        }
        try {
            System.out.println(task.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
