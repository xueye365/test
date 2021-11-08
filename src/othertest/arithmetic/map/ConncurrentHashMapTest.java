package src.othertest.arithmetic.map;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;


public class ConncurrentHashMapTest<K,V> {

    transient volatile ConncurrentHashMapTest.Node<K,V>[] table;
    private transient volatile int sizeCtl;
    private static final int DEFAULT_CAPACITY = 16;
    private static final int MAXIMUM_CAPACITY = 1 << 30;
    static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;
    private static final int DEFAULT_CONCURRENCY_LEVEL = 16;
    private static final float LOAD_FACTOR = 0.75f;
    static final int TREEIFY_THRESHOLD = 8;
    static final int UNTREEIFY_THRESHOLD = 6;
    static final int MIN_TREEIFY_CAPACITY = 64;
    private static final int MIN_TRANSFER_STRIDE = 16;
    private static int RESIZE_STAMP_BITS = 16;
    private static final int MAX_RESIZERS = (1 << (32 - RESIZE_STAMP_BITS)) - 1;
    private static final int RESIZE_STAMP_SHIFT = 32 - RESIZE_STAMP_BITS;
    static final int MOVED     = -1; // hash for forwarding nodes
    static final int TREEBIN   = -2; // hash for roots of trees
    static final int RESERVED  = -3; // hash for transient reservations
    static final int HASH_BITS = 0x7fffffff; // usable bits of normal node hash
    // 计数器单元表格，当非空的时候，size是2的幂
    private transient volatile ConncurrentHashMapTest.CounterCell[] counterCells;
    private transient volatile int cellsBusy;
    // 计数，当没有线程竞争的时候使用，通过cas更新，volatile保证了内存可见行
    private transient volatile long baseCount;
    private transient volatile ConncurrentHashMapTest.Node<K,V>[] nextTable;
    private transient volatile int transferIndex;
    static final int NCPU = Runtime.getRuntime().availableProcessors();



    private final ConncurrentHashMapTest.Node<K,V>[] initTable() {
        ConncurrentHashMapTest.Node<K,V>[] tab; int sc;
        while ((tab = table) == null || tab.length == 0) {
            if ((sc = sizeCtl) < 0)
                Thread.yield(); // lost initialization race; just spin
            else if (U.compareAndSwapInt(this, SIZECTL, sc, -1)) {
                try {
                    if ((tab = table) == null || tab.length == 0) {
                        int n = (sc > 0) ? sc : DEFAULT_CAPACITY;
                        @SuppressWarnings("unchecked")
                        ConncurrentHashMapTest.Node<K,V>[] nt = (ConncurrentHashMapTest.Node<K,V>[])new ConncurrentHashMapTest.Node<?,?>[n];
                        table = tab = nt;
                        sc = n - (n >>> 2);
                    }
                } finally {
                    sizeCtl = sc;
                }
                break;
            }
        }
        return tab;
    }

    public V put(K key, V value) {
        // false 代表允许对旧健值对进行更新
        // true 代表只有存在key一样的健值对的时候才能插入元素
        return putVal(key, value, false);
    }

    /** Implementation for put and putIfAbsent */
    final V putVal(K key, V value, boolean onlyIfAbsent) {
        // key value都不能为空
        if (key == null || value == null) throw new NullPointerException();
        //
        int hash = spread(key.hashCode());
        int binCount = 0;
        for (ConncurrentHashMapTest.Node<K,V>[] tab = table;;) {
            ConncurrentHashMapTest.Node<K,V> f; int n, i, fh;
            if (tab == null || (n = tab.length) == 0)
                // 懒初始化
                tab = initTable();
            // n 为哈希槽个数，
            // 如果hash槽没有数据则使用cas方式放入数据
            else if ((f = tabAt(tab, i = (n - 1) & hash)) == null) {
                if (casTabAt(tab, i, null,
                        new ConncurrentHashMapTest.Node<K,V>(hash, key, value, null)))
                    break;                   // no lock when adding to empty bin
            }
            // fh是f节点的hash值，如果正在扩容中，则参与到扩容当中去
            else if ((fh = f.hash) == MOVED)
                tab = helpTransfer(tab, f);
            else {
                // 真正进行插入操作
                V oldVal = null;
                // f是分配的hash槽，对它进行加锁
                synchronized (f) {
                    // 双重检测，看f是否有变化
                    if (tabAt(tab, i) == f) {
                        // f节点的hash值大于0，则是链表或者是一个节点
                        if (fh >= 0) {
                            binCount = 1;
                            for (ConncurrentHashMapTest.Node<K,V> e = f;; ++binCount) {
                                K ek;
                                // 链表key对比相等
                                if (e.hash == hash &&
                                        ((ek = e.key) == key ||
                                                (ek != null && key.equals(ek)))) {
                                    oldVal = e.val;
                                    if (!onlyIfAbsent)
                                        e.val = value;
                                    break;
                                }
                                ConncurrentHashMapTest.Node<K,V> pred = e;
                                if ((e = e.next) == null) {
                                    pred.next = new ConncurrentHashMapTest.Node<K,V>(hash, key,
                                            value, null);
                                    break;
                                }
                            }
                        }
                        // 如果fh的hash值不大于0，则表示是一个树
                        else if (f instanceof ConncurrentHashMapTest.TreeBin) {
                            ConncurrentHashMapTest.Node<K,V> p;
                            // 哈希桶中有多少健值对，永远等于2
                            binCount = 2;
                            // 如果putTreeVal返回的不为空则说明有相同数据，替换，如果为空则是插入，病保持平衡
                            if ((p = ((ConncurrentHashMapTest.TreeBin<K,V>)f).putTreeVal(hash, key, value)) != null) {
                                oldVal = p.val;
                                if (!onlyIfAbsent)
                                    p.val = value;
                            }
                        }
                    }
                }
                // 当前哈希桶中有多少健值对
                if (binCount != 0) {
                    // 查看链表是否有必要转换成红黑树
                    if (binCount >= TREEIFY_THRESHOLD)
                        treeifyBin(tab, i);
                    if (oldVal != null)
                        // 如果是更新则直接返回老数据， 否则执行count加一操作
                        return oldVal;
                    break;
                }
            }
        }
        // 计数加一，binCount用于检查是否需要扩容
        addCount(1L, binCount);
        return null;
    }

