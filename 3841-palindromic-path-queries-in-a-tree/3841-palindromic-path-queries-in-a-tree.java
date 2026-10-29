class Solution {
    private List<Integer>[] adj;
    private int[][] up;
    private int[] depth, tin, tout, parent;
    private int[] basePrefix;
    private int timer;
    private Fenwick fenwick;

    public List<Boolean> palindromePath(
            int n, int[][] edges, String s, String[] queries) {

        adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int[] e : edges) {
            adj[e[0]].add(e[1]);
            adj[e[1]].add(e[0]);
        }

        int log = 1;
        while ((1 << log) <= n) log++;

        up = new int[log][n];
        depth = new int[n];
        tin = new int[n];
        tout = new int[n];
        parent = new int[n];
        basePrefix = new int[n];

        Arrays.fill(parent, -1);

        
        int[] stack = new int[n];
        int[] next = new int[n];
        int top = 0;

        stack[top++] = 0;
        parent[0] = 0;
        timer = 0;

        while (top > 0) {
            int u = stack[top - 1];

            if (next[u] == 0) {
                tin[u] = timer++;
                basePrefix[u] =
                    (u == 0 ? 0 : basePrefix[parent[u]])
                    ^ (1 << (s.charAt(u) - 'a'));

                up[0][u] = parent[u];

                for (int k = 1; k < log; k++) {
                    up[k][u] = up[k - 1][up[k - 1][u]];
                }
            }

            if (next[u] < adj[u].size()) {
                int v = adj[u].get(next[u]++);

                if (v == parent[u]) continue;

                parent[v] = u;
                depth[v] = depth[u] + 1;
                stack[top++] = v;
            } else {
                tout[u] = timer - 1;
                top--;
            }
        }

        fenwick = new Fenwick(n);
        List<Boolean> answer = new ArrayList<>();

        for (String q : queries) {
            String[] p = q.split(" ");

            if (p[0].equals("update")) {
                int u = Integer.parseInt(p[1]);
                char c = p[2].charAt(0);

                int oldBit = getPrefix(u) ^ getPrefix(parent[u]);
                if (u == 0) oldBit = getPrefix(u);

                int newBit = 1 << (c - 'a');
                int delta = oldBit ^ newBit;

                if (delta != 0) {
                    fenwick.rangeXor(tin[u], tout[u], delta);
                }
            } else {
                int u = Integer.parseInt(p[1]);
                int v = Integer.parseInt(p[2]);
                int lca = findLCA(u, v);

                int mask = getPrefix(u) ^ getPrefix(v)
                         ^ currentNodeBit(lca);

                answer.add((mask & (mask - 1)) == 0);
            }
        }

        return answer;
    }

    private int getPrefix(int u) {
        return basePrefix[u] ^ fenwick.query(tin[u]);
    }

    private int currentNodeBit(int u) {
        int prefix = getPrefix(u);
        int parentPrefix = (u == 0) ? 0 : getPrefix(parent[u]);
        return prefix ^ parentPrefix;
    }

    private int findLCA(int u, int v) {
        if (depth[u] < depth[v]) {
            int t = u;
            u = v;
            v = t;
        }

        int diff = depth[u] - depth[v];

        for (int k = 0; k < up.length; k++) {
            if ((diff & (1 << k)) != 0) {
                u = up[k][u];
            }
        }

        if (u == v) return u;

        for (int k = up.length - 1; k >= 0; k--) {
            if (up[k][u] != up[k][v]) {
                u = up[k][u];
                v = up[k][v];
            }
        }

        return parent[u];
    }

    private static class Fenwick {
        int[] tree;
        int n;

        Fenwick(int n) {
            this.n = n;
            tree = new int[n + 2];
        }

        void update(int index, int value) {
            for (int i = index + 1; i <= n; i += i & -i) {
                tree[i] ^= value;
            }
        }

        int query(int index) {
            int result = 0;

            for (int i = index + 1; i > 0; i -= i & -i) {
                result ^= tree[i];
            }

            return result;
        }

        void rangeXor(int left, int right, int value) {
            update(left, value);

            if (right + 1 < n) {
                update(right + 1, value);
            }
        }
    }
}