
class Solution {
    public int uniquePaths(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        long MOD = 1_000_000_007L;

        long[][] right = new long[m][n];
        long[][] down = new long[m][n];

        right[0][0] = 1;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                long r = right[i][j];
                long d = down[i][j];

                if (i == m - 1 && j == n - 1) {
                    return (int) ((r + d) % MOD);
                }

                if (grid[i][j] == 0) {

                    
                    if (j + 1 < n) {
                        right[i][j + 1] =
                            (right[i][j + 1] + r + d) % MOD;
                    }

                    
                    if (i + 1 < m) {
                        down[i + 1][j] =
                            (down[i + 1][j] + r + d) % MOD;
                    }

                } else {

                    
                    if (i + 1 < m) {
                        down[i + 1][j] =
                            (down[i + 1][j] + r) % MOD;
                    }

                    
                    if (j + 1 < n) {
                        right[i][j + 1] =
                            (right[i][j + 1] + d) % MOD;
                    }
                }
            }
        }

        return 0;
    }
}
