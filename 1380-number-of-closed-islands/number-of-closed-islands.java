class Solution {
    public int closedIsland(int[][] grid) {
     int m = grid.length;
     int n = grid[0].length;
     for(int i=0; i<m; i++){
        dfs(grid,i,0);
        dfs(grid,i,n-1);
     }   
     for(int j=0; j<n; j++){
        dfs(grid , 0 ,j);
        dfs(grid , m-1,j);
     }
     int count =0;
     for(int i=1; i<m-1; i++){
        for(int j=1; j<n-1; j++){
            if(grid[i][j] ==0){
                count++;
                dfs(grid , i ,j);
            }
        }
     }
     return count;
    }
    private void dfs(int[][] grid , int i, int j){
        int m = grid.length;
        int n = grid[0].length;
        if(i<0 || i>=m || j<0 || j>=n || grid[i][j]==1){
            return;
        }
        grid[i][j] = 1;
        dfs(grid , i+1 , j);
        dfs(grid , i-1 , j);
        dfs(grid , i , j+1);
        dfs(grid , i , j-1);
    }
}