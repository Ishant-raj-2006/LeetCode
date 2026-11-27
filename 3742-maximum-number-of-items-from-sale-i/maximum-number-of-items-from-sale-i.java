class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;
        int[] freeCount = new int[n];
        int cheapest = Integer.MAX_VALUE;
        for(int i=0; i<n; i++){
            cheapest = Math.min(cheapest, items[i][1]);
            // for(int j=0; i<n; j++){
            for(int j=0; j<n; j++){
                if(i != j && items[j][0] % items[i][0] == 0){
                    freeCount[i]++;
                }
            }
        }
        int[] dp = new int[budget + 1];
        Arrays.fill(dp,-1);
        dp[0] = 0;
        for(int i=0; i<n; i++){
            int price = items[i][1];
            int value = freeCount[i]+1;
            for(int b = budget; b>= price; b--){
                if(dp[b-price] !=-1){
                    dp[b] = Math.max(
                        dp[b],
                        dp[b - price ] + value
                    );
                }
            }
        }
        int answer = 0;
        for(int b =0; b<=budget; b++){
            if(dp[b] != -1){
                int total = dp[b] + (budget -b) / cheapest;
                answer = Math.max(answer,total);
            }
        }
        return answer;
    }
}