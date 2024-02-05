package src.videotest.other;


import javafx.concurrent.Task;
import src.othertest.arithmetic.tree.TreeTest;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;

public class test<T> {

//    public static void main(String[] args) {
//        ListNode node1 = new ListNode(9);
//        ListNode node2 = new ListNode(1, node1);
//        ListNode node3 = new ListNode(0, node2);
//        System.out.println(node3);

//        ListNode result = revert(node3);
//        System.out.println(result);


//        ListNode node4 = new ListNode(6);
//        ListNode node5 = new ListNode(5, node4);
//        ListNode node6 = new ListNode(4, node5);
//        System.out.println(node6);
//        System.out.println(result);
//        int[] a = new int[]{5666,3444,2333,1111,4000,2777,35,6999999};
//        int[] a = new int[]{5,1,1,2,0,0};
//        sort3(a);
//        sout(a);
//
//    }
    public static void sort(int[] list, int start, int end){
        if (start < end) {
            int i = quickSort(list, start, end);
            sort(list, start, i - 1);
            sort(list, i + 1, end);
        }
    }

    public static int quickSort(int[] list, int start, int end) {
        int a = list[start];
        int i = start, j = end;
        while (i < j) {
            while (list[i] <= a && i < end){
                i++;
            }
            while (list[j] >= a && j > start){
                j--;
            }
            if (i < j) {
                int tmp = list[i];
                list[i] = list[j];
                list[j] = tmp;
            }
        }
        int tmp = list[j];
        list[j] = a;
        list[start] = tmp;
        return j;
    }



    public static void sort2(int[] list){

        for (int i = 1; i < list.length; i++) {
            int value = list[i];
            int j = i - 1;
            while (j >= 0) {
                if (list[j] > value) {
                    list[j + 1] = list[j];
                    j--;
                } else {
                   break;
                }
            }
            list[j + 1] = value;
        }
    }



    public static void sort3(int[] list){
        for (int i = 0; i < list.length; i++) {
            int minIndex = i;
            for (int i1 = i + 1; i1 < list.length; i1++) {
                if (list[minIndex] > list[i1]) {
                    minIndex = i1;
                }
            }
            int tmp = list[i];
            list[i] = list[minIndex];
            list[minIndex] = tmp;
        }
    }


//    public static void main(String[] args) {
//        System.out.println(formate(-9999999999L));
//    }
//
//
//
//
//    public static int find(int[] arr1, int[] arr2) {
//        Map<Integer, Integer> map = new HashMap<>();
//        for (int i = 0; i < arr2.length; i++) {
//            map.put(arr2[i], i);
//        }
//        int index1 = 0, index2 = 0;
//        while (index1 < arr1.length) {
//            if (arr1[index1] == arr2[index2]) {
//                index2++;
//                if (index2 == arr2.length) {
//                    return index1 - arr2.length + 1;
//                }
//                index1++;
//            } else if (index1 + 1 < arr1.length) {
//                Integer lastIndex = map.get(arr1[index1 + 1]);
//                if (lastIndex != null) {
//                    index1 = index1 + 1 - lastIndex;
//                } else {
//                    index1 = index1 + 2;
//                }
//                index2 = 0;
//            } else {
//                index1++;
//            }
//        }
//        return -1;
//    }


//    public static void main(String[] args) {
//        ListNode node5 = new ListNode(5);
//        ListNode node4 = new ListNode(4, node5);
//        ListNode node3 = new ListNode(3, node4);
//        ListNode node2 = new ListNode(2, node3);
//        ListNode node1 = new ListNode(1,node2);
//        System.out.println(node1);
//        System.out.println(test(node1, 2));
//    }
//    public static ListNode test(ListNode node, int n) {
//        ListNode temp = new ListNode(0, node);
//        ListNode quick = temp;
//        while (quick != null && n != 0) {
//            n--;
//            quick = quick.getNext();
//        }
//        while (quick != null && quick.hasNext()) {
//            temp = temp.getNext();
//            quick = quick.getNext();
//        }
//        temp.next = temp.next.next;
//        return node;
//    }


//    public static void main(String[] args) {
//        System.out.println(findLengthOfLCIS(new int[]{1, 3, 5, 7}));
//    }
//    public static int findLengthOfLCIS(int[] nums) {
//        int maxCount = 0;
//        int count = 1;
//        for (int i = 1; i < nums.length; i++) {
//            if (nums[i] > nums[i - 1]) {
//                count++;
//            } else {
//                maxCount = Math.max(count, maxCount);
//                count = 0;
//            }
//            maxCount = Math.max(count, maxCount);
//        }
//        return maxCount;
//
//    }

//    public static void main(String[] args) {
//        TreeNode node4 = new TreeNode(1);
//        TreeNode node5 = new TreeNode(3);
//        TreeNode node2 = new TreeNode(2);
//        node2.left = node4;
//        node2.right = node5;
////        Node node3 = new Node(5);
////        Node node1 = new Node(4, node2, node3);
//        System.out.println(levelOrder(node2));
//    }
//
//
//
//    public static List<List<Integer>> levelOrder(TreeNode root) {
//        ArrayDeque<TreeNode> queue = new ArrayDeque();
//        queue.add(root);
//        List<List<Integer>> result = new ArrayList();
//        while (queue.size() > 0) {
//            List<Integer> tmp = new ArrayList();
//
//            for (int i = queue.size() - 1; i >= 0; i--) {
//                TreeNode node = queue.poll();
//                tmp.add(node.val);
//                if (node.left != null) {
//                    queue.add(node.left);
//                }
//                if (node.right != null) {
//                    queue.add(node.right);
//                }
//            }
//            result.add(tmp);
//        }
//        return result;
//    }

