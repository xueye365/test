package src.othertest.share;

import java.util.ArrayList;
import java.util.List;

public class InternDifference {
    /**
     *
     * 1.6结果：false
     * 1.7结果：true
     *
     * 因为1.6 常量池在方法区，当发现常量池没有数据的时候会在常量池创建字符串并返回引用
     * 因为1.7 常量池在堆区，当发现常量池没有数据的时候---查看堆区是否有字符串----有----将对象引用入常量池
     *                                                                ----无----在常量池创建字符串并返回引用
     *
     * 所以这个例子中1.7的aa在常量池中存储的是引用，引用指向相同对象
     *
     */
    public static int a = 0;
    public static void test() {
        String s3 = new String("a") + new String("a");
        s3.intern();
        String s4 = "aa";
        System.out.println(s3 == s4);
        InternDifference a = new InternDifference();
    }

    public static void test2() {
        //使用List保持着常量池引用，避免Full GC回收常量池行为
        List<String> list = new ArrayList<>();
        int i = 0;
        while(true){
            list.add(String.valueOf(i++).intern());
        }
    }

    public static int test3() {
        int a = 1;
        int b = 2;
        int c = a + b;
        return c;
    }

    public static void main(String[] args) {
        test3();
    }

}
