package leetcode.other;

public class ListNode {
    public int val;
    public ListNode next;

    public ListNode() {
    }

    public ListNode(int node) {
        this.val = node;
    }

    public ListNode(int node, ListNode next) {
        this.val = node;
        this.next = next;
    }

    public boolean hasNext() {
        return this.next != null;
    }

    public int getNode() {
        return val;
    }

    public void setNode(int node) {
        this.val = node;
    }

    public ListNode getNext() {
        return next;
    }

    public void setNext(ListNode next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "ListNode{" +
                "val=" + val +
                ", next=" + next +
                '}';
    }
}