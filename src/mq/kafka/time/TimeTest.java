package src.mq.kafka.time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TimeTest {

    private static Timer timer = new Timer();
    //任务虽然执行完了，但进程还未销毁，呈红色状态。
    //创建一个 Timer 就是启动一个新的线程，这个新启动的线程并不是守护线程，所以它一直在运行。
    static public class MyTask extends TimerTask {
        @Override
        public void run() {
            System.out.println("运行了！时间为：" + new Date());
            // 从任务列表中清除
//            this.cancel();
        }
    }
    // 每两秒执行一次任务，不能设置为守护线程，不然很快就会停止
    public static void main(String[] args) {
        try {
            MyTask task = new MyTask();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateString = "2023-3-28 11:28:00";
            Date dateRef = sdf.parse(dateString);
            System.out.println("字符串时间：" + dateRef.toLocaleString() + "当前时间：" + new Date().toLocaleString());
            timer.schedule(task, dateRef,2000);
            // 将任务队列中的全部任务清空
//            timer.cancel();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


    static int i = 0;
    // Timer 类中的 cancel() 方法有时并不一定会停止执行任务之花，而是正常执行。
    //并没有停止执行
    //这是因为 Timer 类中的 cancel() 方法有时并没有争抢到 queue 锁，所以 TimerTask 类中的任务继续正常执行。
//    public static void main(String[] args) {
//        while (true) {
//            try {
//                i++;
//                Timer timer = new Timer();
//                MyTask task = new MyTask();
//                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                String dateString = "2022-08-27 11:00:00";
//                Date dateRef = sdf.parse(dateString);
//                timer.schedule(task, dateRef);
//                timer.cancel();
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
//        }
//    }

}
