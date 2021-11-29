package com.epam.rd.java.basic.practice6.part1;

import java.io.Serializable;
import java.util.Comparator;

public class Word {

    private String content;

    private int frequency;

    Word(String value) {
        this.content = value;
        frequency = 1;
    }

    String getValue() {
        return content;
    }

    int getFrequency() {
        return frequency;
    }

    void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Word word1 = (Word) obj;

        return frequency == word1.frequency && content.equals(word1.content);
    }

    @Override
    public int hashCode() {
        int result = content.hashCode();
        result = 31 * result + frequency;
        return result;
    }

    static class CompareByWord implements Comparator<Word>, Serializable{

        private static final long serialVersionUID = -6681184473678729928L;

        @Override
        public int compare(Word o1, Word o2) {
            return o1.getValue().compareTo(o2.getValue());
        }
    }

    static class CompareByFrequency implements Comparator<Word>, Serializable {

        private static final long serialVersionUID = 6878903747237297954L;

        @Override
        public int compare(Word o1, Word o2) {
            int comparation = -o1.getFrequency() + o2.getFrequency();
            if (comparation == 0) {
                return new CompareByWord().compare(o1, o2);
            }
            return comparation;
        }
    }
}