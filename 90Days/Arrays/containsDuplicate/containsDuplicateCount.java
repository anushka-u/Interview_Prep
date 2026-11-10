package containsDuplicate;

import java.util.ArrayList;
import  java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class containsDuplicateCount {
    void main(){
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();

        ArrayList<Integer> arr =new ArrayList<>();

        for(int i =0; i<n;i++){
            arr.add(scn.nextInt());
        }
        Map<Integer,Integer> containsDuplicateCount = new HashMap<>();
        
        int count =1;
        for(int i : arr){

            if(!containsDuplicateCount.containsKey(i)){
                containsDuplicateCount.put(i,1);
                count=1;
            }
            else{
                containsDuplicateCount.put(i, containsDuplicateCount.getOrDefault(i, 0) + 1);
            }
        }

        boolean duplicateFlag = false;
        for(var e : containsDuplicateCount.entrySet()){
            if(e.getValue()>1){
                duplicateFlag = true;
                break;
            }
        }

        if(duplicateFlag){
            IO.print("Contains Duplicate");
        }else{
            IO.print("No Duplicate in Array");
        }


    }
}
