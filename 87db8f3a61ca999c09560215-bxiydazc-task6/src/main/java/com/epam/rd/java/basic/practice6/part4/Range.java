package com.epam.rd.java.basic.practice6.part4;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Range implements Iterable<Integer>{

    private int first;
    private int second;
    private boolean reversed;

    public Range(int n, int m) {
        this(n, m, false);
    }

    public Range(int firstBound, int secBound, boolean reversedOrder) {
        reversed = reversedOrder;
        first = firstBound;
        second = secBound;
    }
    
    @Override
    public Iterator<Integer> iterator() {
        return new IteratorImpl();
    }
    
    private final class IteratorImpl implements Iterator<Integer> {

        private int pointer;

        IteratorImpl(){
            if(reversed){
                pointer = second + 1;
            }
            else{
                pointer = first - 1;
            }
        }

        @Override
        public boolean hasNext() {
            if(reversed){
                return pointer > first;
            }
            else{
                return pointer < second;
            }
        }

        @Override
        public Integer next() {
            if(reversed){
                if(!hasNext()){
                    throw new NoSuchElementException();
                }
                return --pointer;
            }
            else{
                if(!hasNext()){
                    throw new NoSuchElementException();
                }
                return ++pointer;
            }
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
