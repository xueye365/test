package src.othertest.thread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * forkjoin 有任务窃取功能，workStealing
 *
 *
 */
public class ForkJoinExample extends RecursiveTask<Integer> {

    public static final int threshold = 2;
    private Integer start;
    private Integer end;

    public ForkJoinExample(Integer start, Integer end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        Integer result = 0;
        if (end - start < threshold) {
            for (Integer i = start; i <= end; i++) {
                result += i;
            }
            System.out.println("start:" + start + "end:" + end);
        } else {
            Integer mid = (end + start) / 2;
            ForkJoinExample example1 = new ForkJoinExample(start, mid);
            ForkJoinExample example2 = new ForkJoinExample(mid + 1, end);
            example1.fork();
            example2.fork();
            return example1.join() + example2.join();
        }
        return result;
    }

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Integer> submit = forkJoinPool.submit(new ForkJoinExample(1, 10));
        try {
            Integer result = submit.get();
            System.out.println(result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


    }




}
