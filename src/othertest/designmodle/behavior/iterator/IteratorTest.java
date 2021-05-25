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
