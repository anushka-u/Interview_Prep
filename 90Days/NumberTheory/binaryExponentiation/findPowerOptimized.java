package binaryExponentiation;

import java.util.Scanner;

public class findPowerOptimized {

    static void main() {
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        int y = sc.nextInt();
        IO.print(findPower(x, y));
    }

    static int findPower(int x, int y) {
        if (y == 0) {
            return 1;
        }

        int a = findPower(x, y / 2);

        if (y % 2 == 1) {
            return a * a * x;
        } else {
            return a * a;
        }
    }
}
