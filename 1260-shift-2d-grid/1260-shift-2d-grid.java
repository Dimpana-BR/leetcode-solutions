class Solution {
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        int row=grid.length;
        int col=grid[0].length;

        List<Integer> list = new ArrayList<>();
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                list.add(grid[i][j]);
            }
        }
        for(int i=0;i<k;i++){
            int last=list.remove(list.size()-1);
            list.add(0,last);
        }
        List<List<Integer>> ans = new ArrayList<>();
        int index=0;
        for(int i=0;i<row;i++){
            List<Integer> currentrow = new ArrayList<>();
            for(int j=0;j<col;j++){
                currentrow.add(list.get(index));
                index++;
            }
            ans.add(currentrow);
        }
        return ans;
    }
}