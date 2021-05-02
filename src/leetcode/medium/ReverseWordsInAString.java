package src.leetcode.medium;

import java.util.Stack;

/**
 *
 * 给定一个字符串，逐个翻转字符串中的每个单词。
 * https://leetcode-cn.com/explore/interview/card/bytedance/242/string/1011/
 */
public class ReverseWordsInAString {


    public static void main(String[] args) {
        System.out.println(reverseWords("  hello world!  "));
    }

    public static String reverseWords(String s) {
        s = s.trim();
        Stack stack = new Stack();
        String temp = "";
        for (int i = 0; i < s.length(); i++) {
            if (' ' != s.charAt(i)) {
                temp = temp + s.charAt(i);
            } else {
                if (i + 1 < s.length() && ' ' == s.charAt(i + 1)) {
                   continue;
                }
                stack.push(temp);
                temp = "";
            }
        }
        stack.push(temp);
        String result = "";
        while (!stack.isEmpty()) {
            result = result + stack.pop() + (stack.isEmpty() ? "" : " ");
        }
        return result;

    }


    /**
     * 最优
     * @param s
     * @return
     */
    public String reverseWords2(String s) {
        String[] tmp = s.split(" ");
        StringBuffer result = new StringBuffer("");
        for(int i = tmp.length - 1;i >=0 ;i--){
            if(!tmp[i].equals("")){
                result.append(tmp[i]);
                result.append(" ");
            }
        }
        return result.toString().trim();
    }


}
