import java.util.*;

class Solution {
    static final long MOD1 = 1_000_000_007L;
    static final long MOD2 = 998_244_353L;
    static final long BASE = 1_000_000_009L;
    static final long OFFSET = 1_000_000_007L;

    long[] h1, h2, p1, p2;

    long getHash(long[] h, long[] p, int l, int r, long mod) {
        long term = (h[l] * p[r - l]) % mod;
        long res = (h[r] - term) % mod;
        if (res < 0) res += mod;
        return res;
    }

    boolean check(int len, int n) {
        HashMap<Long, Integer> map = new HashMap<>();
        for (int i = len; i <= n; i++) {
            long a = getHash(h1, p1, i - len, i, MOD1);
            long b = getHash(h2, p2, i - len, i, MOD2);
            long key = (a << 32) | (b & 0xffffffffL);
            map.put(key, map.getOrDefault(key, 0) + 1);
        }
        for (int count : map.values()) {
            if (count == 1) return true;
        }
        return false;
    }

    public int smallestUniqueSubarray(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;

        h1 = new long[n + 1];
        h2 = new long[n + 1];
        p1 = new long[n + 1];
        p2 = new long[n + 1];

        p1[0] = 1L;
        p2[0] = 1L;

        for (int i = 0; i < n; i++) {
            long val1 = (nums[i] + OFFSET) % MOD1;
            long val2 = (nums[i] + OFFSET) % MOD2;
            if (val1 < 0) val1 += MOD1;
            if (val2 < 0) val2 += MOD2;

            p1[i + 1] = (p1[i] * BASE) % MOD1;
            p2[i + 1] = (p2[i] * BASE) % MOD2;

            h1[i + 1] = (h1[i] * BASE + val1) % MOD1;
            h2[i + 1] = (h2[i] * BASE + val2) % MOD2;
        }

        int low = 1, high = n, ans = n;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (check(mid, n)) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }
}