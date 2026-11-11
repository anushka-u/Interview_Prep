package PrimeNumber;

import java.util.Scanner;

public class PrimeNumber {

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        //TC = O(n)

        for(int i=2;i<n-1;i++){
            if(n%i == 0){
                IO.print("Not Prime");
                return;
            }
        }

        IO.print("Prime Number");

        IO.println();

        //TC -  O(n/2) -> O(n)
        for(int i=2 ; i<=n/2;i++){
            if(n%i==0){
                IO.print("NOt Prime Number");
                return;
            }
        }

        IO.print("Prime Number");
    }
}
