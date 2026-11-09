import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class containsDuplicateBrute {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();

        ArrayList<Integer> arr = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            arr.add(scn.nextInt());
        }

        Collections.sort(arr);

        boolean duplicateFlag = false;

        for (int i = 0; i < n - 1; i++) {
            if (arr.get(i).equals(arr.get(i + 1))) {
                duplicateFlag = true;
                break;
            }
        }

        if (duplicateFlag) {
            System.out.println("Duplicate element in array");
        } else {
            System.out.println("NO DUPLICATE");
        }

        scn.close();
    }
}
