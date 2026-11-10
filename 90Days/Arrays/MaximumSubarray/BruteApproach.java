package MaximumSubarray;

import java.util.Scanner;

public class BruteApproach {
    void main() {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int[] nums = new int[n];
        
        for (int i = 0; i < n; i++) {
            nums[i] = scn.nextInt();
        }

        
        int max_sum = Integer.MIN_VALUE;

       for(int i =0;i<nums.length;i++){
        int sum = 0;

        for(int j=i;j<nums.length;j++){
            sum+=nums[j];
            max_sum = Math.max(sum, max_sum);
        }
       }

       IO.print(max_sum);

    }
}
