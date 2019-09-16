//This is a undirected graph with dfs algorithm that can find the bridge and cycles in the graph 
//(Tarjan's algorithm)
//https://www.youtube.com/watch?v=TyWtx7q2D7Y
//https://www.youtube.com/watch?v=aZXi1unBdJA

class UnDirGraph {
    List<Integer>[] graph;
    public UnDirGraph(int size) {
        graph = new ArrayList[size];
        for (int i = 0; i < size; i++) {
            graph[i] = new ArrayList<>();
        }
    }

    public void addEdge(int from, int to) {
        graph[from].add(to);
        graph[to].add(from);
    }

    public List<Integer> adj(int node) {
        return graph[node];
    }

    public int size() {
        return graph.length;
    }
}

class DFS {
    UnDirGraph graph;
    List<int[]> bridges; 
    List<int[]> edgesInCycles;
    Deque<Integer> stack;
    boolean[] onStack;
    int[] ids;
    int[] low;
    int[] edges;
    int id;
    int cycles;
    public DFS(UnDirGraph graph) {
        this.graph = graph;
        ids = new int[graph.size()]; Arrays.fill(ids, -1);
        low = new int[graph.size()];
        edges = new int[graph.size()];
        onStack = new boolean[graph.size()];
        bridges = new ArrayList<>();
        stack = new ArrayDeque<>();
        edgesInCycles = new ArrayList<>();
        id = 0;
        dfs(0);
    }

    private void dfs(int at) {
        ids[at] = low[at] = id++;
        stack.push(at);
        onStack[at] = true;
        for (int to : graph.adj(at)) {
            if (to == edges[at]) continue;
            if (ids[to] == -1) {
                edges[to] = at;
                dfs(to);
                low[at] = Math.min(low[at], low[to]);
                if (ids[at] < low[to]) {
                    bridges.add(new int[]{at, to});
                }
            } else {
                low[at] = Math.min(low[at], ids[to]);
            }
        }
        if (ids[at] == low[at]) {
            int node = stack.pop();
            if (at == node) return;
            edgesInCycles.add(new int[]{at, node});
            while (node != at) {
                onStack[node] = false;
                low[node] = ids[at];
                edgesInCycles.add(new int[]{edges[node], node});
                node = edges[node];
            }
            cycles++;
        }
    }
}
