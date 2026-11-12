package ProductOfArrayExceptSelf;

import java.util.Scanner;

public class BruteApproach {
    void main() {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int[] nums = new int[n];
        
        for (int i = 0; i < n; i++) {
            nums[i] = scn.nextInt();
        }

        int[] result =  new int[n];

        for(int i = 0;i<nums.length;i++){
            int prod = 1;

            for(int j=0;j<nums.length;j++){
                if(i==j){
                    continue;
                }
                else{
                    prod*=nums[j];
                }

                result[i] = prod;
            }
        }

        for(int i : result){
            IO.print(i + " ");
        }

    }
}
