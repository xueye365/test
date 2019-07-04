package src.leetcode;

import java.util.Stack;

/**
 *
 * 给定一个字符串，逐个翻转字符串中的每个单词。
 * https://leetcode-cn.com/explore/interview/card/bytedance/242/string/1011/
 */
public class ReverseWords {


    public static void main(String[] args) {
        System.out.println(reverseWords("the sky is blue"));
    }

    public static String reverseWords(String s) {
        Stack stack = new Stack();
        String temp = "";
        for (int i = 0; i < s.length(); i++) {
            if (' ' != s.charAt(i)) {
                temp = temp + s.charAt(i);
            } else {
                stack.push(temp);
                temp = "";
            }
        }
        stack.push(temp);
        String result = "";
        while (!stack.isEmpty()) {
            result = result + stack.pop() + " ";
        }
        return result;

    }


}
