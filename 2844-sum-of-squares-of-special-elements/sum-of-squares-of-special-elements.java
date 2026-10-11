class Solution {
    public int sumOfSquares(int[] nums) {
        int index = 0;
        int n = nums.length;

        // i = index, counting from 0  ->  position = i + 1
        for (int i = 0; i < nums.length; i++) {
            if (n % (i + 1) == 0) {
                index += nums[i] * nums[i];
            }
        }

        return index;
    }
}