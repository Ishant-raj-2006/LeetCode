
class Solution {
    private static final int MOD = 1_000_000_007;

    private int[][] grid;
    private int m, n;
    private int[][][] memo;

    private int solve(int i, int j, int dir) {
        if (memo[i][j][dir] != -2) {
            return memo[i][j][dir];
        }

        int x = i + (dir == 1 ? 1 : 0);
        int y = j + (dir == 0 ? 1 : 0);

        // Outside the grid
        if (x >= m || y >= n) {
            return memo[i][j][dir] = -1;
        }

        // Empty cell: destination reached
        if (grid[x][y] == 0) {
            return memo[i][j][dir] = x * n + y + 1;
        }

        // Mirror: change direction and continue
        return memo[i][j][dir] = solve(x, y, 1 - dir);
    }

    public int uniquePaths(int[][] grid) {
        this.grid = grid;
        m = grid.length;
        n = grid[0].length;

        memo = new int[m][n][2];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                memo[i][j][0] = -2;
                memo[i][j][1] = -2;
            }
        }

        int[][] dp = new int[m][n];
        dp[0][0] = 1;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (dp[i][j] == 0) {
                    continue;
                }

                // Try moving right or down
                for (int dir = 0; dir < 2; dir++) {
                    int target = solve(i, j, dir);

                    if (target != -1) {
                        int index = target - 1;
                        int x = index / n;
                        int y = index % n;

                        dp[x][y] = (int) (
                            (dp[x][y] + (long) dp[i][j]) % MOD
                        );
                    }
                }
            }
        }

        return dp[m - 1][n - 1];
    }
}