    /**
     * 树化，
     * @param tab
     * @param index
     */
    private final void treeifyBin(ConncurrentHashMapTest.Node<K,V>[] tab, int index) {
        ConncurrentHashMapTest.Node<K,V> b; int n, sc;
        if (tab != null) {
            // 如果数组长度小于64则尝试扩容
            if ((n = tab.length) < MIN_TREEIFY_CAPACITY)
                // 左移一位，扩容两倍
                tryPresize(n << 1);
            else if ((b = tabAt(tab, index)) != null && b.hash >= 0) {
                // 数组加锁
                synchronized (b) {
                    // 再次校验，如果有变化则取消操锁
                    if (tabAt(tab, index) == b) {
                        // hd：表示head， tl：表示tail，链表的头尾引用
                        ConncurrentHashMapTest.TreeNode<K,V> hd = null, tl = null;
                        for (ConncurrentHashMapTest.Node<K,V> e = b; e != null; e = e.next) {
                            ConncurrentHashMapTest.TreeNode<K,V> p =
                                    new ConncurrentHashMapTest.TreeNode<K,V>(e.hash, e.key, e.val,
                                            null, null);
                            if ((p.prev = tl) == null)
                                // 如果tl为空，则说明p是第一个转换好的节点，将p设置为头节点
                                hd = p;
                            else
                                // 否则将p设置为尾节点之后
                                tl.next = p;
                            tl = p;
                        }
                        setTabAt(tab, index, new ConncurrentHashMapTest.TreeBin<K,V>(hd));
                    }
                }
            }
        }
    }

    /**
     * Tries to presize table to accommodate the given number of elements.
     *
     * @param size number of elements (doesn't need to be perfectly accurate)
     */
    private final void tryPresize(int size) {
        int c = (size >= (MAXIMUM_CAPACITY >>> 1)) ? MAXIMUM_CAPACITY :
                tableSizeFor(size + (size >>> 1) + 1);
        int sc;
        while ((sc = sizeCtl) >= 0) {
            ConncurrentHashMapTest.Node<K,V>[] tab = table; int n;
            if (tab == null || (n = tab.length) == 0) {
                n = (sc > c) ? sc : c;
                if (U.compareAndSwapInt(this, SIZECTL, sc, -1)) {
                    try {
                        if (table == tab) {
                            @SuppressWarnings("unchecked")
                            ConncurrentHashMapTest.Node<K,V>[] nt = (ConncurrentHashMapTest.Node<K,V>[])new ConncurrentHashMapTest.Node<?,?>[n];
                            table = nt;
                            sc = n - (n >>> 2);
                        }
                    } finally {
                        sizeCtl = sc;
                    }
                }
            }
            else if (c <= sc || n >= MAXIMUM_CAPACITY)
                break;
            else if (tab == table) {
                int rs = resizeStamp(n);
                if (sc < 0) {
                    ConncurrentHashMapTest.Node<K,V>[] nt;
                    if ((sc >>> RESIZE_STAMP_SHIFT) != rs || sc == rs + 1 ||
                            sc == rs + MAX_RESIZERS || (nt = nextTable) == null ||
                            transferIndex <= 0)
                        break;
                    if (U.compareAndSwapInt(this, SIZECTL, sc, sc + 1))
                        transfer(tab, nt);
                }
                else if (U.compareAndSwapInt(this, SIZECTL, sc,
                        (rs << RESIZE_STAMP_SHIFT) + 2))
                    transfer(tab, null);
            }
        }
    }

    /**
     * Returns a power of two table size for the given desired capacity.
     * See Hackers Delight, sec 3.2
     */
    private static final int tableSizeFor(int c) {
        int n = c - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }



    static final int spread(int h) {
        // HASH_BITS = 0111 1111 1111 1111 1111 1111 1111 1111
        // 高位右移16位， 为了扰动
        // hashMap是没有这个东西的，只有ConcurrentHashMap有这个值·
        return (h ^ (h >>> 16)) & HASH_BITS;
    }


    static final <K,V> ConncurrentHashMapTest.Node<K,V> tabAt(ConncurrentHashMapTest.Node<K,V>[] tab, int i) {
        return (ConncurrentHashMapTest.Node<K,V>)U.getObjectVolatile(tab, ((long)i << ASHIFT) + ABASE);
    }

    static final <K,V> boolean casTabAt(ConncurrentHashMapTest.Node<K,V>[] tab, int i,
                                        ConncurrentHashMapTest.Node<K,V> c, ConncurrentHashMapTest.Node<K,V> v) {
        return U.compareAndSwapObject(tab, ((long)i << ASHIFT) + ABASE, c, v);
    }


