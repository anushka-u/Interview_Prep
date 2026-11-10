package containsDuplicate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class containsDuplicateThirdLogic {
    void main() {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();

        ArrayList<Integer> arr =new ArrayList<>();

        for(int i =0; i<n;i++){
            arr.add(scn.nextInt());
        }
        Map<Integer,Integer> containsDuplicateCount = new HashMap<>();
        
        boolean duplicateFlag = false;
        for(int i: arr){
            if(containsDuplicateCount.containsKey(i)){
                duplicateFlag = true;
                break;
            }
            else{
                containsDuplicateCount.put(i, 1);
            }
        }

        if(duplicateFlag){
            IO.print("Found duplicate");
        }else{
            IO.print("No duplicate");
        }
    }
}
