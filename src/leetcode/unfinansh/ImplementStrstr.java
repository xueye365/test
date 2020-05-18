package leetcode.unfinansh;


import java.util.ArrayList;
import java.util.List;

/**
 * 实现 strStr()
 * https://leetcode-cn.com/problems/implement-strstr/
 *
 * KMP算法
 *
 */


public class ImplementStrstr {

    public static void main(String[] args) {
        System.out.println(strStr("", ""));
    }

//    public static void main(String[] args) {
//        test();
//    }

    /**
     * 暴力解法
     * @param haystack
     * @param needle
     * @return
     */
    public static int strStr(String haystack, String needle) {

        int strLen = haystack.length(), subLen = needle.length();
        if (subLen == 0) return 0;
        for (int index = 0; index < strLen; index++) {
            /* 首字符不匹配则跳过 */
            if (haystack.charAt(index) != needle.charAt(0)) continue;
            /* 超出边界，无法获取结果 */
            if (index + subLen > strLen) break;
            int tmp = index + 1;
            /* 内循环判断是否相等，若完全相等，则 tmp == index + subLen */
            for(;tmp < index + subLen; tmp++)
                if (haystack.charAt(tmp) != needle.charAt(tmp - index)) break;
            if (tmp == index + subLen) return index;
        }
        return -1;

    }


    /**
     * KMP算法
     * @param haystack
     * @param needle
     * @return
     */
    public int strStr2(String haystack, String needle) {
        int strLen = haystack.length(), subLen = needle.length();
        if (subLen == 0) return 0;
        if (strLen == 0) return -1;
        // 构建状态机
        int[][] FSM = new int[subLen][256];
        int X = 0, matchChar = 0;
        for (int i = 0; i < subLen; i++) {
            matchChar = (int) needle.charAt(i);
            for (int j = 0; j < 256; j++) {
                // 当前状态 + 匹配失败字符 = 孪生词缀状态 + 匹配字符
                FSM[i][j] = FSM[X][j];
            }
            FSM[i][matchChar] = i + 1;
            if (i > 1) {
                // 下一孪生前缀状态 = X + matchChar
                X = FSM[X][matchChar];
            }
        }
        // 匹配子串
        int state = 0;
        for (int i = 0; i < strLen; i++) {
            state = FSM[state][haystack.charAt(i)];
            if (state == subLen) {
                return i - subLen + 1;
            }
        }
        return -1;
    }



}

