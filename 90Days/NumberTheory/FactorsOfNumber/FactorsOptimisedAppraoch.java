package FactorsOfNumber;

import java.util.Scanner;

public class FactorsOptimisedAppraoch {

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        for(int i=1;i<Math.sqrt(n);i++){
            if(n%i==0){
                IO.print(i + " ");
                if(i!=n/i) // To avoid printing the square root twice for perfect squares
                    IO.print(n/i + " ");
            }
        }
    }
}
