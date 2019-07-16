package src.othertest.thread;

import java.util.concurrent.Callable;

public class CallableDemo implements Callable{


    @Override
    public Object call() throws Exception {
        String value = "new Value";
        System.out.println("Ready to work");
        Thread.sleep(3000);
        System.out.println("Work Done");
        return value;
    }
}
