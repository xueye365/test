package src.mq.kafka.time;

import java.util.concurrent.TimeUnit;

public class TimeWheelTest {


        public static void main(String[] args) {
            final TimeWheel wheel = new TimeWheel(1, TimeUnit.SECONDS);
            wheel.createTimerTask(new TimerTask() {
                @Override
                public void run() {
                    System.out.println(1111);
                    wheel.createTimerTask(this, 4, TimeUnit.SECONDS);
                }
            }, 3, TimeUnit.SECONDS);
        }

}
