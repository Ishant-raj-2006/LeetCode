class Solution {
    public int sumCounts(List<Integer> nums) {
       int n = nums.size();
       int sum =0;
       for(int i=0; i<n; i++){
        HashSet<Integer> set = new HashSet<>();
        for(int j=i; j<n; j++){
            set.add(nums.get(j));
            int count = set.size();
            sum += count*count;
        }
       } 
       return sum;
    }
}