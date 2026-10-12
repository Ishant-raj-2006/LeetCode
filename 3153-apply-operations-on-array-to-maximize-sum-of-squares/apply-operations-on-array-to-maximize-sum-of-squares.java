class Solution {
    public int maxSum(List<Integer> nums, int k) {
      int MOD =1_000_000_007;
      int[] bits = new int[31];

      for(int num : nums){
        for(int i=0; i<31; i++){
            if((num & (1 << i)) !=0){
                bits[i]++;
            }
        }
      }  
      long ans =0;
      for(int j=0; j<k; j++){
        long x = 0;

        for(int i=0; i<31; i++){
            if(bits[i] > 0){
                x |=(1L << i);
                bits[i]--;
            }
        }
        ans = (ans + x*x) % MOD;
      }
      return (int) ans;
    }
}