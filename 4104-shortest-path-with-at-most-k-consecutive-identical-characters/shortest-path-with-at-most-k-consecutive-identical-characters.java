import java.util.*;
class Solution {
    public int shortestPath(int n, int[][] edges, String labels, int k) {
      List<int[]>[] graph = new ArrayList[n];
      for(int i=0; i<n; i++){
        graph[i] = new ArrayList<>();
      }  
      for(int[] e : edges ){
        graph[e[0]].add(new int[]{e[1],e[2]});
      }
      long[][] dist = new long[n][k+1];
      for(long[] row: dist){
        Arrays.fill(row, Long.MAX_VALUE);
      }
      PriorityQueue<long[]> pq = new PriorityQueue<>(
        (a,b) -> Long.compare(a[0], b[0])
      );
      dist[0][1] = 0; 
      pq.offer(new long[]{0,0,1});
      while(!pq.isEmpty()){
        long[] curr = pq.poll();
        long cost = curr[0];
        int u = (int) curr[1];
        int count = (int) curr[2];
        if(cost != dist[u][count]){
            continue;
        }
        if(u == n-1){
            return (int) cost;
        }
        for(int[] edge : graph[u]){
            int v = edge[0];
            int weight = edge[1];

            int nextCount;
            if(labels.charAt(u) == labels.charAt(v)){
                nextCount = count + 1;
            }
            else{
                nextCount = 1;
            }
            if(nextCount > k){
                continue;
            }
            long nextCost = cost + weight;
            if(nextCost < dist[v][nextCount]){
                dist[v][nextCount] = nextCost;
                pq.offer(new long[] {
                    nextCost, v , nextCount
                });
            }
        }
      }
      return -1;
    }
}