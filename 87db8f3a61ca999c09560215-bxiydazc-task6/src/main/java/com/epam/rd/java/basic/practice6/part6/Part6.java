package com.epam.rd.java.basic.practice6.part6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part6 {

    private String fileName;
    private String[] input;

    Part6() {
    }

    public static void main(String[] args) {
        new Part6().console(args[0], args[1], args[2], args[3]);
    }

    boolean errEx(String input, String task){
        if (!("--input".equals(input) || "-i".equals(input))) {
            System.err.println("Wrong operation");
            return false;
        }
        if (!("--task".equals(task) || "-t".equals(task))) {
            System.err.println("Wrong task");
            return false;
        }
        return true;
    }

     private boolean console(String input, String fileName, String task, String operation) {

        if(!errEx(input, task)){
            return false;
        }

        initialize(fileName);

        switch (operation) {
            case "frequency":
                getResultFrequency();
                break;
            case "length":
                getResultLength();
                break;
            case "duplicates":
                getResultDuplicates();
                break;
            default:
                return false;
        }
        return true;
    }

    String getInput() {
        StringBuilder sb = new StringBuilder();
        try (Scanner file = new Scanner(new File(fileName), "CP1251")) {
            while (file.hasNext()) {
                sb.append(file.next()).append(" ");
            }
        } catch (FileNotFoundException e) {
            System.err.println(String.format("File: %s not found", fileName));
        }
        return sb.toString();
    }

    public void initialize(String filename) {
        this.fileName = filename;
        StringBuilder sb = new StringBuilder();
        Pattern p = Pattern.compile("[a-zA-Z]+");
        Matcher m = p.matcher(getInput());
        while (m.find()) {
            sb.append(m.group() + " ");
        }
        input = sb.toString().split(" ");
    }

    void getResultLength() {

        class CountWithPlace {
            private final int length;
            private final int place;

            public int getLength() {
                return length;
            }

            public CountWithPlace(int length, int place) {
                this.length = length;
                this.place = place;
            }
        }
        final HashMap<String, CountWithPlace> wordCounts = new HashMap<>();
        for (int place = 0; place < input.length; place++) {
            String w = input[place];
            wordCounts.putIfAbsent(w, new CountWithPlace(w.length(), place));
        }
        TreeMap<String, CountWithPlace> sortedWords = new TreeMap<>((a, b) -> {
            CountWithPlace countWithPlaceA = wordCounts.get(a);
            CountWithPlace countWithPlaceB = wordCounts.get(b);
            int length = countWithPlaceB.length - countWithPlaceA.length;
            if (length == 0) {
                return countWithPlaceA.place - countWithPlaceB.place;
            } else {
                return length;
            }
        });
        sortedWords.putAll(wordCounts);

        int i = 0;
        for (Map.Entry<String, CountWithPlace> s : sortedWords.entrySet()) {
            if (i == 3) {
                break;
            }
            i++;
            System.out.println(s.getKey() + " ==> " + s.getValue().getLength());
        }
    }

    void getResultFrequency() {
        class CountWithPlace {
            private int count = 1;
            private final int place;

            public CountWithPlace(int place) {
                this.place = place;
            }

            public int getCount() {
                return count;
            }

            public CountWithPlace setCount(int count) {
                this.count = count;
                return this;
            }
        }

        final HashMap<String, CountWithPlace> wordCounts = new HashMap<>();
        for (int place = 0; place < input.length; place++) {
            String w = input[place];
            CountWithPlace countWithPlace = wordCounts.get(w);
            if (countWithPlace == null) {
                wordCounts.put(w, new CountWithPlace(place));
            } else {
                countWithPlace.setCount(countWithPlace.getCount() + 1);
            }
        }
        TreeMap<String, CountWithPlace> sortedWords = new TreeMap<>((a, b) -> {
            CountWithPlace countWithPlaceA = wordCounts.get(a);
            CountWithPlace countWithPlaceB = wordCounts.get(b);
            int count = countWithPlaceB.count - countWithPlaceA.count;
            if (count == 0) {
                return countWithPlaceA.place - countWithPlaceB.place;
            } else {
                return count;
            }
        });
        sortedWords.putAll(wordCounts);

        TreeSet<String> firstStrings = new TreeSet<>(Comparator.reverseOrder());
        int i = 0;
        for (String s : sortedWords.keySet()) {
            if (i == 3) {
                break;
            }
            i++;
            firstStrings.add(s);
        }
        for (String s : firstStrings) {
            System.out.println(s + " ==> " + sortedWords.get(s).getCount());
        }
    }

    void getResultDuplicates() {
        final Map<String, Integer> wordCounts = new LinkedHashMap<>();
        for (int place = 0; place < input.length; place++) {
            String w = input[place];
            Integer countWithPlace = wordCounts.get(w);
            if (countWithPlace == null) {
                wordCounts.put(w, 1);
            } else {
                wordCounts.put(w, countWithPlace + 1);
            }
        }
        int i = 0;
        for (Map.Entry<String, Integer> wordCount : wordCounts.entrySet()) {
            if (i == 3) {
                break;
            }
            if (wordCount.getValue() > 1) {
                i++;
                System.out.println(new StringBuilder(wordCount.getKey()).reverse().toString().toUpperCase());
            }
        }

    }
}
