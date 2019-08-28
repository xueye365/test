package src.othertest.other;


public class Test {

    public static void main(String[] args) {

        for (; ; ) {
            new Thread(() -> {
                try {
                    Thread.sleep(50000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}

