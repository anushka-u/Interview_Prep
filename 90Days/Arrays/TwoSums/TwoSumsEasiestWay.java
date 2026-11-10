package TwoSums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class TwoSumsEasiestWay {
    void main() {
        Scanner scn = new Scanner(System.in);
       int n = scn.nextInt();

       ArrayList<Integer> arr = new ArrayList<>();
        for(int i =0 ;i<n;i++){
            arr.add(scn.nextInt());
        }

        int target = scn.nextInt();

       HashMap<Integer,Integer> hashMap = new HashMap<>();

       for(int i=0;i<n;i++){
            hashMap.put(i,arr.get(i));
       }

       for (int i = 0; i < arr.size(); i++) {
        int complement = target - arr.get(i);

        for (var entry : hashMap.entrySet()) {
            if (entry.getValue() == complement && entry.getKey() != i) {
                IO.println(entry.getKey() + ", " + i);
                return;
            }
        }
    }
}
}
