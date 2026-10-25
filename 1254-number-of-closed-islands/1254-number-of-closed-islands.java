class Solution {
void dfs(int[][] grid, int i, int j){
    if(i<0||j<0||i>=grid.length||j>=grid[0].length||grid[i][j]==1){
        return;
    }
    grid[i][j]=1;

    dfs(grid,i-1,j);
    dfs(grid,i,j-1);
    dfs(grid,i+1,j);
    dfs(grid,i,j+1);


}

    public int closedIsland(int[][] grid) {

        int m=grid.length;
        int n=grid[0].length;

        for(int j=0;j<n;j++){
            if(grid[0][j]==0){
                dfs(grid,0,j);
            }

            if(grid[m-1][j]==0){
                dfs(grid,m-1,j);
            }
        }

for(int i=0;i<m;i++){
    if(grid[i][0]==0){
        dfs(grid,i,0);
    }

    if(grid[i][n-1]==0){
        dfs(grid,i,n-1);
    }
}

       int count=0;
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(grid[i][j]==0){
                   count++;
                    dfs(grid,i,j);
                }
            }
        }
        return count;
    }
}