import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class TwoSumsViaHashMap {
    void main() {
       Scanner scn = new Scanner(System.in);
       int n = scn.nextInt();

       HashMap<Integer,Integer> hashMap = new HashMap<>();

       for(int i=0;i<n;i++){
            hashMap.put(i,scn.nextInt());
       }

       int target = scn.nextInt();

       hashMap = hashMap.entrySet().stream()
       .sorted(Map.Entry.comparingByValue()).collect
       (Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, 
        (oldVal,newVal) -> oldVal,
        LinkedHashMap::new));
    List<Integer> keys = new ArrayList<>(hashMap.keySet());

    int p1 = 0;
    int p2 = keys.size() - 1;

    while (p1 < p2) {

        int key1 = keys.get(p1);
        int key2 = keys.get(p2);

        int sum = hashMap.get(key1) + hashMap.get(key2);

        if (sum == target) {
            IO.print(key1 + ", " + key2);
            break;
        } else if (sum < target) {
            p1++;
        } else {
            p2--;
        }
    }

    }
}
