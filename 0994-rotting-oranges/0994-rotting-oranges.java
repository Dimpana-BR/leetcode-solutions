class Solution {
    public int orangesRotting(int[][] grid) {
        Queue<int[]> queue = new LinkedList<>();
        int fresh=0;
        int minutes=0;
        for(int r=0;r<grid.length;r++){
            for(int c=0;c<grid[0].length;c++){
                if(grid[r][c]==2){
                    queue.offer(new int[]{r,c});
                }
                if(grid[r][c]==1){
                    fresh++;
                }
            }
        }
        if(fresh==0){
            return 0;
        }
        int[][] directions={
            {-1,0},
            {1,0},
            {0,-1},
            {0,1}
        };
        while(!queue.isEmpty()){
            int size=queue.size();
            for(int i=0;i<size;i++){
                int[] current=queue.poll();
                int row=current[0];
                int col=current[1];
                for(int[] dir:directions){
                    int newrow = row + dir[0];
                    int newcol = col + dir[1];
                    if(newrow >=0 && newrow< grid.length && newcol>=0 && newcol < grid[0].length && grid[newrow][newcol]==1){
                        grid[newrow][newcol]=2;
                        fresh--;
                        queue.offer(new int[]{newrow,newcol});
                    }
                }
            }
            if(!queue.isEmpty()){
                minutes++;
            }
        }
        if(fresh==0){
            return minutes;
        }else{
            return -1;
        }
    }
}