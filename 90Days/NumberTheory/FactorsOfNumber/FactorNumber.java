package FactorsOfNumber;

import java.util.Scanner;

public class FactorNumber {

    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        for(int i=1;i<=n/2;i++){
            if(n%i==0){
                IO.print(i + " ");
            }
        }
        IO.print(n); // Print the number itself as it is always a factor of itself
    }
}
