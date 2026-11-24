class Solution{
    public int maxLength(int[] nums){
     int n = nums.length;
     int ans = 0;
     for(int i=0; i<n; i++){
        long product = 1 ;
        long gcdVal = 0;
        long lcmVal = 1;
        for(int j = i; j<n; j++){
            product *= nums[j];
            gcdVal = gcd(gcdVal, nums[j]);
            lcmVal = lcm(lcmVal, nums[j]);

            if(product == gcdVal * lcmVal){
                ans = Math.max(ans, j -i +1);
            }
        }
     }
     return ans;
    }
    private long gcd(long a, long b){
        while(b != 0){
            long temp = b; 
            b = a % b;
            a = temp;
        }
        return a;
    }
    private long lcm(long a, long b){
        return (a / gcd(a,b)) * b;
    }
}