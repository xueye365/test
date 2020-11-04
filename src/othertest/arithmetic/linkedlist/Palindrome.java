package othertest.arithmetic.linkedlist;

/**
 * 判断是否为回文
 */
public class Palindrome {

    private static Node head = null;


    public static void main(String[]args){

        Palindrome link = new Palindrome();
        //int data[] = {1};
        //int data[] = {1,2};
        //int data[] = {1,2,3,1};
        //int data[] = {1,2,5};
        //int data[] = {1,2,2,1};
        // int data[] = {1,2,5,2,1};
        int data[] = {1,2,5,3,1};
        for(int i =0; i < data.length; i++){
            link.insertTail(data[i]);
        }
        head = link.inverseLinkList_head(head);
        link.printAll();
//        System.out.println("打印原始:");
//        link.printAll();
//        if (link.palindrome()){
//            System.out.println("回文");
//        }else{
//            System.out.println("不是回文");
//        }
    }

    //顺序插入
    //链表尾部插入
    public void insertTail(int value){

        Node newNode = new Node(value, null);
        //空链表，可以插入新节点作为head，也可以不操作
        if (head == null){
            head = newNode;

        }else{
            Node q = head;
            while(q.next != null){
                q = q.next;
            }
            newNode.next = q.next;
            q.next = newNode;
        }
    }


    /**
     * 判断是否是回文
     * @return
     */
    public boolean palindrome(){
        if (head == null){
            return false;
        }else{
            System.out.println("开始执行找到中间节点");
            Node p = head;
            Node q = head;
            if (p.next == null){
                System.out.println("只有一个元素");
                return true;
            }
            while( q.next != null && q.next.next != null){
                p = p.next;
                q = q.next.next;

            }

            System.out.println("中间节点" + p.data);
            System.out.println("开始执行奇数节点的回文判断");
            Node leftLink = null;
            Node rightLink = null;
            if(q.next == null){
                //　p 一定为整个链表的中点，且节点数目为奇数
                rightLink = p.next;
                leftLink = inverseLinkList(p).next;
                System.out.println("左边第一个节点"+leftLink.data);
                System.out.println("右边第一个节点"+rightLink.data);

            }else{
                //p q　均为中点
                rightLink = p.next;
                leftLink = inverseLinkList(p);
            }
            return TFResult(leftLink, rightLink);

        }
    }

    //无头结点的链表翻转
    public Node inverseLinkList(Node p){

        Node pre = null;
        Node r = head;
        System.out.println("z---" + r.data);
        Node next= null;
        while(r !=p){
            next = r.next;
            r.next = pre;
            pre = r;
            r = next;
        }

        r.next = pre;
        //　返回左半部分的中点之前的那个节点
        //　从此处开始同步像两边比较
        return r;

    }


    //带结点的链表翻转
    public Node inverseLinkList_head(Node node){
        /*
        带头结点的链表翻转等价于
        从第二个元素开始重新头插法建立链表
        */
        Node result = new Node(0, null);
        Node p = node;
        while (p.next != null) {
            Node temp = p.next;
            p.next = result.next;
            result.next = p;
            p = temp;
        }
        return result.next;
    }



    //判断true or false
    public boolean TFResult(Node left, Node right) {
        Node l = left;
        Node r = right;

        boolean flag = true;
        System.out.println("left_:" + l.data);
        System.out.println("right_:" + r.data);
        while (l != null && r != null) {
            if (l.data == r.data) {
                l = l.next;
                r = r.next;
                continue;
            } else {
                flag = false;
                break;
            }

        }
        return flag;
    }

    public void printAll() {
        Node p = head;
        while (p != null) {
            System.out.print(p.data + " ");
            p = p.next;
        }
        System.out.println();
    }

    static class Node {
        private int data;
        private Node next;

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
    }

}
