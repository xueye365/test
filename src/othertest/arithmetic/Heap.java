package src.othertest.arithmetic;

import java.util.Arrays;

/**
 * 堆
 * 堆是一个完全二叉树；
 * 对于每个节点的值都大于等于子树中每个节点值的堆，我们叫作“大顶堆”。
 * 对于每个节点的值都小于等于子树中每个节点值的堆，我们叫作“小顶堆”
 *
 * 从下往上的堆化方法解决插入问题
 * 从上往下的堆化方法解决删除问题
 *
 * 建堆
 * 1.假设，起初堆中只包含一个数据，就是下标为 $1$ 的数据。然后，我们调用前面讲的插入操作
 * 2.直接从第一个非叶子节点开始，依次堆化
 * 排序
 * 删除堆顶元素
 *
 * 对于快速排序来说，数据是顺序访问的。而对于堆排序来说，数据是跳着访问的。这样对 CPU 缓存是不友好的。
 * 对于同样的数据，在排序过程中，堆排序算法的数据交换次数要多于快速排序。
 *
 *
 * 堆应用
 * 1.合并有序小文件
 * 2.高性能定时器:队列首部（也就是小顶堆的堆顶）存储的是最先执行的
 * 3.利用堆求 Top K
 *      针对静态数据:数组中的数据都遍历完之后，堆中的数据就是前 K 大数据了,遍历数组需要 O(n), 一次堆化操作需要 O(logK)
 *      所以最坏情况下，n 个元素都入堆一次，所以时间复杂度就是 O(nlogK)。
 *      针对动态数据:一直都维护一个 K 大小的小顶堆，当有数据被添加到集合中时，我们就拿它与堆顶的元素对比,
 *      无论任何时候需要查询当前的前 K 大数据，我们都可以里立刻返回给他。
 *
 * 4.利用堆求中位数
 *      如果新加入的数据小于等于大顶堆的堆顶元素，我们就将这个新数据插入到大顶堆；如果新加入的数据大于等于小顶堆的堆顶元素，我们就将这个新数据插入到小顶堆
 *
 **********************************
 * 5.10 亿个搜索关键词取top 10
 *      1.将 10 亿条搜索关键词先通过哈希算法分片到 10 个文件中（哈希值同 10 取模）。
 *      2.我们就顺序扫描这 10 亿个搜索关键词。当扫描到某个关键词时，我们去散列表中查询。
 *          如果存在，我们就将对应的次数加一；如果不存在，我们就将它插入到散列表，并记录次数为 1
 *      3.建立一个大小为 10 的小顶堆，遍历散列表，如果出现次数比堆顶搜索关键词的次数多，那就删除堆顶的关键词，将这个出现次数更多的关键词加入到堆中。
 *
 *
 */
public class Heap {

    private int[] a; // 数组，从下标 1 开始存储数据
    private int n;  // 堆可以存储的最大数据个数
    private int count; // 堆中已经存储的数据个数

    public Heap(int capicity) {
        a = new int[capicity + 1];
        n = capicity;
        count = 0;
    }

    /**
     * 增加节点
     * @param data
     */
    public void insert(int data) {
        if (count >= n) return; // 堆满了
        ++count;
        a[count] = data;
        int i = count;
        while (i/2 > 0 && a[i] > a[i/2]) { // 自下往上堆化
            swap(a, i, i/2); // swap() 函数作用：交换下标为 i 和 i/2 的两个元素
            i = i/2;
        }
    }


    /**
     * 删除
     */
    public void removeMax() {
        if (count == 0) return ; // 堆中没有数据
        a[1] = a[count];
        --count;
        heapify(a, count, 1);
    }

    /**
     * 自上向下堆化
     * @param a
     * @param n
     * @param i
     */
    private static void heapify(int[] a, int n, int i) {
        while (true) {
            int maxPos = i;
            if (i*2 <= n && a[i] < a[i*2]) maxPos = i*2;
            if (i*2+1 <= n && a[maxPos] < a[i*2+1]) maxPos = i*2+1;
            if (maxPos == i) break;
            swap(a, i, maxPos);
            i = maxPos;
        }
    }

    /**
     * 排序
     * @param a
     * @param n
     */
    public static void sort(int[] a, int n) {
        buildHeap(a, n);
        int k = n;
        while (k > 1) {
            swap(a, 1, k);
            --k;
            heapify(a, k, 1);
        }
    }

    /**
     * 堆化
     * @param a
     * @param n
     */
    private static void buildHeap(int[] a, int n) {
        for (int i = n/2; i >= 1; --i) {
            heapify(a, n, i);
        }
    }

    public static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }


    public static void main(String[] args) {
        Heap heap = new Heap(7);
        heap.insert(2);
        heap.insert(3);
        heap.insert(1);
        heap.insert(4);
        heap.insert(1);
        heap.insert(5);
        heap.insert(6);
        System.out.println(Arrays.toString(heap.a));
//        sort(new int[]{2,3,1,4,1,5,6}, 7);
    }

}
