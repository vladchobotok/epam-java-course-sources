package com.epam.rd.java.basic.practice6.part6;

public class Part62 {

    Part6 part6;

    Part62() {
    }

    public static void main(String[] args) {
        new Part62().console("--input", "part6.txt", "--task", "length");
    }

    private boolean console(String input, String fileName, String task, String operation) {
        if(!part6.errEx(input, task)){
            return false;
        }

        part6.initialize(fileName);

        if ("length".equals(operation)) {
            part6.getResultLength();
        } else {
            return false;
        }
        return true;
    }
}
