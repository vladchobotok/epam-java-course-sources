package com.epam.rd.java.basic.practice2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class StackImpl implements Stack {

    Object[] stack;
    int size;

    public StackImpl() {
        size = 0;
        stack = new Stack[size];
    }

    @Override
    public void clear() {
        stack = new Stack[0];
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    public Iterator<Object> iterator() {
        return new StackImpl.IteratorImpl();
    }

    private class IteratorImpl implements Iterator<Object> {

        private int currIndex = 0;

        @Override
        public boolean hasNext() {
            return currIndex < stack.length;
        }

        @Override
        public Object next() {
            if(hasNext()) {
                return stack[currIndex++];
            }
            throw new NoSuchElementException();
        }
    }

    @Override
    public void push(Object element) {
        Object[] extStack = new Object[stack.length + 1];
        System.arraycopy(stack, 0, extStack, 1, stack.length);
        extStack[0] = element;
        stack = extStack;
        size++;
    }

    @Override
    public Object pop() {
        if(stack.length != 0) {
            Object cut = stack[0];
            Object[] cpyStack = new Object[stack.length - 1];
            System.arraycopy(stack, 1, cpyStack, 0, stack.length - 1);

            stack = cpyStack;
            size--;

            return cut;
        }
        return null;
    }

    @Override
    public Object top() {
        if(size != 0) {
            return stack[0];
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        if(stack.length == 0){
            sb.append("null");
        }
        for (int i = stack.length - 1; i >= 0; i--) {
            sb.append(stack[i]);
            if(i != 0){
                sb.append(", ");
            }
        }
        sb.append(']');
        return sb.toString();
    }

    public static void main(String[] args) {
        StackImpl stackImpl = new StackImpl();
        Iterator<Object> iter = stackImpl.iterator();

        stackImpl.push("someText");
        stackImpl.push('d');
        stackImpl.push(74);
        while(iter.hasNext()) {
            System.out.print(iter.next() + " ");
        }
        System.out.println();
        System.out.println(stackImpl.toString());
        System.out.println(stackImpl.top());
        System.out.println(stackImpl.pop());
        System.out.println(stackImpl.pop());
        System.out.println(stackImpl.pop());
        System.out.println(stackImpl.size());
        System.out.println(stackImpl.toString());
    }

}
