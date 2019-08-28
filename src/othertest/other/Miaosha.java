package src.othertest.other;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

/**
 * 秒杀系统
 */
public class Miaosha {

    private static final int USER_NUM = 1000;
    private static final Map<String, TGoodsInfo> GOOD_LEFT = new HashMap();
    // 模拟同时抢购
    private static CountDownLatch countDownLatch = new CountDownLatch(USER_NUM);
    // 统计使用时间(加1是加上主线程)
    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(USER_NUM + 1);
    private static int successPerson = 0;
    private static int saleOutNum = 0;

    static {
        GOOD_LEFT.put("xiaomi", new TGoodsInfo("xiaomi", 100, 0));
        GOOD_LEFT.put("pingguo", new TGoodsInfo("pingguo", 90, 0));
        GOOD_LEFT.put("huawei", new TGoodsInfo("huawei", 8, 0));
    }

    public static void main(String[] args) throws InterruptedException, BrokenBarrierException {
        long start = System.currentTimeMillis();
        for (int i = 0; i < USER_NUM; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        countDownLatch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    boolean updateGoodsAmount = updateGoodsAmount("xiaomi", 3);
                    if (updateGoodsAmount) {
                        synchronized (countDownLatch) {
                            successPerson++;
                            saleOutNum+=3;
                        }
                    }
                    try {
                        cyclicBarrier.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
            countDownLatch.countDown();
        }
        cyclicBarrier.await();
        long end = System.currentTimeMillis();
        System.out.println(end - start);
        System.out.println(successPerson);
        System.out.println(saleOutNum);
    }


    public static boolean updateGoodsAmount(String code, int buys) {
        TGoodsInfo info = selectByPrimaryKeySql(code);
        Integer amount = info.getAmount();
        if (amount < buys) {
            return false;
        }


        // 1.普通线程
//        return updateAmountSql(code, buys) > 0 ? true : false;


        // 2.带版本号更新库存，有瓶颈，机器硬盘并发能力在三百，固态硬盘并发能力七百
        Integer version = info.getVersion();
        if(updateAmountByVersionSql(code, buys, version) > 0){
            return true;
        }
        // 如果更新失败，当前线程休眠，并错峰执行
        waitForLock();
        // 递归调用方法本身
        return updateGoodsAmount(code, buys);



    }


    // 乐观锁，通过版本号修改线程
    private static Integer updateAmountByVersionSql(String code, int buys, int version) {
        TGoodsInfo tGoodsInfo = GOOD_LEFT.get(code);
        if (tGoodsInfo != null && tGoodsInfo.getVersion() == version) {
            tGoodsInfo.setAmount(tGoodsInfo.getAmount() - buys);
            tGoodsInfo.setVersion(tGoodsInfo.getVersion() - 1);
            return 1;
        }
        return 0;
    }

    // 普通修改库存
    private static Integer updateAmountSql(String code, int buys) {
        TGoodsInfo tGoodsInfo = GOOD_LEFT.get(code);
        if (tGoodsInfo != null) {
            tGoodsInfo.setAmount(tGoodsInfo.getAmount() - buys);
            return 1;
        }
        return 0;
    }

    private static TGoodsInfo selectByPrimaryKeySql(String code) {
        return GOOD_LEFT.get(code);
    }

    private static void waitForLock() {
        try {
            Thread.sleep(new Random().nextInt(10) + 1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class TGoodsInfo {
    private String name;
    private Integer amount;
    private Integer version;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public TGoodsInfo(String name, Integer amount, Integer version) {
        this.name = name;
        this.amount = amount;
        this.version = version;
    }
}
