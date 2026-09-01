class Solution {
    public boolean check(int[] nums) {
        
        int j = 0;

        for(int i = 0; i < nums.length; i++) {
            
            if(nums[i] > nums[(i + 1) % nums.length]) {
                j++;
            }
        }

        if(j <= 1) {
            return true;
        }
        else {
            return false;
        }
    }
}