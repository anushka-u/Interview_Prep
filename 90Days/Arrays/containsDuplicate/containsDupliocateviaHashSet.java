package containsDuplicate;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class containsDupliocateviaHashSet {
    void main() {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();

        Set<Integer> hashSet =new HashSet<>();

        for(int i =0 ;i<n;i++){
            var value = scn.nextInt();

            if(!hashSet.add(value)){
                IO.print("Found Duplicate");
                return;
            }
        }

        IO.print("No Duplicate");

    }
}
