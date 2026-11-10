package MaximumSubarray;

import java.util.Scanner;

public class kadaneAlgorithm {

    void main(){
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int[] nums = new int[n];

        for (int i = 0; i < n; i++) {
            nums[i] = scn.nextInt();
        }

        int max_sum = Integer.MIN_VALUE;
        int current_sum = 0;

        for(int i =0;i<nums.length;i++){
            current_sum = Math.max(nums[i], current_sum+nums[i]);
            max_sum = Math.max(current_sum, max_sum);
        }


        IO.println(max_sum);
    }
}
