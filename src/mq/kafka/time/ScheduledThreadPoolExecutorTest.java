package src.mq.kafka.time;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ScheduledThreadPoolExecutorTest {

    private static ScheduledExecutorService scheduler;

    public static void main(String[] args) throws Exception {
        scheduler = Executors.newScheduledThreadPool(5);
        System.out.println("main thread time : " + formatDateToString(new Date()));
        // 循环任务，按照上一次任务的发起时间计算下一次任务的开始时间
        scheduler.scheduleAtFixedRate(new Runnable() {
                @Override
                public void run() {
                    System.out.println(
                            " 开始 threadId = "
                                    + Thread.currentThread().getId()
                                    + ",,,threadName = " + Thread.currentThread().getName()
                                    + ",,,时间" +  formatDateToString(new Date())
                    );

                    try {
                        Thread.sleep(1000);
                        System.out.println(
                                " 结束 threadId = "
                                        + Thread.currentThread().getId()
                                        + ",,,threadName = " + Thread.currentThread().getName()
                                        + ",,,时间" + formatDateToString(new Date())
                        );

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //模拟抛出异常
                    if (1 == 1) {
                        throw new RuntimeException("异常");
                    }
                }
            },
        0,1,
            TimeUnit.SECONDS
        );



        // 是以上一个任务开始的时间计时，120秒过去后，检测上一个任务是否执行完毕，如果上一个任务执行完毕，则当前任务立即执行，如果上一个任务没有执行完毕，则需要等上一个任务执行完毕后立即执行
//        scheduler.scheduleAtFixedRate();
        // 是以上一个任务结束时开始计时，120秒过去后，立即执行。
//        scheduler.scheduleWithFixedDelay();

        // 关于定时线程池，好多人认为设置好频率（比如1Min），它会按照这个间隔按部就班的工作。但是，如果其中一次调度任务卡住的话，不仅这次调度失败，而且整个线程池也会停在这次调度上。
        //
        //源码中的注解也清楚的写着 If any execution of the task encounters an exception, subsequent executions are suppressed.
        //
        //因此，注意在使用的时候涉及一些可能因为网络或者其他一些原因导致异常，要注意用try...catch...捕获

    }

    public static String formatDateToString(Date time) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        return sdf.format(time);
    }


}