    private final void addCount(long x, int check) {
        // baseCount计数，但是它记录的不是全部
        // 构造另一个hash表，以支持并发的计数
        CounterCell[] as; long b, s;
        // 如果counterCells不为空，则说明已经初始化
        if ((as = counterCells) != null ||
                // 或者通过cas设置了计数基数失败
                // 当插入第一个健值对的时候，baseCount是0，此处用于更新baseCount值为x
                !U.compareAndSwapLong(this, BASECOUNT, b = baseCount, s = b + x)) {
            CounterCell a; long v; int m;
            boolean uncontended = true;
            // 到这里是baseCount设置失败，需要使用counterCells进行并发计数
            if (as == null
                    // counterCells不是空，但长度为0
                    || (m = as.length - 1) < 0
                    // 当线程Probe获取的hash值分配到的counterCells为空
                    // 在多线程环境下获取的hash值不重复
                    || (a = as[ThreadLocalRandomTest.getProbe() & m]) == null
                    // 使用cas累加失败
                    || !(uncontended = U.compareAndSwapLong(a, CELLVALUE, v = a.value, v + x))) {
                // 执行并发计数
                // 如果counterCells为false，则存在线程竞争，重新分配hash桶
                fullAddCount(x, uncontended);
                return;
            }
            // 不需要检查扩容
            if (check <= 1)
                return;
            // 如果需要检查是否需要扩容，在检查之前，先调用sumCount方法计算哈希桶中元素的个数，0。75n
            s = sumCount();
        }
        // 需要扩容
        if (check >= 0) {
            Node<K,V>[] tab, nt; int n, sc;
            // sizeCtl存储的是下次扩容要求的hash表键值对的数量
            while (s >= (long)(sc = sizeCtl)
                    && (tab = table) != null
                    // table的长度必须小于最大长度
                    && (n = tab.length) < MAXIMUM_CAPACITY) {
                // 每次扩容有唯一的值
                // 扩容戳
                // 扩容不是当时立即就扩容，会有延迟，它不是同步的，要判断当前是第几次扩容，当前线程是否能参与进来
                // 只有相同的批次才能加入到扩容中
                int rs = resizeStamp(n);
                // 已经小于0表示正在扩容
                if (sc < 0) {
                    // 右移16位看扩容戳能不能对上，对不上则不参与扩容
                    if ((sc >>> RESIZE_STAMP_SHIFT) != rs
                            // 当前扩容是当前线程要参与扩容的上一次的
                            || sc == rs + 1
                            // MAX_RESIZERS = 0000 0000 0000 0000 1111 1111 1111 1111
                            // 参与线程最大数，如果线程达到了最大数，则不参与扩容
                            || sc == rs + MAX_RESIZERS
                            // 表示不在扩容，扩容已经结束
                            || (nt = nextTable) == null
                            || transferIndex <= 0)
                        break;
                    // 只要不是第一个参与扩容的，就将SIZECTL加一，sc的低16位
                    if (U.compareAndSwapInt(this, SIZECTL, sc, sc + 1))
                        transfer(tab, nt);
                }
                // SIZECTL高16位记录扩展戳，低16位记录参与扩容过程的线程个数以及扩容是否结束的状态
                // 如果SIZECTL不小于0，表示还没开始扩容，此时是第一个参加扩容的线程
                // 将SIZECTL更新为rs左移16位再加2
                // 0000 0000 0000 0000 1000 0000 0001 1011 = 32795
                // 左移6位
                // 1000 0000 0001 1011 0000 0000 0000 0000 = -2145714176 + 2
                // 加2
                // 1000 0000 0001 1011 0000 0000 0000 0010 = -2145714176 + 2
                // 为什么第一个参与扩容的加2，其余的加1？

                else if (U.compareAndSwapInt(this, SIZECTL, sc,
                        // 左移16位
                        (rs << RESIZE_STAMP_SHIFT) + 2))
                    transfer(tab, null);
                s = sumCount();
            }
        }
    }

