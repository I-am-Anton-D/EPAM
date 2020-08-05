package classes;

import java.lang.reflect.Array;

/**
 * Some abstract holder for tasks 8,9,10
 */

public class AbstractHolder<T> {
    public T[] array;
    public int index;

    public AbstractHolder(Class<T> e, int count) {
        this.array = (T[]) Array.newInstance(e, count);
        index = 0;
    }

    public boolean addElement(T element) {
        if (index == array.length) {
            return false;
        }
        this.array[index++] = element;
        return true;
    }

    public int getSize() {
        return array.length;
    }

    public void print(){
        for (int i = 0; i<index ; i++) {
            System.out.println(array[i].toString());
        }
    }
}
