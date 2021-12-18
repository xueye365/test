package src.leetcode.bytesBeat;


import java.util.Deque;
import java.util.LinkedList;

/**
 *
 * 最小栈
 *
 * https://leetcode-cn.com/problems/min-stack/
 *
 */
public class MinStack {


    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        System.out.println(minStack.getMin());
        minStack.pop();
        minStack.top();
        System.out.println(minStack.getMin());


    }

    private Deque<Integer> stack;
    private Deque<Integer> minStack;

    public MinStack() {
        stack = new LinkedList();
        minStack = new LinkedList();
        minStack.push(Integer.MAX_VALUE);
    }

    public void push(int val) {
        stack.push(val);
        if (minStack.size() > 0 && minStack.peek() >= val) {
            minStack.push(val);
        }
    }

    public void pop() {
        Integer pop = stack.pop();
        if (pop.equals(minStack.peek())) {
            minStack.pop();
        }
    }

    public int top() {
        return stack.size() > 0 ? stack.peek() : 0;
    }

    public int getMin() {
        return minStack.size() > 0 ? minStack.peek() : 0;
    }

}