    static class TreeNode {
         int val;
         TreeNode left;
         TreeNode right;
         TreeNode(int x) { val = x; }
    }

//
//    /**
//     * sunday算法
//     * @param args
//     */
//    public static void main(String[] args) {
//        System.out.println(find(new int[]{1,2,1,2,1,2}, new int[]{1,2,1,2,1}));
//    }
//
//    public static int find(int[] arr1, int[] arr2) {
//        Map<Integer, Integer> map = new HashMap<>();
//        for (int i = 0; i < arr2.length; i++) {
//            map.put(arr2[i], i);
//        }
//        int index1 = 0, index2 = 0;
//        while (index1 < arr1.length) {
//            if (arr1[index1] == arr2[index2]) {
//                index2++;
//                if (index2 == arr2.length) {
//                    return index1 - arr2.length + 1;
//                }
//                index1++;
//            } else {
//                index1 = index1 + arr2.length - index2;
//                if (index1 < arr1.length) {
//                    Integer lastIndex = map.get(arr1[index1]);
//                    if (lastIndex != null) {
//                        index1 -= lastIndex;
//                    } else {
//                        index1++;
//                    }
//                    index2 = 0;
//                } else {
//                    index1++;
//                }
//            }
//        }
//        return -1;
//    }
//


//    public static void main(String[] args) {
//        System.out.println(minWindow("XDOYEZODEYXNZ","XYZ"));
//    }


//    public static void main(String[] args) {
//        System.out.println(kmp("ababab","abababab"));
//    }

//    public static int kmp (String S, String T) {
//        char[] sc = S.toCharArray();
//        char[] tc = T.toCharArray();
//        Map<Character, Integer> map = new HashMap();
//
//        for (int i = 0; i < sc.length; i++) {
//            map.put(sc[i], i);
//        }
//
//        int sci = 0;
//        int tci = 0;
//        int count = 0;
//
//
//        while (tci < tc.length) {
//            for (; sci < sc.length; sci++) {
//                if (sc[sci] == tc[tci]) {
//                    tci++;
//                } else {
//                    break;
//                }
//                if (sci == sc.length - 1) {
//                    count++;
//                }
//            }
//            if (tci + 1 < tc.length) {
//                tci = tci + sc.length - sci;
//                for (; tci < tc.length; tci++) {
//                    if (map.containsKey(tc[tci])) {
//                        sci = map.get(tc[tci]);
//                        tci = tci - sci;
//                        sci = 0;
//                        break;
//                    }
//                }
//            }
//        }
//        return count;
//    }


//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        int n = in.nextInt();
//        int[] a = new int[n];
//        a[0] = in.nextInt();
//        for (int i = 0; i < n - 1; i++) {
//            int value = in.nextInt();
//            int j = i;
//            a[j + 1] = value;
//
//            boolean continueFlag = false;
//            for (; j >= 0; j--) {
//                if (a[j] == 0) {
//                    a[j] = a[j + 1];
//                    a[j + 1] = 0;
//                    continueFlag = true;
//                } else if (a[j] == value) {
//                    a[j + 1] = 0;
//                    continueFlag = true;
//                    break;
//                } else if (a[j] < value) {
//                    continueFlag = true;
//                    break;
//                } else if (a[j] > value) {
//                    a[j + 1] = a[j];
//                    continueFlag = false;
//                }
//            }
//            if (!continueFlag) {
//                a[j] = value;
//            }
//        }
//        for (int i = 0; i < a.length; i++) {
//            if (a[i] == 0) {
//                break;
//            }
//            System.out.println(a[i]);
//        }
//    }
//
//
//
//
//
//
//
//
//
//













//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        // 注意 hasNext 和 hasNextLine 的区别
//        while (in.hasNextLong()) { // 注意 while 处理多个 case
//            Long a = in.nextLong();
//            int tmp = 127;
//            while (a > tmp) {
//                long value = a & tmp;
//                a = a >> 7;
//                if (a != tmp) {
//                    value = value | 128;
//                }
//                System.out.print(Long.toHexString(value).toUpperCase());
//            }
//            System.out.print(Long.toHexString(a >> 4 & 15 ).toUpperCase());
//            System.out.print(Long.toHexString(a & 15).toUpperCase());
//        }
//
//    }


//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        String s = in.nextLine();
//        String[] ss = s.split(" ");
//        int n = ss.length;
//        int[] a = new int[n];
//        for (int i = 0; i < n; i++) {
//            a[i] = Integer.valueOf(ss[i]);
//        }
//        int min = Integer.MAX_VALUE;
//        int value1 = 0;
//        int value2 = 0;
//        for (int i = 0; i < n; i++) {
//            for (int j = 1; j < n; j++) {
//                int sum = Math.abs(a[i] + a[j]);
//                if (min > sum) {
//                    value1 = a[i];
//                    value2 = a[j];
//                    min = sum;
//                }
//            }
//        }
//        System.out.print(value1 + " " + value2 + " " + min);
//    }






//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        int n = in.nextInt();
//        int[] values = new int[n];
//        for (int i = 0; i < n; i++) {
//            values[i] = in.nextInt();
//        }
//
////        int sum = 0;
////        for (int i = 0; i < n; i++) {
////            sum ^= values[i];
////        }
////        if (sum != 0) {
////            System.out.println(-1);
////            return;
////        }
//        ArrayList<Integer> list1 = new ArrayList<>();
//        ArrayList<Integer> list2 = new ArrayList<>();
//        sub(values, 0, list1, list2);
//
//    }
//
//    public static void sub(int[] a, int i, List<Integer> result1, List<Integer> result2) {
//        if (i < a.length - 1) {
//            result1.add(a[i]);
//
//            sub(a, i+1, result1, result2);
//            result1.remove(i);
//            System.out.println(result1);
//            result2.add(a[i]);
//            sub(a, i+1, result1, result2);
//            result2.remove(i);
//            System.out.println(result2);
//        }
//
//    }


//    public static void main(String[] args) {
//        Node node1 = new Node(1, null);
//        Node node2 = new Node(2, node1);
//        Node node3 = new Node(3, node2);
//        Node node4 = new Node(4, node3);
//        node1.next = node2;
////        System.out.println(node4);
//        System.out.println(checkCircle(node4));
//    }
//
//
//    public static boolean checkCircle(Node linkList) {
//        boolean result = false;
//        if (linkList == null) {
//            return result;
//        }
//        Node quick = linkList;
//        Node slow = linkList;
//        while (quick != null && quick.next != null && slow != null) {
//            if (quick.equals(slow)) {
//                result = true;
//                break;
//            }
//            quick = quick.next.next;
//            slow = slow.next;
//        }
//        return result;
//    }


//    static class Node {
//        int val;
//        Node next;
//
//        public Node(int val, Node next) {
//            this.val = val;
//            this.next = next;
//        }
//
//        @Override
//        public String toString() {
//            return "Node{" +
//                    "val=" + val +
//                    ", next=" + next +
//                    '}';
//        }
//    }
//













//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        double d = in.nextDouble();
//        String s = in.next();
//        double result ;
//        if (d <= 1) {
//            result = 20;
//        } else {
//            result = 20 + (int) Math.ceil(d - 1);
//        }
//        if ("y".equals(s)) {
//            result += 5;
//        }
//        System.out.println(result);
//    }






//    public static void main(String[] args) {
//        int[] values = {6, 7, 2, 3, 0, 1, 9, 5};
////        int[] values = {9,8,7,6,5,4,3,2};
//        sort1(values, 0, 7);
//        for (int i = 0; i < values.length; i++) {
//            System.out.println(values[i]);
//        }
//    }
//
//
//
//    public static void sort1(int[] values, int start, int end) {
//        if (start < end) {
//            int index = sort2(values, start, end);
//            sort1(values, start, index - 1);
//            sort1(values, index + 1, end);
//        }
//
//    }
//    // int[] values = {6, 7, 2, 3, 0, 1, 9, 5};
//    public static int sort2(int[] values, int start, int end) {
//        int tmp = values[start];
//        int i = start;
//        int j = end;
//        while (i < j) {
//            while (values[j] >= tmp && i < j){
//                j--;
//            }
//            values[i] = values[j];
//            while (values[i] <= tmp && i < j){
//                i++;
//            }
//            values[j] = values[i];
//        }
//        values[i] = tmp;
//        return i;
//    }





//    public static void main(String[] args) {
//        int[] values = nextGreaterElement(new int[]{4, 1, 2}, new int[]{1, 3, 4, 2});
//        for (int i = 0; i < values.length; i++) {
//            System.out.println(values[i]);
//        }
//    }
//
//
//    public static int[] nextGreaterElement(int[] nums1, int[] nums2) {
//        Stack<Integer> stack = new Stack();
//        Map<Integer, Integer> map = new HashMap();
//        for (int i = 0; i < nums2.length; i++) {
//            int value = nums2[i];
//            while (!stack.isEmpty()) {
//                int peek = stack.peek();
//                if (peek < value) {
//                    map.put(stack.pop(), value);
//                } else {
//                    break;
//                }
//            }
//            stack.add(value);
//        }
//        while (!stack.isEmpty()) {
//            map.put(stack.pop(), -1);
//        }
//        for (int i = 0; i < nums1.length; i++){
//            nums1[i] = map.get(nums1[i]);
//        }
//        return nums1;
//    }




//    public static void main(String[] args) {
//        int value = 50020;
//        Map<Integer, String> map  = new HashMap<> ();
//        map.put(0, "");
//        map.put(1, "十");
//        map.put(2, "百");
//        map.put(3, "千");
//        map.put(4, "万");
//        int n = 1;
//        String result = "";
//        if (value == 0) {
//            System.out.print(0);
//        }
//
//        int count = 1;
//        while (0 != value / ((int)Math.pow(10, count))) {
//            count++;
//        }
//
//        while (n <= count) {
//            int i = value % ((int)Math.pow(10, n)) / (int)Math.pow(10, n - 1);
//            if (i == 0) {
//                if (n != 1 && !result.startsWith("零")) {
//                    result = "零" + result;
//                }
//            } else {
//                result = i + map.get(n - 1) + result;
//            }
//
//            n++;
//        }
//        System.out.print(result);
//
//    }

//    private static volatile int i = 0;
//    public static void main(String[] args) {
//
//        Object lock = new Object();
//        Thread thread1 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while (i < 10000) {
//                    synchronized (lock) {
//                        System.out.println(Thread.currentThread().getName() + i++);
//                        try {
//                            lock.notify();
//                            lock.wait();
//                        } catch (InterruptedException e) {
//                            throw new RuntimeException(e);
//                        }
//                    }
//                }
//            }
//        });
//        thread1.start();
//
//        Thread thread2 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while (i < 10000) {
//                    synchronized (lock) {
//                        System.out.println(Thread.currentThread().getName() + i++);
//                        try {
//                            lock.notify();
//                            lock.wait();
//                        } catch (InterruptedException e) {
//                            throw new RuntimeException(e);
//                        }
//                    }
//                }
//            }
//        });
//        thread2.start();
//
//
//
//
//
//    }
//
//



//    public static void main(String[] args) {
//        Node node1 = new Node(1, null);
//        Node node2 = new Node(2, node1);
//        Node node3 = new Node(3, node2);
//        Node node4 = new Node(4, node3);
//        System.out.println(node4);
//        Node revert = revert(node4);
//        System.out.println(revert);
//    }
//
//
//    public static Node revert(Node node) {
//        Node result = new Node(0, null);
//        while (node != null) {
//            Node nNext = node.next;
//            Node rNext = result.next;
//            result.next = node;
//            result.next.next = rNext;
//            node = nNext;
//        }
//        return result.next;
//    }
//
//
//    static class Node {
//        int val;
//        Node next;
//
//        public Node(int val, Node next) {
//            this.val = val;
//            this.next = next;
//        }
//
//        @Override
//        public String toString() {
//            return "Node{" +
//                    "val=" + val +
//                    ", next=" + next +
//                    '}';
//        }
//    }







//    题目：一个数组 array 和一个数 k ，从数组中移除 k 个元素，找出移除后数组中剩余不同数的最少数量
//    输入：arr = [5,7,1,1,7,7,6], k = 2
//    输出：2
//    输入：arr = [5,7,1,1,7,7,6,6], k = 2
//    输出：3


