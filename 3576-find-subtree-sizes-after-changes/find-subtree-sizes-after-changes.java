import java.util.*;
class Solution{
    public int[] findSubtreeSizes(int[] parent,String s){
        int n = parent.length;
        ArrayList<Integer>[] adj = new ArrayList[n];
        for(int i=0; i<n; i++){
            adj[i] = new ArrayList<>();
        }
        for(int i=1; i<n; i++){
            adj[parent[i]].add(i);
        }
        int[] newParent = parent.clone();
        int[] last = new int[26];
        Arrays.fill(last, -1);
        int[] previous = new int[n];
        int[] order = new int[n];
        int[] stack = new int[2*n];
        int top = 0;
        int count = 0;
        stack[top++] = 0;
        while(top>0){
            int event = stack[--top];
            if(event >= 0){
                int node = event;
                int ch = s.charAt(node) -'a';
                order[count++] = node;
                previous[node] = last[ch];
                if(node !=0 && last[ch] != -1){
                    newParent[node] = last[ch];
                }
                last[ch] = node;
                stack[top++] = -node-1;
                for(int i=adj[node].size()-1; i>=0; i--){
                    stack[top++] = adj[node].get(i);
                }
            }
            else{
                int node = -event -1;
                int ch = s.charAt(node) - 'a';
                last[ch] = previous[node];
            }
        }
        int[] ans = new int[n];
        Arrays.fill(ans,1);
        for(int i=n-1; i>0; i--){
            ans[newParent[order[i]]] += ans[order[i]];
        }
        return ans;
    }
}