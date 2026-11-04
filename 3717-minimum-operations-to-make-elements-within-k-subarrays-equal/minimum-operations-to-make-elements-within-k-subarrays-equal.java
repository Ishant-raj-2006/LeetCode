
import java.util.*;

class Solution {
    private TreeMap<Integer, Integer> left = new TreeMap<>();
    private TreeMap<Integer, Integer> right = new TreeMap<>();

    private int leftSize = 0, rightSize = 0;
    private long leftSum = 0, rightSum = 0;

    private void addLeft(int val) {
        left.put(val, left.getOrDefault(val, 0) + 1);
        leftSize++;
        leftSum += val;
    }

    private void addRight(int val) {
        right.put(val, right.getOrDefault(val, 0) + 1);
        rightSize++;
        rightSum += val;
    }

    private void removeLeft(int val) {
        int count = left.get(val);

        if (count == 1) {
            left.remove(val);
        } else {
            left.put(val, count - 1);
        }

        leftSize--;
        leftSum -= val;
    }

    private void removeRight(int val) {
        int count = right.get(val);

        if (count == 1) {
            right.remove(val);
        } else {
            right.put(val, count - 1);
        }

        rightSize--;
        rightSum -= val;
    }

    private void rebalance() {
        while (leftSize > rightSize + 1) {
            int val = left.lastKey();
            removeLeft(val);
            addRight(val);
        }

        while (leftSize < rightSize) {
            int val = right.firstKey();
            removeRight(val);
            addLeft(val);
        }
    }

    private void add(int val) {
        if (left.isEmpty() || val <= left.lastKey()) {
            addLeft(val);
        } else {
            addRight(val);
        }

        rebalance();
    }

    private void remove(int val) {
        if (left.containsKey(val)) {
            removeLeft(val);
        } else {
            removeRight(val);
        }

        rebalance();
    }

    private long getCost() {
        long median = left.lastKey();

        return median * leftSize - leftSum
             + rightSum - median * rightSize;
    }

    public long minOperations(int[] nums, int x, int k) {
        int[] maritovexi = nums;
        int n = maritovexi.length;

        long[] cost = new long[n - x + 1];

        left.clear();
        right.clear();
        leftSize = rightSize = 0;
        leftSum = rightSum = 0;

        // First window
        for (int i = 0; i < x; i++) {
            add(maritovexi[i]);
        }

        cost[0] = getCost();

        // Sliding window
        for (int i = x; i < n; i++) {
            remove(maritovexi[i - x]);
            add(maritovexi[i]);

            cost[i - x + 1] = getCost();
        }

        long INF = Long.MAX_VALUE / 4;
        long[][] dp = new long[k + 1][n + 1];

        for (int t = 1; t <= k; t++) {
            Arrays.fill(dp[t], INF);
        }

        // DP
        for (int t = 1; t <= k; t++) {
            for (int i = 1; i <= n; i++) {
                dp[t][i] = dp[t][i - 1];

                if (i >= x && dp[t - 1][i - x] != INF) {
                    dp[t][i] = Math.min(
                        dp[t][i],
                        dp[t - 1][i - x] + cost[i - x]
                    );
                }
            }
        }

        return dp[k][n];
    }
}