    public static void main(String[] args) {
        int[] arr = new int[]{5,7,1,1,7,7,6};
//        int[] arr = new int[]{5,7,1,1,7,7,6,6};
        int k = 2;


        Map<Integer, Value> map = new HashMap();
        for (int i = 0; i < arr.length; i++) {
            if (map.containsKey(arr[i])) {
                Value count = map.get(arr[i]);
                count.setN(count.getN() + 1);
            } else {
                map.put(arr[i], new Value(arr[i], 1));
            }
        }

        Collection<Value> values = map.values();
        ArrayList<Value> values1 = new ArrayList<>(values);
        values1.sort(new Comparator<Value>() {
            @Override
            public int compare(Value o1, Value o2) {
                return o1.getN() - o2.getN();
            }
        });

        System.out.println(values1);

        Iterator<Value> iterator = values1.iterator();
        while (iterator.hasNext() && k != 0) {
            Value next = iterator.next();
            if (next.getN() == 1) {
                iterator.remove();
            } else {
                next.setN(next.getN() - 1);
            }
            k--;
        }
        System.out.println(values1.size());


        new TreeSet<Value>(Comparator.comparingInt(item -> -item.getI()));

        

    }


    static class Value {
        int i;
        int n;

        public int getI() {
            return i;
        }

        public void setI(int i) {
            this.i = i;
        }

        public int getN() {
            return n;
        }

        public void setN(int n) {
            this.n = n;
        }

        public Value(int i, int n) {
            this.i = i;
            this.n = n;
        }

        @Override
        public String toString() {
            return "Value{" +
                    "i=" + i +
                    ", n=" + n +
                    '}';
        }
    }










}

