package src;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StackTest {


    private List<Integer> items;
    private List<Integer> itemsMin;
    private Integer size;

    public StackTest(int size) {
        items = new ArrayList<>(size);
        itemsMin = new ArrayList<>(size);
        this.size = size;
    }


    public boolean add (int value) {
        if (itemsMin == null || itemsMin.size() == 0) {
            itemsMin.add(value);
        } else if (itemsMin.get(itemsMin.size() - 1) >= value) {
            itemsMin.add(value);
        }
        items.add(value);
        size++;
        return true;
    }

    public Integer pop () {
        Integer integer = items.get(size);
        if (integer == itemsMin.get(itemsMin.size() - 1)) {
            itemsMin.remove(integer);
        }
        items.set(size, null);
        size--;
        return integer;
    }

    public Integer getMin () {
        return itemsMin.get(itemsMin.size() - 1);
    }

}
