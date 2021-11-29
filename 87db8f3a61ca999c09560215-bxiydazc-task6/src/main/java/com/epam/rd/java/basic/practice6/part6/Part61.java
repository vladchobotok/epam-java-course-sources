package com.epam.rd.java.basic.practice6.part6;

public class Part61 {

    Part6 part6;

    Part61() {
    }

    public static void main(String[] args) {
        new Part61().console("--input", "part6.txt", "--task", "frequency");
    }

    private boolean console(String input, String fileName, String task, String operation) {
        if(!part6.errEx(input, task)){
            return false;
        }

        part6.initialize(fileName);

        if ("frequency".equals(operation)) {
            part6.getResultDuplicates();
        } else {
            return false;
        }
        return true;
    }
}
