package src.util;
/**
 * @author xueye <xueye@kuaishou.com>
 * Created on 2023-10-26
 */
public class LongUtil {
    // 11111111111111111111111111111111
    private static final long LONG_LOW_32 = 0xFFFFFFFFL;
    // 1111111111111111111111111111111100000000000000000000000000000000
    private static final long LONG_HIGH_32 = 0xFFFFFFFF00000000L;
    private static final int BINARY_DIGIT = 32;


    /**
     * 将两个int 组合为 long
     *
     * @param high 高位
     * @param low  低位
     * @return
     */
    public static long combineInt2Long(int high, int low) {
        return ((long) low & LONG_LOW_32) | (((long) high << BINARY_DIGIT) & LONG_HIGH_32);
    }


    /**
     * 将一个long 拆分为两个 int，返回高位int
     *
     * @param val long数字
     * @return 高位int
     */

    public static int getHighLong(long val) {
        return (int) ((LONG_HIGH_32 & val) >> BINARY_DIGIT);
    }

    /**
     * 将一个long 拆分为两个 int，返回低位int
     *
     * @param val long数字
     * @return 低位int
     */
    public static int getLowLong(long val) {
        return (int) (LONG_LOW_32 & val);
    }

}
