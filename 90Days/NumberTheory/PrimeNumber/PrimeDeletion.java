package PrimeNumber;

import java.util.Scanner;

public class PrimeDeletion {

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();

        for(int i=0;i<s.length();i++){
            char c = s.charAt(i);
            if(c=='1'){
                IO.print("13");
                return;
            } else if (c=='3') {
                IO.print("31");
                return;
            }
        }
    }
}