    // 为了并发记录hash表中元素个数，设计了另一个hash表，使用线程的probe这个hash值对hash表元素进行记录和更新
    private final void fullAddCount(long x, boolean wasUncontended) {
        int h;
        // 等于0时证明probe还没有初始化
        if ((h = ThreadLocalRandomTest.getProbe()) == 0) {
            // 强制初始化
            ThreadLocalRandomTest.localInit();      // force initialization
            h = ThreadLocalRandomTest.getProbe();
            // true表示不存在线程竞争，false表示存在线程竞争，所谓线程竞争指的是 当前线程cas失败
            wasUncontended = true;
        }
        boolean collide = false;                // True if last slot nonempty
        // 自旋
        for (;;) {
            ConncurrentHashMapTest.CounterCell[] as; ConncurrentHashMapTest.CounterCell a; int n; long v;
            // 已经初始化好了，此时只要使用h给当前线程分配数组元素，设置计数即可
            if ((as = counterCells) != null && (n = as.length) > 0) {
                // hash值对counterCells长度逻辑与操作，计算出对应的数组元素
                // 如果改元素是null，则尝试添加新CounterCell，保存计数
                if ((a = as[(n - 1) & h]) == null) {
                    // cellsBusy保证了内存可见行
                    // 如果是0，表示没有线程在创建CounterCell
                    if (cellsBusy == 0) {            // Try to attach new Cell
                        // 首先创建好需要设置的CounterCell对象，乐观创建
                        ConncurrentHashMapTest.CounterCell r = new ConncurrentHashMapTest.CounterCell(x); // Optimistic create
                        // 如果是0，则用cas设置成为1，进行赋值，说明正在创建了
                        if (cellsBusy == 0 &&
                                U.compareAndSwapInt(this, CELLSBUSY, 0, 1)) {
                            boolean created = false;
                            try {               // Recheck under lock
                                ConncurrentHashMapTest.CounterCell[] rs; int m, j;
                                // 乐观锁情况下，重新检查
                                if ((rs = counterCells) != null &&
                                        (m = rs.length) > 0 &&
                                        rs[j = (m - 1) & h] == null) {
                                    // 设置counterCells为新创建的CounterCell
                                    rs[j] = r;
                                    created = true;
                                }
                            } finally {
                                cellsBusy = 0;
                            }
                            if (created)
                                // 如果创建成功则自旋
                                break;
                            continue;           // Slot is now non-empty
                        }
                    }
                    collide = false;
                }
                // cas失败，hash槽有线程竞争，重新获取hash值，重新分配
                else if (!wasUncontended)       // CAS already known to fail
                    wasUncontended = true;      // Continue after rehash
                // 如果分配的hash槽不是null，通过cas累加计数成功，方法退出
                else if (U.compareAndSwapLong(a, CELLVALUE, v = a.value, v + x))
                    break;
                else if (counterCells != as || n >= NCPU)
                    collide = false;            // At max size or stale
                else if (!collide)
                    collide = true;
                // 处理counterCells的扩容，如果没有线程在创建counterCell，并且成功将counterCell设置成为1
                else if (cellsBusy == 0 &&
                        U.compareAndSwapInt(this, CELLSBUSY, 0, 1)) {
                    try {
                        if (counterCells == as) {// Expand table unless stale
                            // counterCell扩容
                            ConncurrentHashMapTest.CounterCell[] rs = new ConncurrentHashMapTest.CounterCell[n << 1];
                            // 将原来的值拷贝过去
                            for (int i = 0; i < n; ++i)
                                rs[i] = as[i];
                            counterCells = rs;
                        }
                    } finally {
                        cellsBusy = 0;
                    }
                    collide = false;
                    continue;                   // Retry with expanded table
                }
                // 每次自旋都更新线程的probe的值，即取当前线程的下一个hash值
                h = ThreadLocalRandomTest.advanceProbe(h);
            }
            else if (cellsBusy == 0 && counterCells == as &&
                    U.compareAndSwapInt(this, CELLSBUSY, 0, 1)) {
                boolean init = false;
                try {                           // Initialize table
                    if (counterCells == as) {
                        ConncurrentHashMapTest.CounterCell[] rs = new ConncurrentHashMapTest.CounterCell[2];
                        rs[h & 1] = new ConncurrentHashMapTest.CounterCell(x);
                        counterCells = rs;
                        init = true;
                    }
                } finally {
                    cellsBusy = 0;
                }
                // 如果初始化成功，则跳出循环，因为在创建过程中已经保存了当前线程需要保存的值
                if (init)
                    break;
            }
            // 如果操作baseCount更新成功也跳出自旋
            else if (U.compareAndSwapLong(this, BASECOUNT, v = baseCount, v + x))
                break;                          // Fall back on using base
        }
    }

    static final int resizeStamp(int n) {
        // Integer.numberOfLeadingZeros(n)返回二进制表示1左边有多少个0
        // 假设n = 16
        // 0000 0000 0000 0000 0000 0000 0001 0000
        // 则返回27， 每次扩容这个数字减1
        // 0000 0000 0000 0000 0000 0000 0001 1011  16->32
        // 0000 0000 0000 0000 0000 0000 0001 1010  32->64
        // 逻辑或
        // 0000 0000 0000 0000 1000 0000 0000 0000
        // 结果是
        // 0000 0000 0000 0000 1000 0000 0001 1011  正好16位
        return Integer.numberOfLeadingZeros(n) | (1 << (RESIZE_STAMP_BITS - 1));
    }

    // 计算总数，baseCount + counterCells中数据的个数
    final long sumCount() {
        ConncurrentHashMapTest.CounterCell[] as = counterCells; ConncurrentHashMapTest.CounterCell a;
        long sum = baseCount;
        if (as != null) {
            for (int i = 0; i < as.length; ++i) {
                if ((a = as[i]) != null)
                    sum += a.value;
            }
        }
        return sum;
    }

    /**
     * Helps transfer if a resize is in progress.
     */
    final ConncurrentHashMapTest.Node<K,V>[] helpTransfer(ConncurrentHashMapTest.Node<K,V>[] tab, ConncurrentHashMapTest.Node<K,V> f) {
        ConncurrentHashMapTest.Node<K,V>[] nextTab; int sc;
        // 要想进行扩容，当前hash表不能为空，，该节点正在扩容，新hash表不是空
        if (tab != null && (f instanceof ConncurrentHashMapTest.ForwardingNode) &&
                (nextTab = ((ConncurrentHashMapTest.ForwardingNode<K,V>)f).nextTable) != null) {
            // 获取扩容戳
            int rs = resizeStamp(tab.length);
            while (nextTab == nextTable && table == tab &&
                    (sc = sizeCtl) < 0) {
                // 是不是同一次扩容
                if ((sc >>> RESIZE_STAMP_SHIFT) != rs || sc == rs + 1 ||
                        sc == rs + MAX_RESIZERS || transferIndex <= 0)
                    break;
                // SIZECTL加一，因为不是第一个参与扩容的线程
                if (U.compareAndSwapInt(this, SIZECTL, sc, sc + 1)) {
                    transfer(tab, nextTab);
                    break;
                }
            }
            // 如果加入了扩容则返回新table
            return nextTab;
        }
        // 如果没有参与扩容，则返回旧值
        return table;
    }


