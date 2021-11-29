package com.epam.rd.java.basic.practice6.part6;

public final class Part63 {

    Part6 part6;

    Part63() {
    }

    public static void main(String[] args) {

        new Part63().console("--input", "part6.txt", "--task", "duplicates");
    }

    private boolean console(String input, String fileName, String task, String operation) {
        if(!part6.errEx(input, task)){
            return false;
        }

        part6.initialize(fileName);

        if ("duplicates".equals(operation)) {
            part6.getResultDuplicates();
        } else {
            return false;
        }
        return true;
    }

}