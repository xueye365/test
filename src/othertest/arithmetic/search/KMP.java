package src.othertest.arithmetic.search;

/**
 * KMP
 *
 * https://www.zhihu.com/question/21923021
 *
 *
 *
 * 如果在 j 处字符不匹配
 * 主字符串中 i 指针之前的 PMT[j −1] 位就一定与模式字符串的第 0 位至第 PMT[j−1] 位是相同的
 *
 */
public class KMP {


    public static void main(String[] args) {
//        kmp(new char[]{'a','b','c','a','c','a','b','c','b','c','b','a','c','a','b','c'}, 16, new char[]{'c','b','a','c','a','b','c'}, 7);
//        kmp(new char[]{'a','b','c','a','c','a','b','c','b','c','b','a','c','a','b','c'}, 16, new char[]{'c','b','a','c','a','b','c'}, 7);
        kmp(new char[]{'a','b','c','a','c','a','b','c','b','c','b','a','c','a','b','c'}, 16, new char[]{'a','b','c','a','b','d','a'}, 7);
    }



//    // a, b分别是主串和模式串；n, m分别是主串和模式串的长度。
//    public static int kmp(char[] a, int n, char[] b, int m) {
//        int[] next = getNexts(b, m);
//        int j = 0;
//        for (int i = 0; i < n; ++i) {
//            while (j > 0 && a[i] != b[j]) { // 一直找到a[i]和b[j]
//                j = next[j - 1] + 1;
//            }
//            if (a[i] == b[j]) {
//                ++j;
//            }
//            if (j == m) { // 找到匹配模式串的了
//                return i - m + 1;
//            }
//        }
//        return -1;
//    }
//
//
//    // b表示模式串，m表示模式串的长度
//    private static int[] getNexts(char[] b, int m) {
//        int[] next = new int[m];
//        next[0] = -1;
//        int k = -1;
//        for (int i = 1; i < m; ++i) {
//            while (k != -1 && b[k + 1] != b[i]) {
//                k = next[k];
//            }
//            if (b[k + 1] == b[i]) {
//                ++k;
//            }
//            next[i] = k;
//        }
//        return next;
//    }


    // 这个比较好理解
    private static int kmp(char[] a, int n, char[] b, int m) {
        int i = 0;
        int j = 0;
        int[] next = getNext(b, m);
        while (i < n && j < m) {
            if (a[i] == b[j]) {
                i++;
                j++;
            } else {
                int i1 = next[j];
                if (i1 == j) {
                    i++;
                }
                j = i1;
            }
        }
        if (j == m)
            return i - j;
        else
            return -1;
    }

    /**
     * key 表示子串的长度
     * value 表示最长可匹配子串的长度
     */
    private static int[] getNext(char[] b, int m){
        int[] next = new int[m];
        next[0] = 0;
        int i = 1, j = 0;
        while (i < m - 1) {
            // b[i] 是最长后缀，b[j]是最长前缀，如果前后能匹配，则两个游标同时向后移动一位，长度加一
            if (b[i] == b[j]) {
                ++i;
                ++j;
                next[i] = j;
                // 如果前后子串不能匹配，则重新开始匹配
            } else {
                ++i;
                j = 0;
            }
        }
        return next;
    }



}
