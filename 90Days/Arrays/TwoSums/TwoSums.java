public static void main(String[] args){
    Scanner scn = new Scanner(System.in);
    int n = scn.nextInt();

    ArrayList<Integer> arr = new ArrayList<>();
    for(int i =0 ;i<n;i++){
        arr.add(scn.nextInt());
    }

    int target = scn.nextInt();

    int[] result = new int[2];

    for(int i=0;i< arr.size(); i++){
        for(int j= i+1 ; j<arr.size(); j++){
            if(arr.get(i)+arr.get(j) == target){
                result[0]=i;
                result[1] = j;
            }
        }
    }

    for(int i : result){
        IO.println(i);
    }

}