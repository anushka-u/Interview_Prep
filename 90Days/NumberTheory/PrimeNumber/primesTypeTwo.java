package PrimeNumber;

import java.util.Scanner;

public class primesTypeTwo {

    public static void main(String[] args){
        Scanner scanneer =  new Scanner(System.in);
        int n = scanneer.nextInt();

        if(isPrime(n-2)){
            IO.print(2 + " " + (n-2));
        }
        else {
            IO.print("-1");
        }

    }

    static boolean isPrime(int n){
        if(n<=1) return false;
        for(int i = 2;i<=Math.sqrt(n);i++){
            if(n%i==0){
                return false;
            }
        }
        return true;
    }
}
