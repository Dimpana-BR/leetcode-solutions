class Solution {
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int originalcolor = image[sr][sc];
        if(originalcolor == color){
            return image;
        }
        dfs(image,sr,sc,originalcolor,color);
        return image;
    }
    public void dfs(int[][] image,int sr,int sc,int originalcolor,int color){
        if(sr<0 || sc<0 || sr>= image.length || sc >= image[0].length|| image[sr][sc]!= originalcolor){
            return;
        }
        image[sr][sc]=color;
        dfs(image,sr+1,sc,originalcolor,color);
        dfs(image,sr-1,sc,originalcolor,color);
        dfs(image,sr,sc+1,originalcolor,color);
        dfs(image,sr,sc-1,originalcolor,color);
    }
}