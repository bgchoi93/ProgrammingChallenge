package a1;

import java.util.Scanner;

public class Oddities {

    private final static String EVEN = " is even";
    private final static String ODD = " is odd";

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();

        // read input
        for (int i = 0; i < n; i++) {
            int input = in.nextInt();
            System.out.println(isOddOrEven(input));
        }
    }

    /**
     * Return the oddity result of given number i
     * @param i number to test
     * @return string indicating whether i is odd or even
     */
    static String isOddOrEven(int i) {

        if (i % 2 == 0) {
            return Integer.toString(i).concat(EVEN);
        }
        else {
            return Integer.toString(i).concat(ODD);
        }
    }
}
