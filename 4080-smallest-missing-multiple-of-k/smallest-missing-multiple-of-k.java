class Solution {
    public int missingMultiple(int[] nums, int k) {
     int multiple = k;
     while(true){
        boolean found = false;
        for(int i=0; i<nums.length; i++){
            if(nums[i] == multiple){
                found = true;
                break;
            }
        }
        if(!found){
            return multiple;
        }
        multiple +=k;
     }   
    }
}