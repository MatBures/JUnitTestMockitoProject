package org.firstPF;

import java.util.Scanner;

public class Calculator {
    Scanner scanner = new Scanner(System.in);
    public int integerDivision(int dividend, int divisor) {

        return dividend/divisor;
    }

    public int integerSubtraction(int minuend, int subtrahend) {
        return minuend - subtrahend;
    }

    public int enterNumber(){
        int enteredNumber;
        if (scanner.hasNextInt()) {
            enteredNumber = scanner.nextInt();
            return enteredNumber;
        }
        scanner.next();
        System.out.println("Invalid input. You need to write a number.");

        return enterNumber();
    }
}
