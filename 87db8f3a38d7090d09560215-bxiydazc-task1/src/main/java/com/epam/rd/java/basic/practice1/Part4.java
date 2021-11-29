package com.epam.rd.java.basic.practice1;

public class Part4 {

    public static void main(String[] args) {
        int x;
        int y;
        x = Integer.parseInt(args[0]);
        y = Integer.parseInt(args[1]);
        while(x != y) {
            if(x > y) {
                x -= y;
            }
            else {
                y -= x;
            }
        }
        System.out.print(x);
    }

}
