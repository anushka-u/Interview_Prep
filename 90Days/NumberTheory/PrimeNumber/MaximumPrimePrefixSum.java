package PrimeNumber;

import java.util.Scanner;

public class MaximumPrimePrefixSum {

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        int countOne =0;
        int countTwo=0;
        for(int i=0;i<n;i++){
            if(scanner.nextInt()==1){
                countOne++;
            }
            else{
                countTwo++;
            }
        }

        if(countOne==n){
            for(int i =0;i<n;i++){
                IO.print(1 + " ");
            }
        }else if(countTwo==n){
            for(int i =0;i<n;i++){
                IO.print(2 + " ");
            }
        }else{
            IO.print(2 + " " + 1 + " ");
            for(int i =0;i<countTwo-1;i++){
                IO.print(2 + " ");
            }
            for(int i=0;i<countOne-1;i++){
                IO.print(1 + " ");
            }
        }
    }
}
