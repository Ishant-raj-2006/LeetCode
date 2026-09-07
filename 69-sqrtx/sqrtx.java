class Solution {
    public int mySqrt(int x) {
        // Simple trick: Use 'long' to completely avoid complicated math overflow bugs
        long start = 0;
        long end = x;
        long ans = 0;

        while (start <= end) {
            long mid = start + (end - start) / 2;

            if (mid * mid == x) {
                return (int) mid; // Found the exact square root!
            } else if (mid * mid < x) {
                ans = mid;        // This could be the answer (rounded down)
                start = mid + 1;  // Try to find a bigger number
            } else {
                end = mid - 1;    // The number is too big, look lower
            }
        }

        return (int) ans;
    }
}