    /**
     * Moves and/or copies the nodes in each bin to new table. See
     * above for explanation.
     */
    private final void transfer(ConncurrentHashMapTest.Node<K,V>[] tab, ConncurrentHashMapTest.Node<K,V>[] nextTab) {
        int n = tab.length, stride;
        if ((stride = (NCPU > 1) ? (n >>> 3) / NCPU : n) < MIN_TRANSFER_STRIDE)
            stride = MIN_TRANSFER_STRIDE; // subdivide range
        if (nextTab == null) {            // initiating
            try {
                @SuppressWarnings("unchecked")
                ConncurrentHashMapTest.Node<K,V>[] nt = (ConncurrentHashMapTest.Node<K,V>[])new ConncurrentHashMapTest.Node<?,?>[n << 1];
                nextTab = nt;
            } catch (Throwable ex) {      // try to cope with OOME
                sizeCtl = Integer.MAX_VALUE;
                return;
            }
            nextTable = nextTab;
            transferIndex = n;
        }
        int nextn = nextTab.length;
        ConncurrentHashMapTest.ForwardingNode<K,V> fwd = new ConncurrentHashMapTest.ForwardingNode<K,V>(nextTab);
        boolean advance = true;
        boolean finishing = false; // to ensure sweep before committing nextTab
        for (int i = 0, bound = 0;;) {
            ConncurrentHashMapTest.Node<K,V> f; int fh;
            while (advance) {
                int nextIndex, nextBound;
                if (--i >= bound || finishing)
                    advance = false;
                else if ((nextIndex = transferIndex) <= 0) {
                    i = -1;
                    advance = false;
                }
                else if (U.compareAndSwapInt
                        (this, TRANSFERINDEX, nextIndex,
                                nextBound = (nextIndex > stride ?
                                        nextIndex - stride : 0))) {
                    bound = nextBound;
                    i = nextIndex - 1;
                    advance = false;
                }
            }
            if (i < 0 || i >= n || i + n >= nextn) {
                int sc;
                if (finishing) {
                    nextTable = null;
                    table = nextTab;
                    sizeCtl = (n << 1) - (n >>> 1);
                    return;
                }
                if (U.compareAndSwapInt(this, SIZECTL, sc = sizeCtl, sc - 1)) {
                    if ((sc - 2) != resizeStamp(n) << RESIZE_STAMP_SHIFT)
                        return;
                    finishing = advance = true;
                    i = n; // recheck before commit
                }
            }
            else if ((f = tabAt(tab, i)) == null)
                advance = casTabAt(tab, i, null, fwd);
            else if ((fh = f.hash) == MOVED)
                advance = true; // already processed
            else {
                synchronized (f) {
                    if (tabAt(tab, i) == f) {
                        ConncurrentHashMapTest.Node<K,V> ln, hn;
                        if (fh >= 0) {
                            int runBit = fh & n;
                            ConncurrentHashMapTest.Node<K,V> lastRun = f;
                            for (ConncurrentHashMapTest.Node<K,V> p = f.next; p != null; p = p.next) {
                                int b = p.hash & n;
                                if (b != runBit) {
                                    runBit = b;
                                    lastRun = p;
                                }
                            }
                            if (runBit == 0) {
                                ln = lastRun;
                                hn = null;
                            }
                            else {
                                hn = lastRun;
                                ln = null;
                            }
                            for (ConncurrentHashMapTest.Node<K,V> p = f; p != lastRun; p = p.next) {
                                int ph = p.hash; K pk = p.key; V pv = p.val;
                                if ((ph & n) == 0)
                                    ln = new ConncurrentHashMapTest.Node<K,V>(ph, pk, pv, ln);
                                else
                                    hn = new ConncurrentHashMapTest.Node<K,V>(ph, pk, pv, hn);
                            }
                            setTabAt(nextTab, i, ln);
                            setTabAt(nextTab, i + n, hn);
                            setTabAt(tab, i, fwd);
                            advance = true;
                        }
                        else if (f instanceof ConncurrentHashMapTest.TreeBin) {
                            ConncurrentHashMapTest.TreeBin<K,V> t = (ConncurrentHashMapTest.TreeBin<K,V>)f;
                            ConncurrentHashMapTest.TreeNode<K,V> lo = null, loTail = null;
                            ConncurrentHashMapTest.TreeNode<K,V> hi = null, hiTail = null;
                            int lc = 0, hc = 0;
                            for (ConncurrentHashMapTest.Node<K,V> e = t.first; e != null; e = e.next) {
                                int h = e.hash;
                                ConncurrentHashMapTest.TreeNode<K,V> p = new ConncurrentHashMapTest.TreeNode<K,V>
                                        (h, e.key, e.val, null, null);
                                if ((h & n) == 0) {
                                    if ((p.prev = loTail) == null)
                                        lo = p;
                                    else
                                        loTail.next = p;
                                    loTail = p;
                                    ++lc;
                                }
                                else {
                                    if ((p.prev = hiTail) == null)
                                        hi = p;
                                    else
                                        hiTail.next = p;
                                    hiTail = p;
                                    ++hc;
                                }
                            }
                            ln = (lc <= UNTREEIFY_THRESHOLD) ? untreeify(lo) :
                                    (hc != 0) ? new ConncurrentHashMapTest.TreeBin<K,V>(lo) : t;
                            hn = (hc <= UNTREEIFY_THRESHOLD) ? untreeify(hi) :
                                    (lc != 0) ? new ConncurrentHashMapTest.TreeBin<K,V>(hi) : t;
                            setTabAt(nextTab, i, ln);
                            setTabAt(nextTab, i + n, hn);
                            setTabAt(tab, i, fwd);
                            advance = true;
                        }
                    }
                }
            }
        }
    }

    static final <K,V> void setTabAt(ConncurrentHashMapTest.Node<K,V>[] tab, int i, ConncurrentHashMapTest.Node<K,V> v) {
        U.putObjectVolatile(tab, ((long)i << ASHIFT) + ABASE, v);
    }

