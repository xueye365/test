package src.othertest.thread;

public class ThreadDemo implements Runnable{

    String value;

    @Override
    public void run() {
        try {
            Thread.currentThread().sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        value = "new value";
    }


}
