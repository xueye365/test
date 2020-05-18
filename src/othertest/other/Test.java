package othertest.other;


import com.seventh7.mybatis.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class Test {

    public static void main(String[] args) {

//        for (; ; ) {
//            new Thread(() -> {
//                try {
//                    Thread.sleep(50000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }).start();
//        }

//        Integer a = new Integer(2);
//        testr(a);
//        System.out.println(a);
        Double aDouble = new Double(-1.50);
        BigDecimal bigDecimal = BigDecimal.valueOf(aDouble).setScale(0, RoundingMode.HALF_UP);

        System.out.println(bigDecimal);
    }

    public static void testr(Integer a) {
        a = a+2;
    }
}

