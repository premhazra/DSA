// Problem: Maximize Spanning Tree Stability with Upgrades
// Difficulty: Hard
// Source: LeetCode
// Language: Java
// Synced by CodexSync
class Solution {

    class DSU {
        int[] parent, rank;

        DSU(int n){
            parent = new int[n];
            rank = new int[n];
            for(int i=0;i<n;i++) parent[i]=i;
        }

        int find(int x){
            if(parent[x]!=x)
                parent[x]=find(parent[x]);
            return parent[x];
        }

        boolean union(int a,int b){
            int pa=find(a), pb=find(b);
            if(pa==pb) return false;

            if(rank[pa]<rank[pb])
                parent[pa]=pb;
            else if(rank[pb]<rank[pa])
                parent[pb]=pa;
            else{
                parent[pb]=pa;
                rank[pa]++;
            }
            return true;
        }
    }

    public int maxStability(int n, int[][] edges, int k) {

        int low = 1, high = 200000, ans = -1;

        while(low <= high){

            int mid = (low + high) / 2;

            if(can(n, edges, k, mid)){
                ans = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return ans;
    }

    boolean can(int n, int[][] edges, int k, int x){

        DSU dsu = new DSU(n);
        int used = 0;

        // mandatory edges first
        for(int[] e : edges){

            int u = e[0], v = e[1], s = e[2], must = e[3];

            if(must == 1){

                if(s < x) return false;

                if(!dsu.union(u,v))
                    return false;

                used++;
            }
        }

        int upgrades = 0;

        for(int[] e : edges){

            int u = e[0], v = e[1], s = e[2], must = e[3];

            if(must == 0){

                if(dsu.find(u) == dsu.find(v))
                    continue;

                if(s >= x){
                    dsu.union(u,v);
                    used++;
                }
                else if(2*s >= x && upgrades < k){
                    upgrades++;
                    dsu.union(u,v);
                    used++;
                }
            }
        }

        return used == n-1;
    }
}