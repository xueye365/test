package src.leetcode.easy;


/**
 * https://leetcode.cn/problems/length-of-last-word/submissions/
 * 最后一个单词的长度
 */


public class LengthOfLastWord {


    public static void main(String[] args) {
        int hello_world = lengthOfLastWord("a");
        System.out.println(hello_world);
    }


    public static int lengthOfLastWord(String s) {
        char[] chars = s.toCharArray();
        int flag = 0;
        for (int i = chars.length - 1; i >= 0 ; i--) {
            if (chars[i] != ' ') {
                flag++;
            } else {
                if (flag > 0) {
                    return flag;
                }
            }
        }
        return flag;
    }



}