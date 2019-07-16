package src.othertest.arithmetic;

import java.util.ArrayDeque;

/**
 * 树
 * 数组顺序存储的方式比较适合完全二叉树，其他类型的二叉树用数组存储会比较浪费存储空间
 */
public class TreeTest {

    public static void main(String[] args) {
        Tree a = new Tree("A");
        Tree b = new Tree("B");
        Tree c = new Tree("C");
        Tree d = new Tree("D");
        Tree e = new Tree("E");
        Tree f = new Tree("F");
        Tree g = new Tree("G");
        a.left = b;
        a.right = c;
        b.left = d;
        b.right = e;
        c.left = f;
        c.right = g;
        order(a);
    }

    // todo 按层排序
    public static void order(Tree node) {
        if (node == null) {
            return;
        }
        ArrayDeque<Tree> queue = new ArrayDeque<>();
        queue.add(node);
        while (!queue.isEmpty()) {
            // 一定要放判定之前，否则会出大事
            node = queue.poll();
            System.out.print(node.data + " ");
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
    }




    /**
     * 二叉查找树（也叫二叉排序树）
     *
     * 当有相同数据的时候
     * 第一种方法 ： 二叉查找树中每一个节点不仅会存储一个数据，通过链表和支持动态扩容的数组等数据结构，把值相同的数据都存储在同一个节点上。
     * 第二种方法 ： 每个节点仍然只存储一个数据。在查找插入位置的过程中，如果碰到一个节点的值，与要插入数据的值相同，我们就将这个要插入的数据放到这个节点的右子树， 把这个新插入的数据当作大于这个节点的值来处理。
     *              查找数据的时候，遇到值相同的节点，我们并不停止查找操作，而是继续在右子树中查找，直到遇到叶子节点，才停止。这样就可以把键值等于要查找值的所有节点都找出来。
     *
     * 删除节点：如果要删除的节点有两个子节点，这就比较复杂了。我们需要找到这个节点的右子树中的最小节点，把它替换到要删除的节点上
     *
     * 散列时间复杂度是O(1)，为什么还要使用二叉树？
     * 1.散列表的数据是无序的，平衡二叉树是有序的
     * 2.散列扩容耗时较多，当遇到散列冲突时，性能不稳定，平衡二叉查找树的性能非常稳定，时间复杂度稳定在 O(logn)
     * 3.尽管散列表的查找等操作的时间复杂度是常量级的， 但因为哈希冲突的存在，这个常量不一定比 logn 小
     * 4.散列表的构造比二叉查找树要复杂，需要考虑的东西很多。比如散列函数的设计、冲突解决办法、扩容、缩容等。平衡二叉查找树只需要考虑平衡性这一个问题，而且这个问题的解决方案比较成熟、固定
     * 5.为了避免过多的散列冲突，散列表装载因子不能太大，特别是基于开放寻址法解决冲突的散列表，不然会浪费一定的存储空间。平衡二叉树不会浪费空间
     *
     *
     */
    // todo
    private Node tree;

    public Node find(int data) {
        Node p = tree;
        while (p != null) {
            if (data < p.data) p = p.left;
            else if (data > p.data) p = p.right;
            else return p;
        }
        return null;
    }

    public void insert(int data) {
        if (tree == null) {
            tree = new Node(data);
            return;
        }

        Node p = tree;
        while (p != null) {
            if (data > p.data) {
                if (p.right == null) {
                    p.right = new Node(data);
                    return;
                }
                p = p.right;
            } else { // data < p.data
                if (p.left == null) {
                    p.left = new Node(data);
                    return;
                }
                p = p.left;
            }
        }
    }

    public void delete(int data) {
        Node p = tree; // p指向要删除的节点，初始化指向根节点
        Node pp = null; // pp记录的是p的父节点
        while (p != null && p.data != data) {
            pp = p;
            if (data > p.data) p = p.right;
            else p = p.left;
        }
        if (p == null) return; // 没有找到

        // 要删除的节点有两个子节点
        if (p.left != null && p.right != null) { // 查找右子树中最小节点
            Node minP = p.right;
            Node minPP = p; // minPP表示minP的父节点
            while (minP.left != null) {
                minPP = minP;
                minP = minP.left;
            }
            p.data = minP.data; // 将minP的数据替换到p中
            p = minP; // 下面就变成了删除minP了
            pp = minPP;
        }

        // 删除节点是叶子节点或者仅有一个子节点
        Node child; // p的子节点
        if (p.left != null) child = p.left;
        else if (p.right != null) child = p.right;
        else child = null;

        if (pp == null) tree = child; // 删除的是根节点
        else if (pp.left == p) pp.left = child;
        else pp.right = child;
    }

    public Node findMin() {
        if (tree == null) return null;
        Node p = tree;
        while (p.left != null) {
            p = p.left;
        }
        return p;
    }

    public Node findMax() {
        if (tree == null) return null;
        Node p = tree;
        while (p.right != null) {
            p = p.right;
        }
        return p;
    }


    /**
     * 平衡二叉查找树
     * 平衡二叉树的严格定义是这样的：二叉树中任意一个节点的左右子树的高度相差不能大于 1
     *
     * AVL树
     * 查询效率特别高，但插入、删除，为了维持这种高度的平衡，就要付出更多的代价
     *
     *
     * 红黑树
     *
     * 根节点是黑色的；
     * 每个叶子节点都是黑色的空节点（NIL），也就是说，叶子节点不存储数据；
     * 任何相邻的节点都不能同时为红色，也就是说，红色节点是被黑色节点隔开的；
     * 每个节点，从该节点到达其可达叶子节点的所有路径，都包含相同数目的黑色节点；
     *
     * 有一个红色节点就要至少有一个黑色节点，将它跟其他红色节点隔开。红黑树中包含最多黑色节点的路径不会超过 log2n，所以加入红色节点之后，最长路径不会超过 2log2n，也就是说，红黑树的高度近似 2log2n。
     *
     * 近似平衡，插入、删除、查找操作的时间复杂度都是 O(logn)。
     *
     *
     * 插入的节点必须是红色的。而且，二叉查找树中新插入的节点都是放在叶子节点上
     * 如果插入节点的父节点是黑色的，那我们什么都不用做，它仍然满足红黑树的定义。
     * 如果插入的节点是根节点，那我们直接改变它的颜色，把它变成黑色就可以了。
     * 调整的过程包含两种基础的操作：左右旋转和改变颜色。
     *
     *
     */


    /**
     * 字典树
     * Trie 树的本质，就是利用字符串之间的公共前缀，将重复的前缀合并在一起
     *
     * 构建 Trie 树的过程，需要扫描所有的字符串，时间复杂度是 O(n)
     * 构建好 Trie 树后，在其中查找字符串的时间复杂度是 O(k)，k 表示要查找的字符串的长度
     *
     * 它对要处理的字符串有及其严苛的要求
     * 1.字符串中包含的字符集不能太大，会造成浪费
     * 2.要求字符串的前缀重合比较多，不然会造成浪费
     * 3.没有现成的实现
     * 4.通过指针串起来的数据块是不连续的，而 Trie 树中用到了指针，缓存不友好
     *
     *
     *
     */

    public class Trie {
        private TrieNode root = new TrieNode('/'); // 存储无意义字符

        // 往 Trie 树中插入一个字符串
        public void insert(char[] text) {
            TrieNode p = root;
            for (int i = 0; i < text.length; ++i) {
                int index = text[i] - 'a';
                if (p.children[index] == null) {
                    TrieNode newNode = new TrieNode(text[i]);
                    p.children[index] = newNode;
                }
                p = p.children[index];
            }
            p.isEndingChar = true;
        }

        // 在 Trie 树中查找一个字符串
        public boolean find(char[] pattern) {
            TrieNode p = root;
            for (int i = 0; i < pattern.length; ++i) {
                int index = pattern[i] - 'a';
                if (p.children[index] == null) {
                    return false; // 不存在 pattern
                }
                p = p.children[index];
            }
            if (p.isEndingChar == false) return false; // 不能完全匹配，只是前缀
            else return true; // 找到 pattern
        }

        public class TrieNode {
            public char data;
            public TrieNode[] children = new TrieNode[26];
            public boolean isEndingChar = false;
            public TrieNode(char data) {
                this.data = data;
            }
        }
    }












    public static class Node {
        private int data;
        private Node left;
        private Node right;

        public Node(int data) {
            this.data = data;
        }
    }

    public static class Tree {
        private String data;
        private Tree left;
        private Tree right;

        public Tree(String data) {
            this.data = data;
        }
    }


}

