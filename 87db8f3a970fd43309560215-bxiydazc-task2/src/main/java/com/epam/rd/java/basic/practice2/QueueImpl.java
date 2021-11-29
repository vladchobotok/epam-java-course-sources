package com.epam.rd.java.basic.practice2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class QueueImpl implements Queue {

    Object[] queue;
    int size;

    public QueueImpl() {
        size = 0;
        queue = new Queue[size];
    }

    @Override
    public void clear() {
        size = 0;
        queue = new Queue[0];
    }

    @Override
    public int size() {
        return size;
    }

    public Iterator<Object> iterator() {
        return new IteratorImpl();
    }

    private class IteratorImpl implements Iterator<Object> {

        private int currIndex = 0;

        @Override
        public boolean hasNext() {
            return currIndex < queue.length;
        }

        @Override
        public Object next() {
            if(hasNext()) {
                return queue[currIndex++];
            }
            throw new NoSuchElementException();
        }
    }

    @Override
    public void enqueue(Object element) {
        Object[] extQueue = new Object[queue.length + 1];
        System.arraycopy(queue, 0, extQueue, 0, queue.length);
        extQueue[extQueue.length-1] = element;
        queue = extQueue;
        size++;
    }

    @Override
    public Object dequeue() {
        if(queue.length != 0) {
            Object[] cpyQueue = new Object[queue.length - 1];
            Object cut = queue[0];
            System.arraycopy(queue, 1, cpyQueue, 0, queue.length - 1);

            queue = cpyQueue;
            size--;
            return cut;
        }

        return null;
    }

    @Override
    public Object top() {
        if(size != 0) {
            return queue[0];
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (int i = 0; i < queue.length; i++) {
            sb.append(queue[i]);
            if(i != queue.length - 1){
                sb.append(", ");
            }
        }
        if(queue.length == 0){
            sb.append("null");
        }
        sb.append(']');
        return sb.toString();
    }

    public static void main(String[] args) {
        QueueImpl queueImpl = new QueueImpl();
        Iterator<Object> iter = queueImpl.iterator();

        queueImpl.enqueue("someText");
        queueImpl.enqueue('d');
        queueImpl.enqueue(74);
        while(iter.hasNext()) {
            System.out.print(iter.next() + " ");
        }
        System.out.println();
        System.out.println(queueImpl.toString());
        System.out.println(queueImpl.top());
        System.out.println(queueImpl.dequeue());
        System.out.println(queueImpl.size());
        System.out.println(queueImpl.toString());
    }

}