    static <K,V> ConncurrentHashMapTest.Node<K,V> untreeify(ConncurrentHashMapTest.Node<K,V> b) {
        ConncurrentHashMapTest.Node<K,V> hd = null, tl = null;
        for (ConncurrentHashMapTest.Node<K,V> q = b; q != null; q = q.next) {
            ConncurrentHashMapTest.Node<K,V> p = new ConncurrentHashMapTest.Node<K,V>(q.hash, q.key, q.val, null);
            if (tl == null)
                hd = p;
            else
                tl.next = p;
            tl = p;
        }
        return hd;
    }

    static class Node<K,V> implements Map.Entry<K,V> {
        final int hash;
        final K key;
        volatile V val;
        volatile ConncurrentHashMapTest.Node<K,V> next;

        Node(int hash, K key, V val, ConncurrentHashMapTest.Node<K,V> next) {
            this.hash = hash;
            this.key = key;
            this.val = val;
            this.next = next;
        }

        public final K getKey()       { return key; }
        public final V getValue()     { return val; }
        public final int hashCode()   { return key.hashCode() ^ val.hashCode(); }
        public final String toString(){ return key + "=" + val; }
        public final V setValue(V value) {
            throw new UnsupportedOperationException();
        }

        public final boolean equals(Object o) {
            Object k, v, u; Map.Entry<?,?> e;
            return ((o instanceof Map.Entry) &&
                    (k = (e = (Map.Entry<?,?>)o).getKey()) != null &&
                    (v = e.getValue()) != null &&
                    (k == key || k.equals(key)) &&
                    (v == (u = val) || v.equals(u)));
        }

        /**
         * Virtualized support for map.get(); overridden in subclasses.
         */
        ConncurrentHashMapTest.Node<K,V> find(int h, Object k) {
            ConncurrentHashMapTest.Node<K,V> e = this;
            if (k != null) {
                do {
                    K ek;
                    if (e.hash == h &&
                            ((ek = e.key) == k || (ek != null && k.equals(ek))))
                        return e;
                } while ((e = e.next) != null);
            }
            return null;
        }
    }

    static final class TreeBin<K,V> extends ConncurrentHashMapTest.Node<K,V> {
        ConncurrentHashMapTest.TreeNode<K, V> root;
        volatile ConncurrentHashMapTest.TreeNode<K, V> first;
        volatile Thread waiter;
        volatile int lockState;
        // values for lockState
        static final int WRITER = 1; // set while holding write lock
        static final int WAITER = 2; // set when waiting for write lock
        static final int READER = 4; // increment value for setting read lock
        private static final long LOCKSTATE;

        static {
            try {
                U = sun.misc.Unsafe.getUnsafe();
                Class<?> k = ConncurrentHashMapTest.TreeBin.class;
                LOCKSTATE = U.objectFieldOffset
                        (k.getDeclaredField("lockState"));
            } catch (Exception e) {
                throw new Error(e);
            }
        }

        /**
         * Creates bin with initial set of nodes headed by b.
         */
        TreeBin(ConncurrentHashMapTest.TreeNode<K, V> b) {
            super(TREEBIN, null, null, null);
            this.first = b;
            ConncurrentHashMapTest.TreeNode<K, V> r = null;
            for (ConncurrentHashMapTest.TreeNode<K, V> x = b, next; x != null; x = next) {
                next = (ConncurrentHashMapTest.TreeNode<K, V>) x.next;
                x.left = x.right = null;
                if (r == null) {
                    x.parent = null;
                    x.red = false;
                    r = x;
                } else {
                    K k = x.key;
                    int h = x.hash;
                    Class<?> kc = null;
                    for (ConncurrentHashMapTest.TreeNode<K, V> p = r; ; ) {
                        int dir, ph;
                        K pk = p.key;
                        if ((ph = p.hash) > h)
                            dir = -1;
                        else if (ph < h)
                            dir = 1;
                        else if ((kc == null &&
                                (kc = comparableClassFor(k)) == null) ||
                                (dir = compareComparables(kc, k, pk)) == 0)
                            dir = tieBreakOrder(k, pk);
                        ConncurrentHashMapTest.TreeNode<K, V> xp = p;
                        if ((p = (dir <= 0) ? p.left : p.right) == null) {
                            x.parent = xp;
                            if (dir <= 0)
                                xp.left = x;
                            else
                                xp.right = x;
                            r = balanceInsertion(r, x);
                            break;
                        }
                    }
                }
            }
            this.root = r;
            assert checkInvariants(root);
        }

        /**
         * Finds or adds a node.
         * @return null if added
         */
        final ConncurrentHashMapTest.TreeNode<K,V> putTreeVal(int h, K k, V v) {
            Class<?> kc = null;
            boolean searched = false;
            for (ConncurrentHashMapTest.TreeNode<K,V> p = root;;) {
                int dir, ph; K pk;
                if (p == null) {
                    first = root = new ConncurrentHashMapTest.TreeNode<K,V>(h, k, v, null, null);
                    break;
                }
                else if ((ph = p.hash) > h)
                    dir = -1;
                else if (ph < h)
                    dir = 1;
                else if ((pk = p.key) == k || (pk != null && k.equals(pk)))
                    return p;
                else if ((kc == null &&
                        (kc = comparableClassFor(k)) == null) ||
                        (dir = compareComparables(kc, k, pk)) == 0) {
                    if (!searched) {
                        ConncurrentHashMapTest.TreeNode<K,V> q, ch;
                        searched = true;
                        if (((ch = p.left) != null &&
                                (q = ch.findTreeNode(h, k, kc)) != null) ||
                                ((ch = p.right) != null &&
                                        (q = ch.findTreeNode(h, k, kc)) != null))
                            return q;
                    }
                    dir = tieBreakOrder(k, pk);
                }

                ConncurrentHashMapTest.TreeNode<K,V> xp = p;
                if ((p = (dir <= 0) ? p.left : p.right) == null) {
                    ConncurrentHashMapTest.TreeNode<K,V> x, f = first;
                    first = x = new ConncurrentHashMapTest.TreeNode<K,V>(h, k, v, f, xp);
                    if (f != null)
                        f.prev = x;
                    if (dir <= 0)
                        xp.left = x;
                    else
                        xp.right = x;
                    if (!xp.red)
                        x.red = true;
                    else {
                        lockRoot();
                        try {
                            root = balanceInsertion(root, x);
                        } finally {
                            unlockRoot();
                        }
                    }
                    break;
                }
            }
            assert checkInvariants(root);
            return null;
        }
        /**
         * Acquires write lock for tree restructuring.
         */
        private final void lockRoot() {
            if (!U.compareAndSwapInt(this, LOCKSTATE, 0, WRITER));
//                contendedLock(); // offload to separate method
        }

