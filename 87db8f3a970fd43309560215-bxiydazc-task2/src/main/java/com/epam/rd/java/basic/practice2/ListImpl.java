package com.epam.rd.java.basic.practice2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListImpl implements List {

    public static class Node {
        Object data;
        Node next;
        Node prev;
        public Node(Object data) {
            this.data = data;
            this.next = null;
            this.prev = null;
        }
    }

    Node head;
    Node tail;
    int size;

    public ListImpl() {
        size = 0;
    }

    @Override
    public void clear() {
        head.data = null;
        head.next = null;
        tail.data = null;
        tail.prev = null;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    public Iterator<Object> iterator() {
        return new IteratorImpl();
    }

    private class IteratorImpl implements Iterator<Object> {

        private Node currNode = head;
        private int currIndex = 0;

        @Override
        public boolean hasNext() {
            return currIndex < size && currNode != null;
        }

        @Override
        public Object next() {
            if(hasNext()) {
                currIndex++;
                Node dataNode = currNode;
                currNode = currNode.next;
                return dataNode.data;
            }
            throw new NoSuchElementException();
        }
    }

    @Override
    public void addFirst(Object element) {
        Node firstElem = new Node(element);
        Node oldHead = head;
        if(head == null) {
            head = firstElem;
            if(tail != null){
                head.next = tail;
                tail.prev = head;
            }
        }
        else {
            head = firstElem;
            head.next = oldHead;
            oldHead.prev = head;
            if(tail == null) {
                tail = oldHead;
            }
        }
        size++;
    }

    @Override
    public void addLast(Object element) {
        Node lastElem = new Node(element);
        Node oldTail = tail;
        if(head == null) {
            head = lastElem;
        }
        else if(tail == null) {
            tail = lastElem;
            head.next = lastElem;
            tail.prev = head;
        }
        else {
            tail = lastElem;
            oldTail.next = tail;
            tail.prev = oldTail;
        }
        size++;
    }

    @Override
    public void removeFirst() {
        head = head.next;
        head.prev = null;
        if(size != 0) {
            size--;
        }
    }

    @Override
    public void removeLast() {
        Node deletedElem = tail;
        tail = deletedElem.prev;
        tail.next = null;
        if(size != 0) {
            size--;
        }
    }

    @Override
    public Object getFirst() {
        return head.data;
    }

    @Override
    public Object getLast() {
        return tail.data;
    }

    @Override
    public Object search(Object element) {
        Node currNode = head;
        for (int i = 0; i < size; i++) {
            if(element.equals(currNode.data)){
                return element;
            }
            currNode = currNode.next;
        }
        return null;
    }

    @Override
    public boolean remove(Object element) {
        Node currNode = head;
        while(currNode != null) {
            if(currNode.data == element){
                if(element == head.data) {
                    head = head.next;
                    head.prev = null;
                }
                else if(tail.data == element) {
                    tail = tail.prev;
                    tail.next = null;
                }
                else{
                    currNode.prev.next = currNode.next;
                    currNode.next.prev = currNode.prev;
                }
                size--;
                return true;
            }
            currNode = currNode.next;
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        Node currNode = head;
        for (int i = 0; i < size; i++) {
            sb.append(currNode.data);
            if(i != size - 1){
                sb.append(", ");
            }
            currNode = currNode.next;
        }
        sb.append(']');
        return sb.toString();
    }

    public static void main(String[] args) {
        ListImpl listImpl = new ListImpl();
        Iterator<Object> iter = listImpl.iterator();

        listImpl.addFirst(323);
        listImpl.addFirst(null);
        listImpl.addFirst("someText");
        listImpl.addLast("46Dsa");
        listImpl.addLast('d');
        listImpl.addLast(74);
        while(iter.hasNext()) {
            System.out.print(iter.next() + " ");
        }
        System.out.println();
        System.out.println(listImpl.toString());
        System.out.println(listImpl.getFirst());
        System.out.println(listImpl.getLast());
        listImpl.removeFirst();
        listImpl.remove("46Dsa");
        listImpl.remove(null);
        listImpl.removeLast();
        System.out.println(listImpl.toString());
        System.out.println(listImpl.search("someText"));
        System.out.println(listImpl.search(2112));
        System.out.println(listImpl.size());
        System.out.println(listImpl.toString());
    }
}
