class Solution {
    public int countIslands(int[][] grid, int k) {
        int m = grid.length;
        int n=grid[0].length;
        int count = 0;
        int[] dx ={-1,1,0,0};
        int[] dy ={0,0,-1,1};
        int[] stack = new int[m*n];
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(grid[i][j] == 0){
                    continue;
                }
                int top =0;
                long sum = grid[i][j];
                grid[i][j] = 0;
                stack[top++] = i*n+j;
                while(top >0){
                    int cell = stack [--top];
                    int x = cell/n;
                    int y = cell %n;
                    for(int d=0; d<4; d++){
                        int nx = x+dx[d];
                        int ny = y+dy[d];
                        if(nx >=0 && nx <m && ny >= 0 && ny <n && grid[nx][ny]>0){
                            sum +=grid[nx][ny];
                            grid[nx][ny] =0;
                            stack[top++] = nx*n+ny;
                        }
                    }
                }
                if(sum %k == 0){
                    count++;
                }
            }
        }
        return count;
    }
}