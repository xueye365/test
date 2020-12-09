package othertest.arithmetic.search;

import java.util.LinkedList;
import java.util.Queue;

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
 * Trie 树是非常耗内存的，用的是一种空间换时间的思路”
 * 我们可以稍微牺牲一点查询的效率，将每个节点中的数组换成其他数据结构，来存储一个节点的子节点指针。用哪种数据结构呢？我们的选择其实有很多，比如有序数组、跳表、散列表、红黑树等。
 * Trie 树的变体有很多，都可以在一定程度上解决内存消耗的问题。比如，缩点优化
 */

public class Trie {
    private TrieNode root1 = new TrieNode('/'); // 存储无意义字符
    private AcNode root2 = new AcNode('/'); // 存储无意义字符

    // 往 Trie 树中插入一个字符串
    public void insert(char[] text) {
        TrieNode p = root1;
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
        TrieNode p = root1;
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




    public class AcNode {
        public char data;
        public AcNode[] children = new AcNode[26]; // 字符集只包含a~z这26个字符
        public boolean isEndingChar = false; // 结尾字符为true
        public int length = -1; // 当isEndingChar=true时，记录模式串长度
        public AcNode fail; // 失败指针
        public AcNode(char data) {
            this.data = data;
        }
    }

    public void match(char[] text) { // text是主串
        int n = text.length;
        AcNode p = root2;
        for (int i = 0; i < n; ++i) {
            int idx = text[i] - 'a';
            while (p.children[idx] == null && p != root2) {
                p = p.fail; // 失败指针发挥作用的地方
            }
            p = p.children[idx];
            if (p == null) p = root2; // 如果没有匹配的，从root开始重新匹配
            AcNode tmp = p;
            while (tmp != root2) { // 打印出可以匹配的模式串
                if (tmp.isEndingChar == true) {
                    int pos = i-tmp.length+1;
                    System.out.println("匹配起始下标" + pos + "; 长度" + tmp.length);
                }
                tmp = tmp.fail;
            }
        }
    }



    public void buildFailurePointer() {
        Queue<AcNode> queue = new LinkedList<>();
        root2.fail = null;
        queue.add(root2);
        while (!queue.isEmpty()) {
            AcNode p = queue.remove();
            for (int i = 0; i < 26; ++i) {
                AcNode pc = p.children[i];
                if (pc == null) continue;
                if (p == root2) {
                    pc.fail = root2;
                } else {
                    AcNode q = p.fail;
                    while (q != null) {
                        AcNode qc = q.children[pc.data - 'a'];
                        //
                        if (qc != null) {
                            pc.fail = qc;
                            break;
                        }
                        q = q.fail;
                    }
                    if (q == null) {
                        // 这里是为了回到root节点重新匹配字符
                        pc.fail = root2;
                    }
                }
                queue.add(pc);
            }
        }
    }



}




