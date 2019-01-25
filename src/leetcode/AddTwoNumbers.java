package leetcode;


/**
 * 链表两个数相加
 *
 * https://leetcode-cn.com/problems/add-two-numbers/
 *
 */
public class AddTwoNumbers {

    public static void main(String[] args) {
        ListNode node1 = new ListNode(3);
        ListNode node2 = new ListNode(4, node1);
        ListNode node3 = new ListNode(2, node2);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(6, node4);
        ListNode node6 = new ListNode(5, node5);
        ListNode result = addTwoNumbers(node3, node6);
        System.out.println(result);

    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        return addTwoNumbers2(null, l1, l2, false);
    }

    public static ListNode addTwoNumbers2(ListNode result, ListNode l1, ListNode l2, Boolean add) {
        boolean flag = false;
        ListNode next = new ListNode();
        if (result != null) {
            if (l1 == null && l2 == null) {
                if (add) {
                    result.setNode(1);
                }
                return result;
            }
            if (l1 == null) {
                if (add) {
                    result.setNode(l2.getNode() + 1);
                    return result;
                }
            }
            if (l2 == null) {
                if (add) {
                    result.setNode(l1.getNode() + 1);
                    return result;
                }
            }
        }
        int sum = l1.getNode() + l2.getNode();
        if (add) {
            sum++;
        }
        if (sum >= 10) {
            next.setNode(sum-10);
            flag = true;
        } else {
            next.setNode(sum);
            flag = false;
        }

        if (l1.getNext() == null || l2.getNext() == null) {
            return next;
        } else {
            next.setNext(addTwoNumbers2(next, l1.getNext(), l2.getNext(), flag));
            return next;
        }


    }

    public static ListNode addTwoNumbers3(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode();
        ListNode curr = dummyHead;
        boolean flag = false;
        while (l1 != null || l2 != null) {
            int sum = l1.getNode() + l2.getNode();
            if (flag) {
                sum++;
            }
            if (sum >= 10) {
                curr.setNode(sum-10);
                flag = true;
            } else {
                curr.setNode(sum);
                flag = false;
            }
            if (l1 != null) {
                l1 = l1.getNext();
            }
            if (l2 != null) {
                l2 = l2.getNext();
            }
            curr.setNext(new ListNode());
            curr = curr.getNext();
        }
        return dummyHead;
    }


}


class ListNode{
    int val;
    ListNode next;
    public ListNode(){
    }
    public ListNode(int node){
        this.val = node;
    }
    public ListNode(int node, ListNode next){
        this.val = node;
        this.next = next;
    }
    public boolean hasNext(){
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