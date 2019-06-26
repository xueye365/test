package src.othertest.other;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


/**
 * 将数组分组，不同的线程执行不同组的数据
 */
public class ArraySplitAndCallableRun {

    public static void main(String[] args) {
        List<Integer> origin = new ArrayList<>();
        for (int i = 0; i < 3000; i++) {
            origin.add(i);
        }

        int threadWrok = 500;
        int threadNum = origin.size() / threadWrok + 1;
        ExecutorService executorService = Executors.newFixedThreadPool(threadNum);
        List<Callable<Integer>> callables = new ArrayList<>();
        for (int i = 0; i < threadNum; i++) {
            List<Integer> subList;
            if (i == threadNum - 1) {
                if (origin.size() % threadWrok == 0) {
                    break;
                }
                subList = origin.subList(threadWrok * i, origin.size());
            } else {
                subList = origin.subList(threadWrok * i, threadWrok * (i + 1));
            }
            List<Integer> finalStrings = subList;
            callables.add(new Callable() {
                @Override
                public Object call() throws Exception {
                    System.out.println(Thread.currentThread().getName() + finalStrings.toString());
                    return 1;
                }
            });
        }

        try {
            List<Future<Integer>> futures = executorService.invokeAll(callables);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }







}
