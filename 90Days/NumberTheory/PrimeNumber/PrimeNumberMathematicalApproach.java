package PrimeNumber;

import java.util.Scanner;

public class PrimeNumberMathematicalApproach {

    public static void main(String[] args){
        Scanner scananer =  new Scanner(System.in);
        int n = scananer.nextInt();

        for(int i=2;i<=Math.sqrt(n);i++){
            if(n%i==0){
                IO.print("Not Prime");
                return;
            }
        }
        IO.print("Prime Number");
    }
}
