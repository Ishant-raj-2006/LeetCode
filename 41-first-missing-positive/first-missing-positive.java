class Solution {
    public int firstMissingPositive(int[] nums) {
        int [] freq = new int[nums.length+1];
        for(int i:nums){
            if(i>0 && i<=nums.length){
                freq[i] =1;
            }
        }
        for(int i=1; i<=nums.length; i++){
            if(freq[i] ==0){
                return i;
            }
        }
        return nums.length+1;
    }
}