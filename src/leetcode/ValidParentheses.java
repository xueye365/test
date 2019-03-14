package src.leetcode;

import java.util.Stack;

/**
 * 有效的括号
 *
 * https://leetcode-cn.com/explore/interview/card/tencent/221/array-and-strings/901/
 *
 */
public class ValidParentheses {

    public static void main(String[] args) {
        System.out.println(isValid(""));
    }
    public static boolean isValid(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        Stack stack = new Stack();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(' || s.charAt(i) == '[' ||  s.charAt(i) == '{') {
                stack.push(s.charAt(i));
            }
            if (s.charAt(i) == ')' || s.charAt(i) == ']' ||  s.charAt(i) == '}') {
                char p;
                try {
                     p = (char)stack.pop();
                } catch (Exception e) {
                    return false;
                }
                if ((s.charAt(i) == ')' && p =='(')
                        || (s.charAt(i) == ']' && p =='[')
                        || (s.charAt(i) == '}' && p =='{')) {
                    continue;
                } else {
                    return false;
                }
            }
        }
        if (stack.empty()) {
            return true;
        } else {
            return false;
        }
    }
}
