class Solution {
    public List<Boolean> palindromePath(int n, int[][] edges, String s, String[] queries) {
        List<Integer>[] graph = new ArrayList[n];
        for(int i=0; i<n; i++){
            graph[i] = new ArrayList<>();
        }
        
        for(int[] e:edges){
            graph[e[0]].add(e[1]);
            graph[e[1]].add(e[0]);
        }
        int LOG = 1;
        while((1<<LOG) <= n) LOG++;
        int[][] up = new int[LOG][n];
        int[] parent = new int[n];
        int[] depth = new int[n];
        int[] tin = new int[n];
        int[] tout = new int[n];
        int[] size = new int[n];
        int[] prefix = new int[n];
        int[] order = new int[n];
        int[] stack = new int[n];

        Arrays.fill(parent, -1);
        char[] chars = s.toCharArray();
        int top = 0, count = 0, timer = 0;
        stack[top++] =0;
        parent[0] = 0;
        prefix[0] = 1<< (chars[0] - 'a');

        while(top > 0){
            int u = stack[--top];
            tin[u] = timer++;
            order[count++] = u;
            size[u] = 1;

            for(int v : graph[u]){
                if(v == parent[u]) continue;
                parent[v] =u;
                depth[v] = depth[u] +1;
                prefix[v] = prefix[u] ^ (1<< (chars[v] - 'a'));
                up[0][v] = u;
                stack[top++] = v;
            }
        }
        up[0][0] = 0;
        for(int k =1; k<LOG; k++){
            for(int u =0; u<n; u++){
                up[k][u] = up[k-1][up[k-1][u]];
            }
        }
        for(int i=n-1; i>0; i--){
            int u = order[i];
            size[parent[u]] += size[u];
        }
        for(int u=0; u<n; u++){
            tout[u] = tin[u] + size[u] -1;
        }
        Fenwick bit = new Fenwick(n);
        List<Boolean> ans = new ArrayList<>();
        for(String q: queries){
            String[] p = q.split(" ");
            if(p[0].equals("update")){
                int u = Integer.parseInt(p[1]);
                char c = p[2].charAt(0);
                int oldBit = 1 << (chars[u]-'a');
                int newBit = 1 << (c -'a');
                int delta = oldBit^newBit;

                if(delta !=0 ){
                    bit.update(tin[u], delta);
                    bit.update(tout[u] + 1,delta);
                    chars[u] = c;
                }
            }
            else{
                int u = Integer.parseInt(p[1]);
                int v = Integer.parseInt(p[2]);

                int a = lca(u,v,depth , up,LOG);
                int pu = prefix[u] ^ bit.query(tin[u]);
                int pv = prefix[v] ^ bit.query(tin[v]);
                int mask = pu ^ pv ^ (1 << (chars[a] - 'a'));
                ans.add((mask & (mask -1)) == 0 );
            }
        }
        return ans;
    }
    static class Fenwick {
        int n ;
        int[] bit;
        Fenwick(int n){
            this.n = n; 
            bit = new int[n+1];
        }
        void update (int index, int value){
            for(int i=index+1; i<=n; i+= i & -i){
                bit[i] ^=value;
            }
        }
        int query(int index){
            int result =0;
            for(int i = index +1; i>0; i-=i & -i){
                result ^= bit[i];
            }
            return result;
        }
    }
    private int lca(int u, int v, int[] depth, int[][] up , int LOG){
        if(depth[u] < depth[v]){
            int temp = u; 
            u = v; 
            v = temp;
        }
        int diff = depth[u] - depth[v];
        for(int k = 0; k<LOG; k++){
            if((diff & (1 << k)) !=0){
                u = up[k][u];
            }
        }
        if(u==v) return u; 
        // for(int k = LOG-1; k>=0; k--){
        //     if(up[k][u] != up[k][v]){
        //         u = up[k][v];
        //         v = up[k][v];
        //     }
        // }
        for(int k= LOG-1; k>=0; k--){
            if(up[k][u] != up[k][v]){
                u = up[k][u];
                v = up[k][v];
            }
        }
        return up[0][u];
    }
}