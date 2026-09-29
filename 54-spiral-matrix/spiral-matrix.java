class Solution {
    public List<Integer> spiralOrder(int[][] mat) {
         ArrayList<Integer> ans = new ArrayList<>();
        if (mat == null || mat.length == 0) return ans;

        int m = mat.length;
        int n = mat[0].length;
        
        int firstRow = 0, lastRow = m - 1;
        int firstCol = 0, lastcol = n - 1;

        while (firstRow <= lastRow && firstCol <= lastcol) {
            // 1. Right Traverse
            for (int j = firstCol; j <= lastcol; j++) {
                ans.add(mat[firstRow][j]);
            }
            firstRow++; // Top boundary niche bajao

            // 2. Down Traverse
            for (int j = firstRow; j <= lastRow; j++) {
                ans.add(mat[j][lastcol]);
            }
            lastcol--; // Right boundary left khiskao

            // Check: Kya abhi bhi valid rows hain?
            if (firstRow > lastRow) break;

            // 3. Left Traverse
            for (int j = lastcol; j >= firstCol; j--) {
                ans.add(mat[lastRow][j]);
            }
            lastRow--; // Bottom boundary upar khiskao

            // Check: Kya abhi bhi valid columns hain?
            if (firstCol > lastcol) break;

            // 4. Up Traverse
            for (int j = lastRow; j >= firstRow; j--) {
                ans.add(mat[j][firstCol]);
            }
            firstCol++; // Left boundary right khiskao
        }

        return ans;
    }
}

    