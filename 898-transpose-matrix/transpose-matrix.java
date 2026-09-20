

class Solution {
    public int[][] transpose(int[][] arr) {
        int row = arr.length;
        int col = arr[0].length;
        int[][] brr = new int[col][row];
        
        for (int i = 0; i < col; i++) {
            for (int j = 0; j < row; j++) {
                brr[i][j] = arr[j][i];
            }
        }
        
        return brr;
    }
}