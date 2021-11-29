package com.epam.rd.java.basic.practice3;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Part4 {

    public static void main(String[] args) throws NoSuchAlgorithmException {
        System.out.println(hash("asdf", "MD5"));
        System.out.println(hash("asdf", "SHA-256"));
    }

    public static String hash(String input, String algorithm) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance(algorithm);

        digest.update(input.getBytes());

        byte[] hash = digest.digest();

        StringBuilder sb = new StringBuilder();

        for (byte b : hash) {
            StringBuilder num = new StringBuilder();

            int additionalNum = Math.abs(b);

            for (int j = 0; j < (8 - Integer.toBinaryString(additionalNum).length()); j++) {
                num.append('0');
            }

            num.append(Integer.toBinaryString(additionalNum));

            if (b < 0) {
                binaryLoops(num);
            }
            int decimal = Integer.parseInt(num.toString(), 2);
            sb.append(String.format("%02X", decimal).toUpperCase());
        }
        return sb.toString();
    }

    public static void binaryLoops(StringBuilder num){
        for (int j = 0; j < num.toString().length(); j++) {
            if (num.toString().charAt(j) == '0') {
                num.setCharAt(j, '1');
            } else if (num.toString().charAt(j) == '1') {
                num.setCharAt(j, '0');
            }
        }
        for (int j = num.toString().length() - 1; j >= 0; j--) {
            if (num.toString().charAt(j) == '1') {
                num.setCharAt(j, '0');
            }
            else if (num.toString().charAt(j) == '0') {
                num.setCharAt(j, '1');
                break;
            }
        }
    }
}
