class Solution {
    public int[][] matrixReshape(int[][] mat, int r, int c) {
     int row=mat.length;
     int col=mat[0].length;

     if(row * col != r*c){
        return mat;
     }
     int[][] ans= new int[r][c];
     int index=0;
     for(int i=0;i<row;i++){
        for(int j=0;j<col;j++){
            ans[index/c][index%c]=mat[i][j];
            index++;
        }
     }
     return ans;
    }
}