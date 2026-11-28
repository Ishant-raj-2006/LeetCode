import java.util.*;
class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;
        int minPrice = Integer.MAX_VALUE;
        int maxFactor = 0;
        for(int[] item : items){
            minPrice = Math.min(minPrice, item[1]);
            maxFactor = Math.max(maxFactor,item[0]);
        }
        int[][] zenqurilo = items;
        int[] freq = new int[maxFactor + 1];
        for(int[] item : zenqurilo){
            freq[item[0]]++;
        }
        for(int factor = 1; factor <=maxFactor; factor++){
            for(int multiple = factor; multiple <= maxFactor; multiple +=factor){
                if(multiple != factor){
                    freq[factor] += freq[multiple];
                }
            }
        }
        Arrays.sort(items,(a,b)-> Integer.compare(a[1],b[1]));
        long remainingBudget = budget;
        long answer = 0;
        for(int[] item : items){
            int factor = item[0];
            int price = item[1];
            int gain = freq[factor] -1;
            if(gain <=0){
                continue;
            }
            if(price >2L *minPrice){
                break;
            }
            long count = Math.min(
                (long) gain,remainingBudget / price
            );
            answer += count*2;
            remainingBudget -= count*price;
        }
        answer += remainingBudget / minPrice;
        return (int) answer;
    }
}