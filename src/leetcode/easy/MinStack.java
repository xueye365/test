package src.leetcode.easy;


/**
 *
 * 剑指offer
 * 包含min函数的栈
 *
 * https://leetcode-cn.com/problems/bao-han-minhan-shu-de-zhan-lcof/
 *
 */

import java.util.Stack;

public class MinStack {

    public static void main(String[] args) {
        MinStack minStack = new MinStack();
//        minStack.push(-2);
//        minStack.push(0);
//        minStack.push(-3);
//        System.out.println(minStack.min());
//        minStack.pop();
//        System.out.println(minStack.top());
//        System.out.println(minStack.min());

        minStack.push(0);
        minStack.push(1);
        minStack.push(0);
        System.out.println(minStack.min());
        minStack.pop();
        System.out.println(minStack.min());

    }

    Stack<Integer> stackA;
    Stack<Integer> stackB;

    public MinStack() {
        stackA = new Stack<>();
        stackB = new Stack<>();
    }

    public void push(int x) {
        stackA.push(x);
        // stackB最下面为标准
        if (stackB.empty() || stackB.peek() >= x) {
            stackB.push(x);
        }
    }

    public void pop() {
        // stackB只看最小值
        if (stackA.pop().equals(stackB.peek())) {
            stackB.pop();
        }
    }

    public int top() {
        return stackA.peek();
    }

    public int min() {
        return stackB.peek();
    }
}