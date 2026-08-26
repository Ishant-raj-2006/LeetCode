class Solution {
    public int removeDuplicates(int[] nums) {
      int uni=0;
      for(int i=1; i<nums.length; i++){
        if(nums[i] != nums[uni]){
            uni ++;
            nums[uni] = nums[i];
        }
      }  
      return uni +1;
    }
}