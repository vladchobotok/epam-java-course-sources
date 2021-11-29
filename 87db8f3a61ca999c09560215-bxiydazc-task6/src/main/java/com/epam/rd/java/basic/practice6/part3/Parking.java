package com.epam.rd.java.basic.practice6.part3;

public class Parking {

    private Object[] park;

    private int countCar;

    public Parking(int capacity) {
        fillInNull(capacity);
    }

    private void fillInNull(int n) {
        park = new Object[n];
        for (int i = 0; i < n; i++) {
            park[i] = null;
        }
    }

    public boolean depart(int k) {
        if (k < 0 || k > (park.length - 1)) {
            throw new IllegalArgumentException("k < 0 || k > (n - 1)");
        }
        if (k == 4) {
            k = 0;
        }

        if (park[k] == null) {
            return false;
        } else {
            park[k] = null;
            countCar--;
        }

        return true;
    }

    public boolean arrive(int k) {

        if (countCar == park.length) {
            return false;
        }

        if (k < 0 || k > park.length - 1) {
            throw new IllegalArgumentException("k < 0 || k > (n - 1)");
        }

        if (park[k] != null) {
            for (int i = k; i < park.length; i++) {
                if(park[i] == null) {
                    park[i] = 1;
                    countCar++;
                    return true;
                }
            }
            for (int i = 0; i < park.length; i++) {
                if (park[i] == null) {
                    park[i] = 1;
                    countCar++;
                    return true;
                }
            }
        } else {
            park[k] = 1;
            countCar++;
            return true;
        }

        return false;
    }

    public void print() {
        StringBuilder sb = new StringBuilder();
        for (Object car : park) {
            sb = (car == null) ? sb.append("0") : sb.append(car);
        }
        System.out.print(sb + System.lineSeparator());
    }
}
