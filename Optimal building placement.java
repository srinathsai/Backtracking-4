
//Timecomplexity:- O((hw)pn*(hw)^n) [hwpn is permutations of building placement]
//space complexity:- O(hw).

public class Main {
    public static void main(String[] args) {
        BuildingPlacement bp=new BuildingPlacement();
        System.out.println(bp.findMinDistance(3,3,2));
    }
    public static class BuildingPlacement{
        int min=Integer.MAX_VALUE;
        
        public int findMinDistance(int h, int w, int n){
            int[][] grid=new int[h][w];
            for(int[] row:grid){
                Arrays.fill(row,-1);//initially grid is initiallized to -1 in all places.
            }
            backtracking(grid,0,0,h,w,n);
            
            return min;
        }
        private void bfs(int[][] grid,int h,int w){
            Queue<int[]> cache=new LinkedList<>();
            boolean[][] visited=new boolean[h][w];
            for(int i=0;i<h;i++){
                for(int j=0;j<w;j++){
                    if(grid[i][j]==0){
                        cache.add(new int[]{i,j});//position of the buildings are added to queues.
                        visited[i][j]=true;
                    }
                    
                }
            }
                int[][] dirs={{0,1},{1,0},{0,-1},{-1,0}};
                int level=0;
                while(!cache.isEmpty()){
                    int size=cache.size();
                    for(int x=0;x<size;x++){
                        int[] front=cache.poll();
                         int i1=front[0];
                         int j1=front[1];
                        for(int[] dir:dirs){
                            int r=i1+dir[0];
                            int c=j1+dir[1];
                            if(r>=0 && r<h && c>=0 && c<w && visited[r][c]==false){
                                cache.add(new int[]{r,c});
                                visited[r][c]=true;// adding bfs directed elements to the queue which will be next level.
                                }
                        }
                    }
                    level+=1; //after adding each level incrementing level.
                    
                }
                
                
                min=Math.min(min,level-1);
                
            
            
        }
        private void backtracking(int[][] grid,int r, int c, int h, int w,int n){
            if(n<=0){    // if all buildings are placed, then bfs function is called for calulating distance to far away stop.
                bfs(grid,h,w);
                return;
            }
            if(c>=w){
                r=r+1;
                c=0;
                
            }
            for(int i=r;i<h;i++){
                for(int j=c;j<w;j++){
                    grid[i][j]=0;// operation of backtracking that is placing building.
                    backtracking(grid,i,j,h,w,n-1);
                    grid[i][j]=-1;//undoing the operation.
                }
                c=0;
            }
            
            
        }
    }
}