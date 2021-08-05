package src.leetcode.medium;


import java.util.Stack;

/**
 *
 * 剑指offer
 * 栈的压入、弹出序列
 *
 * https://leetcode-cn.com/problems/zhan-de-ya-ru-dan-chu-xu-lie-lcof/
 *
 */


public class ValidateStackSequences {

    public static void main(String[] args) {
        int[] pushed = new int[]{1,2,3,4,5};
        int[] popped = new int[]{4,5,3,2,1};
        boolean b = validateStackSequences(pushed, popped);
        System.out.println(b);
    }




    public static boolean validateStackSequences(int[] pushed, int[] popped) {
        Stack<Integer> s = new Stack();
        int i = 0, j = 0;
        while (true) {
            if (!s.isEmpty() && j < popped.length && s.peek() == popped[j]) {
                s.pop();
                j++;
            } else if (i < pushed.length) {
                s.push(pushed[i]);
                i++;
            } else {
                break;
            }
        }
        if (s.empty() || j == popped.length) {
            return true;
        }
        return false;
    }





}