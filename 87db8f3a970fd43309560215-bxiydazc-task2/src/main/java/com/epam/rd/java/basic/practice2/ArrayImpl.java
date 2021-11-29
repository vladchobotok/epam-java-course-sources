package com.epam.rd.java.basic.practice2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayImpl implements Array {

    Object[] array;
    int size;

    public ArrayImpl(int size) {
        array = new Object[size];
    }

	@Override
    public void clear() {
        array = new Object[0];
        size = 0;
    }

	@Override
    public int size() {
        return size;
    }
	
	@Override
    public Iterator<Object> iterator() {
	    return new IteratorImpl();
    }
	
	private class IteratorImpl implements Iterator<Object> {

        private int currIndex = 0;

        @Override
        public boolean hasNext() {
            return currIndex < array.length;
        }

        @Override
        public Object next() {
            if(hasNext()) {
                return array[currIndex++];
            }
            throw new NoSuchElementException();
        }

    }
	
	@Override
    public void add(Object element) {
        set(size, element);
    }

	@Override
    public void set(int index, Object element) {
        if(index > array.length - 1) {
            Object[] extArray = new Object[index + 1];
            System.arraycopy(array, 0, extArray, 0, array.length);
            extArray[index] = element;
            array = extArray;
            size++;
        }
        else {
            if(array[index] == null) {
                size++;
            }
            array[index] = element;
        }
    }

	@Override
    public Object get(int index) {
        return array[index];
    }

	@Override
    public int indexOf(Object element) {
        for (int i = 0; i < array.length; i++) {
            if(element.equals(array[i])) {
                return i;
            }
        }
        return -1;
    }

	@Override
    public void remove(int index) {
        if(size != 0) {
            Object[] cpyArray = new Object[array.length - 1];
            size = array.length - 1;
            System.arraycopy(array, 0, cpyArray, 0, array.length - 1);
            System.arraycopy(array, index + 1, cpyArray, index, array.length - 1 - index);

            array = cpyArray;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        if(array.length == 0){
            sb.append("null");
        }
        for (int i = 0; i < array.length; i++) {
            sb.append(array[i]);
            if(i != array.length - 1){
                sb.append(", ");
            }
        }
        sb.append(']');
        return sb.toString();
    }

    public static void main(String[] args) {
        ArrayImpl arrayImpl = new ArrayImpl(0);
        Iterator<Object> iter = arrayImpl.iterator();

        arrayImpl.add("someText");
        arrayImpl.add(323);
        arrayImpl.add('d');
        arrayImpl.add(null);
        while(iter.hasNext()) {
            System.out.print(iter.next() + " ");
        }
        System.out.println();
        System.out.println(arrayImpl.toString());
        arrayImpl.set(2, "More text");
        System.out.println(arrayImpl.toString());
        System.out.println(arrayImpl.get(2));
        System.out.println(arrayImpl.indexOf("More text"));
        System.out.println(arrayImpl.indexOf(2112));
        arrayImpl.remove(2);
        arrayImpl.remove(0);
        System.out.println(arrayImpl.size());
        System.out.println(arrayImpl.toString());
    }
}