        /**
         * Releases write lock for tree restructuring.
         */
        private final void unlockRoot() {
            lockState = 0;
        }


    }


    /**
     * Recursive invariant check
     */
    static <K,V> boolean checkInvariants(ConncurrentHashMapTest.TreeNode<K,V> t) {
        ConncurrentHashMapTest.TreeNode<K,V> tp = t.parent, tl = t.left, tr = t.right,
                tb = t.prev, tn = (ConncurrentHashMapTest.TreeNode<K,V>)t.next;
        if (tb != null && tb.next != t)
            return false;
        if (tn != null && tn.prev != t)
            return false;
        if (tp != null && t != tp.left && t != tp.right)
            return false;
        if (tl != null && (tl.parent != t || tl.hash > t.hash))
            return false;
        if (tr != null && (tr.parent != t || tr.hash < t.hash))
            return false;
        if (t.red && tl != null && tl.red && tr != null && tr.red)
            return false;
        if (tl != null && !checkInvariants(tl))
            return false;
        if (tr != null && !checkInvariants(tr))
            return false;
        return true;
    }

    static Class<?> comparableClassFor(Object x) {
        if (x instanceof Comparable) {
            Class<?> c; Type[] ts, as; Type t; ParameterizedType p;
            if ((c = x.getClass()) == String.class) // bypass checks
                return c;
            if ((ts = c.getGenericInterfaces()) != null) {
                for (int i = 0; i < ts.length; ++i) {
                    if (((t = ts[i]) instanceof ParameterizedType) &&
                            ((p = (ParameterizedType)t).getRawType() ==
                                    Comparable.class) &&
                            (as = p.getActualTypeArguments()) != null &&
                            as.length == 1 && as[0] == c) // type arg is c
                        return c;
                }
            }
        }
        return null;
    }

    static int compareComparables(Class<?> kc, Object k, Object x) {
        return (x == null || x.getClass() != kc ? 0 :
                ((Comparable)k).compareTo(x));
    }

    static int tieBreakOrder(Object a, Object b) {
        int d;
        if (a == null || b == null ||
                (d = a.getClass().getName().
                        compareTo(b.getClass().getName())) == 0)
            d = (System.identityHashCode(a) <= System.identityHashCode(b) ?
                    -1 : 1);
        return d;
    }

    static <K,V> ConncurrentHashMapTest.TreeNode<K,V> balanceInsertion(ConncurrentHashMapTest.TreeNode<K,V> root,
                                                                  ConncurrentHashMapTest.TreeNode<K,V> x) {
        x.red = true;
        for (ConncurrentHashMapTest.TreeNode<K,V> xp, xpp, xppl, xppr;;) {
            if ((xp = x.parent) == null) {
                x.red = false;
                return x;
            }
            else if (!xp.red || (xpp = xp.parent) == null)
                return root;
            if (xp == (xppl = xpp.left)) {
                if ((xppr = xpp.right) != null && xppr.red) {
                    xppr.red = false;
                    xp.red = false;
                    xpp.red = true;
                    x = xpp;
                }
                else {
                    if (x == xp.right) {
                        root = rotateLeft(root, x = xp);
                        xpp = (xp = x.parent) == null ? null : xp.parent;
                    }
                    if (xp != null) {
                        xp.red = false;
                        if (xpp != null) {
                            xpp.red = true;
                            root = rotateRight(root, xpp);
                        }
                    }
                }
            }
            else {
                if (xppl != null && xppl.red) {
                    xppl.red = false;
                    xp.red = false;
                    xpp.red = true;
                    x = xpp;
                }
                else {
                    if (x == xp.left) {
                        root = rotateRight(root, x = xp);
                        xpp = (xp = x.parent) == null ? null : xp.parent;
                    }
                    if (xp != null) {
                        xp.red = false;
                        if (xpp != null) {
                            xpp.red = true;
                            root = rotateLeft(root, xpp);
                        }
                    }
                }
            }
        }
    }

    static <K,V> ConncurrentHashMapTest.TreeNode<K,V> rotateLeft(ConncurrentHashMapTest.TreeNode<K,V> root,
                                                                 ConncurrentHashMapTest.TreeNode<K,V> p) {
        ConncurrentHashMapTest.TreeNode<K,V> r, pp, rl;
        if (p != null && (r = p.right) != null) {
            if ((rl = p.right = r.left) != null)
                rl.parent = p;
            if ((pp = r.parent = p.parent) == null)
                (root = r).red = false;
            else if (pp.left == p)
                pp.left = r;
            else
                pp.right = r;
            r.left = p;
            p.parent = r;
        }
        return root;
    }

