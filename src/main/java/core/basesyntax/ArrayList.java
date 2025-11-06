package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    static final int DEFAULT_ARRAY_SIZE = 10;
    private Object[] elementData = new Object[DEFAULT_ARRAY_SIZE];
    private int size = 0;

    public Object[] getElementData() {
        return elementData;
    }

    public void setElementData(Object[] elementData) {
        this.elementData = elementData;
    }

    public Object[] grow() {
        Object[] newArray = new Object[elementData.length + elementData.length / 2];
        for (int i = 0; i < elementData.length; i++) {
            newArray[i] = elementData[i];
        }
        return newArray;
    }

    @Override
    public void add(T value) {
        if (size == elementData.length - 1) {
            elementData = grow();
        }
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (size == elementData.length - 1) {
            elementData = grow();
        }
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index is invalid");
        } else {
            Object[] newArray = new Object[elementData.length];
            for (int i = 0; i < index; i++) {
                newArray[i] = elementData[i];
            }
            newArray[index] = value;
            for (int i = index; i < elementData.length - 1; i++) {
                newArray[i + 1] = elementData[i];
            }
            elementData = newArray;
            size++;
        }
    }

    @Override
    public void addAll(List<T> list) {
        do {
            elementData = grow();
        } while (size + list.size() > elementData.length);
        for (int i = 0; i < list.size(); i++) {
            elementData[size] = list.get(i);
            size++;
        }
    }

    @Override
    public T get(int index) {
        if (index < size && index >= 0) {
            return (T) elementData[index];
        } else {
            throw new ArrayListIndexOutOfBoundsException("index is invalid");
        }
    }

    @Override
    public void set(T value, int index) {
        if (index < size && index >= 0) {
            elementData[index] = value;
        } else {
            throw new ArrayListIndexOutOfBoundsException("index is invalid");
        }
    }

    @Override
    public T remove(int index) {
        if (index < size && index >= 0) {
            Object[] newArray = new Object[elementData.length];
            for (int i = 0; i < index; i++) {
                newArray[i] = elementData[i];
            }
            for (int i = index; i < elementData.length - 1;i++) {
                newArray[i] = elementData[i + 1];
            }
            Object removed = elementData[index];
            elementData = newArray;
            size--;
            return (T)removed;
        } else {
            throw new ArrayListIndexOutOfBoundsException("index is invalid");
        }
    }

    @Override
    public T remove(T element) {
        int index = -1;
        for (int i = 0; i < elementData.length; i++) {
            if (Objects.equals(elementData[i],element)) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            Object[] newArray = new Object[elementData.length];
            for (int i = 0; i < index; i++) {
                newArray[i] = elementData[i];
            }
            for (int i = index; i < elementData.length - 1;i++) {
                newArray[i] = elementData[i + 1];
            }
            Object removed = elementData[index];
            elementData = newArray;
            size--;
            return (T)removed;
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0 ? true : false;
    }
}
