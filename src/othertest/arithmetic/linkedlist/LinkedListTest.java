package src.othertest.arithmetic.linkedlist;


/**
 * 链表测试
 */
public class LinkedListTest {

    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2, node1);
        Node node3 = new Node(3, node2);
        Node node4 = new Node(4, node3);
        System.out.println(node4);
        System.out.println(reverse(node4));

    }

    /**
     * 单链表反转
     * @param node
     */
    public static Node reverse(Node node) {
        if (node == null) {return null;}
        Node pre = null;
        Node next = node;
        while (next != null) {
            Node temp = next.getNext();
            next.setNext(pre);
            pre = next;
            next = temp;
        }
        return pre;
    }

    /**
     * 单链表反转
     * @param node
     */
    public static Node reverse2(Node node) {
        if (node == null) {return null;}
        Node pre = null;
        Node temp = node;
        while (temp != null) {
            Node p = temp.getNext();
            p.setNext(pre);
            pre = p;
            temp = temp.getNext();
        }
        return pre;
    }

    /**
     * 链表中环的检测
     * @param list
     * @return
     */
    public static boolean checkCircle(Node list) {
        if (list == null) {return false;}

        Node fast = list;
        Node slow = list;

        while (fast != null && fast.getNext() != null) {
            fast = fast.getNext().getNext();
            slow = slow.getNext();
            if (fast == slow) {
                return true;
            }
        }
        return false;

    }

    /**
     * 有序链表合并
     * @param la
     * @param lb
     * @return
     */
    public static Node mergeSortedLists(Node la, Node lb){
        if (la == null) {
            return lb;
        }
        if (lb == null) {
            return la;
        }
        Node head;
        Node p = la;
        Node q = lb;
        if (p.getData() > q.getData()) {
            head = p;
            p = p.getNext();
        } else {
            head = q;
            q = q.getNext();
        }
        Node r = head;
        while (la != null && lb != null) {
            if (p.getData() > q.getData()) {
                r.setNext(p);
                p = p.getNext();
            } else {
                r.setNext(q);
                q = q.getNext();
            }
            r = r.getNext();
        }

        if (p != null) {
            r.setNext(p);
        }
        if (q != null) {
            r.setNext(q);
        }
        return head;

    }


    /**
     * 删除链表倒数第n个结点
     * @return
     */
    public static void deleteLastN(Node node, int n){
        if (node == null || n <= 0) {
            return;
        }
        Node p = node;
        Node fast = p;
        int i = 0;
        while (p != null && i < n) {
            fast = fast.getNext();
            i++;
        }
        if (fast == null) {
            return;
        }
        Node slow = node;
        Node prev = null;
        while (fast.getNext() != null) {
            fast = fast.getNext();
            prev = slow;
            slow = slow.getNext();
        }

        if (prev == null) {
            node = node.getNext();
        } else {
            prev.setNext(prev.getNext().getNext());
        }
        return;

    }


    // 求中间结点
    public static Node findMiddleNode(Node list) {
        if (list == null) return null;

        Node fast = list;
        Node slow = list;

        while (fast.getNext() != null && fast.getNext().getNext() != null) {
            fast = fast.getNext().getNext();
            slow = slow.getNext();
        }

        return slow;
    }




}


class Node {
    private int data;
    private Node next;

    public Node() {
    }

    public Node(int data) {
        this.data = data;
    }

    public Node(int data, Node next) {
        this.data = data;
        this.next = next;
    }

    public int getData() {
        return data;
    }

    public Node getNext() {
        return next;
    }

    public void setData(int data) {
        this.data = data;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public boolean hasNext() {
        return next != null;
    }

    public String toString() {
        return data + "->" + (next == null ? "null" : next.toString());
    }

}
