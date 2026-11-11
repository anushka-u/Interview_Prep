package PrimeNumber;

import java.util.Scanner;

public class PrimeSubtraction {

    public static void main(String[] args){
        Scanner scanner =  new Scanner(System.in);
        int x = scanner.nextInt();
        int y = scanner.nextInt();

        if(x<=y || x-y ==1){
            IO.print("NO");
        }
        else{
            IO.print("YES");
        }

    }
}
