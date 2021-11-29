package com.epam.rd.java.basic.practice4;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part4 {

    public static void main(String[] args) {
        String input = Part1.getInput("part4.txt");
        IterableClass c = new IterableClass(input);

        Iterator<String> iterator = c.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next().replaceAll("\\s+", " "));
        }
    }

}

class IterableClass implements Iterable<String> {

    String input;

    IterableClass(String input) {
        this.input = input;
    }

    @Override
    public Iterator<String> iterator() {
        return new IteratorIterableClass();
    }

    private class IteratorIterableClass implements Iterator<String> {
        Pattern pattern = Pattern.compile("(?<=^|\\s)[A-ZА-ЯІЇЄҐ][^\\.]+[.]");
        Matcher matcher = pattern.matcher(input);

        @Override
        public boolean hasNext() {
            return matcher.find();
        }

        @Override
        public String next() {
            if(matcher.group() == null){
                throw new NoSuchElementException();
            }
            return matcher.group();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
