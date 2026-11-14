package binaryExponentiation;

import java.util.Scanner;
//Brute Approach
public class FindPower {

    static void main() {
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        int y = sc.nextInt();
        IO.print(findPower(x, y));
    }

    public static int findPower(int x, int y){
        int power = 1;

        for(int i=1;i<=y;i++){
            power*=x;
        }

        return power;
    }
}
