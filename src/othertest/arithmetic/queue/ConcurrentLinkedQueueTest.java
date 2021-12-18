//package src.othertest.arithmetic.queue;
//
//
//
//public class ConcurrentLinkedQueueTest<E> {
//
//
//    public boolean add(E e) {
//        return offer(e);
//    }
//
//    public boolean offer(E e) {
//        // 如果e为null，则直接抛出NullPointerException异常
//        checkNotNull(e);
//        // 创建入队节点
//        final Node<E> newNode = new Node<E>(e);
//
//        // 循环CAS直到入队成功
//        // 1、根据tail节点定位出尾节点（last node）；2、将新节点置为尾节点的下一个节点；3、casTail更新尾节点
//        for (Node<E> t = tail, p = t;;) {
//            // p用来表示队列的尾节点，初始情况下等于tail节点
//            // q是p的next节点
//            Node<E> q = p.next;
//            // 判断p是不是尾节点，tail节点不一定是尾节点，判断是不是尾节点的依据是该节点的next是不是null
//            // 如果p是尾节点
//            if (q == null) {
//                // p is last node
//                // 设置p节点的下一个节点为新节点，设置成功则casNext返回true；否则返回false，说明有其他线程更新过尾节点
//                if (p.casNext(null, newNode)) {
//                    // Successful CAS is the linearization point
//                    // for e to become an element of this queue,
//                    // and for newNode to become "live".
//                    // 如果p != t，则将入队节点设置成tail节点，更新失败了也没关系，因为失败了表示有其他线程成功更新了tail节点
//                    if (p != t) // hop two nodes at a time
//                        casTail(t, newNode);  // Failure is OK.
//                    return true;
//                }
//                // Lost CAS race to another thread; re-read next
//            }
//            // 多线程操作时候，由于poll时候会把旧的head变为自引用，然后将head的next设置为新的head
//            // 所以这里需要重新找新的head，因为新的head后面的节点才是激活的节点
//            else if (p == q)
//                // We have fallen off list.  If tail is unchanged, it
//                // will also be off-list, in which case we need to
//                // jump to head, from which all live nodes are always
//                // reachable.  Else the new tail is a better bet.
//                p = (t != (t = tail)) ? t : head;
//                // 寻找尾节点
//            else
//                // Check for tail updates after two hops.
//                p = (p != t && t != (t = tail)) ? t : q;
//        }
//    }
//
//    private static void checkNotNull(Object v) {
//        if (v == null)
//            throw new NullPointerException();
//    }
//
//    public boolean offer(E e) {
//        checkNotNull(e);
//        final Node<E> newNode = new Node<E>(e);
//
//        for (;;) {
//            Node<E> t = tail;
//
//            if (t.casNext(null ,newNode) && casTail(t, newNode)) {
//                return true;
//            }
//        }
//    }
//
//    public int size() {
//        int count = 0;
//        // first()获取第一个具有非空元素的节点，若不存在，返回null
//        // succ(p)方法获取p的后继节点，若p == p的后继节点，则返回head
//        for (Node<E> p = first(); p != null; p = succ(p))
//            if (p.item != null)
//                // Collection.size() spec says to max out
//                // 最大返回Integer.MAX_VALUE
//                if (++count == Integer.MAX_VALUE)
//                    break;
//        return count;
//    }
//
//    public boolean remove(Object o) {
//        // 删除的元素不能为null
//        if (o != null) {
//            Node<E> next, pred = null;
//
//            for (Node<E> p = first(); p != null; pred = p, p = next) {
//                boolean removed = false;
//                E item = p.item;
//
//                // 节点元素不为null
//                if (item != null) {
//                    // 若不匹配，则获取next节点继续匹配
//                    if (!o.equals(item)) {
//                        next = succ(p);
//                        continue;
//                    }
//
//                    // 若匹配，则通过CAS操作将对应节点元素置为null
//                    removed = p.casItem(item, null);
//                }
//
//                // 获取删除节点的后继节点
//                next = succ(p);
//                // 将被删除的节点移除队列
//                if (pred != null && next != null) // unlink
//                    pred.casNext(p, next);
//                if (removed)
//                    return true;
//            }
//        }
//        return false;
//    }
//
//    public E poll() {
//        restartFromHead:
//        for (;;) {
//            // p节点表示首节点，即需要出队的节点
//            for (Node<E> h = head, p = h, q;;) {
//                E item = p.item;
//
//                // 如果p节点的元素不为null，则通过CAS来设置p节点引用的元素为null，如果成功则返回p节点的元素
//                if (item != null && p.casItem(item, null)) {
//                    // Successful CAS is the linearization point
//                    // for item to be removed from this queue.
//                    // 如果p != h，则更新head
//                    if (p != h) // hop two nodes at a time
//                        updateHead(h, ((q = p.next) != null) ? q : p);
//                    return item;
//                }
//                // 如果头节点的元素为空或头节点发生了变化，这说明头节点已经被另外一个线程修改了。
//                // 那么获取p节点的下一个节点，如果p节点的下一节点为null，则表明队列已经空了
//                else if ((q = p.next) == null) {
//                    // 更新头结点
//                    updateHead(h, p);
//                    return null;
//                }
//                // p == q，则使用新的head重新开始
//                else if (p == q)
//                    continue restartFromHead;
//                    // 如果下一个元素不为空，则将头节点的下一个节点设置成头节点
//                else
//                    p = q;
//            }
//        }
//    }
//
//    // 默认构造方法，head节点存储的元素为空，tail节点等于head节点
//    public ConcurrentLinkedQueue() {
//        head = tail = new Node<E>(null);
//    }
//
//    // 根据其他集合来创建队列
//    public ConcurrentLinkedQueue(Collection<? extends E> c) {
//        Node<E> h = null, t = null;
//        // 遍历节点
//        for (E e : c) {
//            // 若节点为null，则直接抛出NullPointerException异常
//            checkNotNull(e);
//            Node<E> newNode = new Node<E>(e);
//            if (h == null)
//                h = t = newNode;
//            else {
//                t.lazySetNext(newNode);
//                t = newNode;
//            }
//        }
//        if (h == null)
//            h = t = new Node<E>(null);
//        head = h;
//        tail = t;
//    }
//
//
//    public boolean contains(Object o) {
//        if (o == null) return false;
//
//        // 遍历队列
//        for (Node<E> p = first(); p != null; p = succ(p)) {
//            E item = p.item;
//            // 若找到匹配节点，则返回true
//            if (item != null && o.equals(item))
//                return true;
//        }
//        return false;
//    }
//
//
//
//
//
//
//
//    private static class Node<E> {
//        volatile E item;
//        volatile Node<E> next;
//
//        Node(E item) {
//            UNSAFE.putObject(this, itemOffset, item);
//        }
//
//        boolean casItem(E cmp, E val) {
//            return UNSAFE.compareAndSwapObject(this, itemOffset, cmp, val);
//        }
//
//        void lazySetNext(Node<E> val) {
//            UNSAFE.putOrderedObject(this, nextOffset, val);
//        }
//
//        boolean casNext(Node<E> cmp, Node<E> val) {
//            return UNSAFE.compareAndSwapObject(this, nextOffset, cmp, val);
//        }
//
//        private static final sun.misc.Unsafe UNSAFE;
//        private static final long itemOffset;
//        private static final long nextOffset;
//
//        static {
//            try {
//                UNSAFE = sun.misc.Unsafe.getUnsafe();
//                Class<?> k = Node.class;
//                itemOffset = UNSAFE.objectFieldOffset
//                        (k.getDeclaredField("item"));
//                nextOffset = UNSAFE.objectFieldOffset
//                        (k.getDeclaredField("next"));
//            } catch (Exception e) {
//                throw new Error(e);
//            }
//        }
//    }
//
//}
