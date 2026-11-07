import java.util.*;

void main() {
    Scanner scn = new Scanner(System.in);

    int n = scn.nextInt();

    int[][] arr = new int[n][2];

    for (int i = 0; i < n; i++) {
        arr[i][0] = scn.nextInt(); // Value
        arr[i][1] = i;             // Original index
    }

    Arrays.sort(arr, (a, b) -> Integer.compare(a[0], b[0]));

    int target = scn.nextInt();

    int[] result = new int[2];
    boolean found = false;

    int p1 = 0;
    int p2 = n - 1;

    while (p1 < p2) {
        int sum = arr[p1][0] + arr[p2][0];

        if (sum == target) {
            result[0] = arr[p1][1]; // Original index
            result[1] = arr[p2][1]; // Original index
            found = true;
            break;
        } else if (sum < target) {
            p1++;
        } else {
            p2--;
        }
    }

    if (found) {
        IO.println(result[0]);
        IO.println(result[1]);
    } else {
        IO.println("No pair found");
    }
}