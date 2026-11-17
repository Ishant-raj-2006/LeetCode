class Solution {
    public int minimumChanges(String s, int k) {
       int n = s.length();
       int[][] cost = new int[n][n];
       for(int len =2; len <=n; len++){
        for(int i=0; i + len <=n; i++){
            int j = i + len -1;
            cost[i][j] = getCost(s,i,j);
        }
       } 
       int INF = 1000000;
       int[][] dp = new int[k +1][n+1];
       for(int[] row : dp){
        java.util.Arrays.fill(row,INF);
       }
       dp[0][0] =0;
       for(int parts = 1; parts <=k; parts++){
        // for(int i=1; i<=n; i++){
        for(int i=2*parts; i<=n; i++){
            // for(int j = parts-1; j<i; j++){
            for(int j =2*(parts - 1); j<=i - 2; j++){
                if(i-j >=2){
                    dp[parts][i] = Math.min(
                        dp[parts][i],
                    dp[parts -1][j] + cost[j][i-1]
                    );
                }
            }
        }
       }
       return dp[k][n];
    }
    private int getCost(String s, int l , int r){
        int len = r -l + 1;
        int ans = Integer.MAX_VALUE;
        for(int d=1; d<len; d++){
            if(len % d != 0) continue;
            int changes = 0;
            for(int i=0; i<d; i++){
                int left = l +i;
                // int right = r - ((len -1 -i)/d)*d;
                int right = l+i+(len / d -1) *d;
                while(left < right){
                    if(s.charAt(left) != s.charAt(right)){
                        changes++;
                    }
                    left += d;
                    right -= d;
                }
            }
            ans = Math.min(ans , changes);
        }
        return ans;
    }
}