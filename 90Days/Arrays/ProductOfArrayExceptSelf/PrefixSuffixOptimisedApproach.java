package ProductOfArrayExceptSelf;

import java.util.Scanner;

public class PrefixSuffixOptimisedApproach {

    void main(){
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int[] nums = new int[n];
        
        for (int i = 0; i < n; i++) {
            nums[i] = scn.nextInt();
        }
//1 2 3 4
        int[] prefixProductArray = new int[n];
        int prefix_Product = 1;
        for(int i=0;i<nums.length;i++){
            if(i-1<0){
                prefixProductArray[i] = prefix_Product;
            }
            else{
                prefix_Product*=nums[i-1];
                prefixProductArray[i]=prefix_Product;
            }
        }
//1 1 2 6
        int[] suffixProductArray = new int[n];
        int suffix_product = 1;
        for(int i = 0; i<nums.length;i++){
            if(i-1<0){
                suffixProductArray[nums.length-i-1]=suffix_product;
            }
            else{
                suffix_product*=nums[nums.length-i];
                suffixProductArray[nums.length-i-1]=suffix_product;
            }
        }
//24 12 4 1
        for(int i : prefixProductArray){
            IO.print(i + " ");
        }
        IO.println();
        for(int i: suffixProductArray){
            IO.print(i+ " ");
        }

        //Combining both prefix and suffix product array to get the final result
        int[] result = new int[n];
        for(int i=0;i<result.length;i++) {
            result[i] = prefixProductArray[i] * suffixProductArray[i];
        }

        IO.println();
        for(int i: result){
            IO.print(i + " ");
        }
    }

}