    static <K,V> ConncurrentHashMapTest.TreeNode<K,V> rotateRight(ConncurrentHashMapTest.TreeNode<K,V> root,
                                                                  ConncurrentHashMapTest.TreeNode<K,V> p) {
        ConncurrentHashMapTest.TreeNode<K,V> l, pp, lr;
        if (p != null && (l = p.left) != null) {
            if ((lr = p.left = l.right) != null)
                lr.parent = p;
            if ((pp = l.parent = p.parent) == null)
                (root = l).red = false;
            else if (pp.right == p)
                pp.right = l;
            else
                pp.left = l;
            l.right = p;
            p.parent = l;
        }
        return root;
    }



    static final class TreeNode<K,V> extends ConncurrentHashMapTest.Node<K,V> {
        ConncurrentHashMapTest.TreeNode<K, V> parent;  // red-black tree links
        ConncurrentHashMapTest.TreeNode<K, V> left;
        ConncurrentHashMapTest.TreeNode<K, V> right;
        ConncurrentHashMapTest.TreeNode<K, V> prev;    // needed to unlink next upon deletion
        boolean red;

        TreeNode(int hash, K key, V val, ConncurrentHashMapTest.Node<K, V> next,
                 ConncurrentHashMapTest.TreeNode<K, V> parent) {
            super(hash, key, val, next);
            this.parent = parent;
        }

        final ConncurrentHashMapTest.TreeNode<K,V> findTreeNode(int h, Object k, Class<?> kc) {
            if (k != null) {
                TreeNode<K,V> p = this;
                do  {
                    int ph, dir; K pk; ConncurrentHashMapTest.TreeNode<K,V> q;
                    ConncurrentHashMapTest.TreeNode<K,V> pl = p.left, pr = p.right;
                    if ((ph = p.hash) > h)
                        p = pl;
                    else if (ph < h)
                        p = pr;
                    else if ((pk = p.key) == k || (pk != null && k.equals(pk)))
                        return p;
                    else if (pl == null)
                        p = pr;
                    else if (pr == null)
                        p = pl;
                    else if ((kc != null ||
                            (kc = comparableClassFor(k)) != null) &&
                            (dir = compareComparables(kc, k, pk)) != 0)
                        p = (dir < 0) ? pl : pr;
                    else if ((q = pr.findTreeNode(h, k, kc)) != null)
                        return q;
                    else
                        p = pl;
                } while (p != null);
            }
            return null;
        }

    }

    /**
     * treeNode用于平衡树，而不是链表
     * treeBin是TreeNode集合的跟节点
     *
     *
     *
     *
     * @param <K>
     * @param <V>
     */
    static final class ForwardingNode<K,V> extends ConncurrentHashMapTest.Node<K,V> {
        final ConncurrentHashMapTest.Node<K,V>[] nextTable;
        ForwardingNode(ConncurrentHashMapTest.Node<K,V>[] tab) {
            super(MOVED, null, null, null);
            this.nextTable = tab;
        }

        ConncurrentHashMapTest.Node<K,V> find(int h, Object k) {
            // loop to avoid arbitrarily deep recursion on forwarding nodes
            outer: for (ConncurrentHashMapTest.Node<K,V>[] tab = nextTable;;) {
                ConncurrentHashMapTest.Node<K,V> e; int n;
                if (k == null || tab == null || (n = tab.length) == 0 ||
                        (e = tabAt(tab, (n - 1) & h)) == null)
                    return null;
                for (;;) {
                    int eh; K ek;
                    if ((eh = e.hash) == h &&
                            ((ek = e.key) == k || (ek != null && k.equals(ek))))
                        return e;
                    if (eh < 0) {
                        if (e instanceof ConncurrentHashMapTest.ForwardingNode) {
                            tab = ((ConncurrentHashMapTest.ForwardingNode<K,V>)e).nextTable;
                            continue outer;
                        }
                        else
                            return e.find(h, k);
                    }
                    if ((e = e.next) == null)
                        return null;
                }
            }
        }
    }



    // Unsafe mechanics
    private static sun.misc.Unsafe U;
    // 参与扩容的线程个数
    private static final long SIZECTL;
    private static final long TRANSFERINDEX;
    private static final long BASECOUNT;
    private static final long CELLSBUSY;
    private static final long CELLVALUE;
    private static final long ABASE;
    private static final int ASHIFT;

    static {
        try {
            U = sun.misc.Unsafe.getUnsafe();
            Class<?> k = ConncurrentHashMapTest.class;
            SIZECTL = U.objectFieldOffset
                    (k.getDeclaredField("sizeCtl"));
            TRANSFERINDEX = U.objectFieldOffset
                    (k.getDeclaredField("transferIndex"));
            BASECOUNT = U.objectFieldOffset
                    (k.getDeclaredField("baseCount"));
            CELLSBUSY = U.objectFieldOffset
                    (k.getDeclaredField("cellsBusy"));
            Class<?> ck = ConncurrentHashMapTest.CounterCell.class;
            CELLVALUE = U.objectFieldOffset
                    (ck.getDeclaredField("value"));
            Class<?> ak = ConncurrentHashMapTest.Node[].class;
            ABASE = U.arrayBaseOffset(ak);
            int scale = U.arrayIndexScale(ak);
            if ((scale & (scale - 1)) != 0)
                throw new Error("data type scale not a power of two");
            ASHIFT = 31 - Integer.numberOfLeadingZeros(scale);
        } catch (Exception e) {
            throw new Error(e);
        }
    }
    @sun.misc.Contended static final class CounterCell {
        volatile long value;
        CounterCell(long x) { value = x; }
    }




}
