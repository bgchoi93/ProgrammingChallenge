package a1;

import java.util.Scanner;

public class DigitSum {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        while ( N != 0) {
            System.out.println(Integer.toString(findSmallestP(N)));
            N = in.nextInt();
        }
    }


    /**
     * find min p such that digitSum(N * p) == digitSum(N)
     * @param N given number N
     * @return minimum p
     */
    private static int findSmallestP(int N) {
        int digitSumN = getDigitSum(N);

        // start from p = 11 and iteratively increase p to find p
        int p = 11;

        while (true) {
            int digitSumNp = getDigitSum(N * p);
            if (digitSumNp == digitSumN) {
                return p;
            }
            p++;
        }
    }

    /**
     * Get the sum of digits of the given number
     * @param i Given integer i
     * @return sum of digits
     */
    private static int getDigitSum(int i) {

        int sum = 0;

        // Iteratively sum up digits
        while (i > 0) {
            // Using mathematical property of log base 10 : 10^p = y <=> log_10(y) = p
            int power = (int) Math.log10((double) i);
            int digit = i / (int) Math.pow(10.0, (double) power);
            sum += i / (int) Math.pow(10.0, (double) power);
            i = i % (int) (Math.pow(10.0, (double) power));
        }

        return sum;
    }
}
