package com.epam.rd.java.basic.practice6.part2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Part2 {

    private static final int N = 10000;

    private static final int K = 4;

    private int listLength;

    private List<Integer> list;

    Part2(int listLength) {
        this.listLength = listLength;
    }

    public static void main(String[] args) {

        Part2 part2 = new Part2(N);
        System.out.println("ArrayList#Index:" + " " + removeByIndex(part2.getArrayList(), K) + " ms");
        System.out.println("LinkedList#Index:" + " " + removeByIndex(part2.getLinkedList(), K) + " ms");
        System.out.println("ArrayList#Iterator:" + " " + removeByIterator(part2.getArrayList(), K) + " ms");
        System.out.println("LinkedList#Iterator:" + " " + removeByIterator(part2.getLinkedList(), K) + " ms");

    }

    public static long removeByIterator(final List<Integer> list, final int k) {

        long time = System.currentTimeMillis();
        int count = 0;
        Iterator<Integer> it = list.iterator();
        while (list.size() > 1) {
            if (it.hasNext()) {
                it.next();
                count++;
                if (count == k) {
                    it.remove();
                    count = 0;
                }
            } else {
                it = list.iterator();
            }
        }
        return System.currentTimeMillis() - time;
    }

    public static long removeByIndex(final List<Integer> list, int k) {
        long time = System.currentTimeMillis();
        int local = 0;
        for (int i = list.size() - 1; i >= 0; i--) {
            if (list.size() == 1) {
                break;
            }

            local += (k - 1);

            while (local >= list.size()) {
                local = local - list.size();
            }
            list.remove(local);
        }
        return System.currentTimeMillis() - time;
    }

    public List<Integer> getArrayList() {
        list = new ArrayList<>();
        for (int i = 0; i < listLength; i++) {
            list.add(i);
        }
        return new ArrayList<>(list);
    }

    public List<Integer> getLinkedList() {
        list = new LinkedList<>();
        for (int i = 0; i < listLength; i++) {
            list.add(i);
        }
        return new LinkedList<>(list);
    }
}
