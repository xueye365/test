package src.othertest.designmodle.behavior.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 迭代器模式
 *
 * 对于类似数组和链表这样的数据结构，遍历方式比较简单，直接使用 for 循环来遍历就足够了。
 * 但是，对于复杂的数据结构（比如树、图）来说，有各种复杂的遍历方式。
 * 比如，树有前中后序、按层遍历，图有深度优先、广度优先遍历等等。
 * 如果由客户端代码来实现这些遍历算法，势必增加开发成本，而且容易写错。如果将这部分遍历的逻辑写到容器类中，也会导致容器类代码的复杂性。
 *
 *
 *
 * modCount，记录集合被修改的次数，集合每调用一次增加或删除元素的函数，就会给 modCount 加 1。
 * 当通过调用集合上的 iterator() 函数来创建迭代器的时候，我们把 modCount 值传递给迭代器的 expectedModCount 成员变量，
 * 之后每次调用迭代器上的 hasNext()、next()、currentItem() 函数，我们都会检查集合上的 modCount 是否等于 expectedModCount，
 * 也就是看，在创建完迭代器之后，modCount 是否改变过。
 *
 *
 * 迭代器类中除了前面提到的几个最基本的方法之外，还定义了一个 remove() 方法，能够在遍历集合的同时，安全地删除集合中的元素。
 *
 */
public class IteratorTest {


    public static void main(String[] args) {
        List names = new ArrayList<>();
        names.add("xzg");
        names.add("wang");
        names.add("zheng");

        // 它只能删除游标指向的前一个元素，而且一个 next() 函数之后，只能跟着最多一个 remove() 操作，多次调用 remove() 操作会报错
        java.util.Iterator iterator = names.iterator();
        iterator.next();
        iterator.remove();
        iterator.remove(); //报错，抛出IllegalStateException异常
    }


    /**
     * 支持快照的迭代器
     * @param <E>
     */
    public class SnapshotArrayIterator<E> implements Iterator<E> {
        private int cursor;
        private ArrayList<E> snapshot;

        public SnapshotArrayIterator(ArrayList<E> arrayList) {
            this.cursor = 0;
            this.snapshot = new ArrayList<>();
            this.snapshot.addAll(arrayList);
        }

        @Override
        public boolean hasNext() {
            return cursor < snapshot.size();
        }

        @Override
        public E next() {
            E currentItem = snapshot.get(cursor);
            cursor++;
            return currentItem;
        }
    }



}


/**
 * 使用时间戳实现的支持快照的迭代器
 * @param <E>
 */
class ArrayList2<E> /* implements List<E> */ {
    private static final int DEFAULT_CAPACITY = 10;

    private int actualSize; //不包含标记删除元素
    private int totalSize; //包含标记删除元素

    private Object[] elements;
    private long[] addTimestamps;
    private long[] delTimestamps;

    public ArrayList2() {
        this.elements = new Object[DEFAULT_CAPACITY];
        this.addTimestamps = new long[DEFAULT_CAPACITY];
        this.delTimestamps = new long[DEFAULT_CAPACITY];
        this.totalSize = 0;
        this.actualSize = 0;
    }

    public void add(E obj) {
        elements[totalSize] = obj;
        addTimestamps[totalSize] = System.currentTimeMillis();
        delTimestamps[totalSize] = Long.MAX_VALUE;
        totalSize++;
        actualSize++;
    }

    public void remove(E obj) {
        for (int i = 0; i < totalSize; ++i) {
            if (elements[i].equals(obj)) {
                delTimestamps[i] = System.currentTimeMillis();
                actualSize--;
            }
        }
    }

    public int actualSize() {
        return this.actualSize;
    }

    public int totalSize() {
        return this.totalSize;
    }

    public E get(int i) {
        if (i >= totalSize) {
            throw new IndexOutOfBoundsException();
        }
        return (E)elements[i];
    }

    public long getAddTimestamp(int i) {
        if (i >= totalSize) {
            throw new IndexOutOfBoundsException();
        }
        return addTimestamps[i];
    }

    public long getDelTimestamp(int i) {
        if (i >= totalSize) {
            throw new IndexOutOfBoundsException();
        }
        return delTimestamps[i];
    }
}

class SnapshotArrayIterator2<E> implements Iterator<E> {
    private long snapshotTimestamp;
    private int cursorInAll; // 在整个容器中的下标，而非快照中的下标
    private int leftCount; // 快照中还有几个元素未被遍历
    private ArrayList2<E> arrayList;

    public SnapshotArrayIterator2(ArrayList2<E> arrayList) {
        this.snapshotTimestamp = System.currentTimeMillis();
        this.cursorInAll = 0;
        this.leftCount = arrayList.actualSize();;
        this.arrayList = arrayList;

        justNext(); // 先跳到这个迭代器快照的第一个元素
    }

    @Override
    public boolean hasNext() {
        return this.leftCount >= 0; // 注意是>=, 而非>
    }

    @Override
    public E next() {
        E currentItem = arrayList.get(cursorInAll);
        justNext();
        return currentItem;
    }

    private void justNext() {
        while (cursorInAll < arrayList.totalSize()) {
            long addTimestamp = arrayList.getAddTimestamp(cursorInAll);
            long delTimestamp = arrayList.getDelTimestamp(cursorInAll);
            if (snapshotTimestamp > addTimestamp && snapshotTimestamp < delTimestamp) {
                leftCount--;
                break;
            }
            cursorInAll++;
        }
    }
}
