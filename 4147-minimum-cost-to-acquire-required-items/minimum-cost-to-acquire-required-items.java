class Solution {
    public long minimumCost(int cost1, int cost2, int costBoth, int need1, int need2) {
        long option1 = (long) need1 * cost1 + (long)need2*cost2;
        long option2 = (long) costBoth *Math.max(need1, need2);
        int minNeed = Math.min(need1 , need2);
        long option3 = (long) minNeed * costBoth + (long) (need1 -minNeed) *cost1 + (long) (need2 - minNeed) *cost2;
        return Math.min(option1, Math.min(option2, option3));
    }
}