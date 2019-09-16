//This is a directed graph with dfs algorithm that can find the bridge and strongly connected components in the graph 
//The node itself is counted as one of those components
//(Tarjan's algorithm)
//https://www.youtube.com/watch?v=TyWtx7q2D7Y
//https://www.youtube.com/watch?v=aZXi1unBdJA

class Graph {
    List<Integer>[] graph;
    public Graph(int size) {
        graph = new ArrayList[size];
        for (int i = 0; i < size; i++) {
            graph[i] = new ArrayList<>();
        }
    }

    public void addEdge(int from, int to) {
        graph[from].add(to);
    }

    public List<Integer> adj(int node) {
        return graph[node];
    }

    public int size() {
        return graph.length;
    }
}

class DFS {
    Graph graph;
    List<int[]> bridges; 
    List<int[]> edgesInCycles;
    Deque<Integer> stack;
    boolean[] onStack;
    int[] ids;
    int[] low;
    int[] edges;
    int id;
    int sccCount;
    public DFS(Graph graph) {
        this.graph = graph;
        ids = new int[graph.size()]; Arrays.fill(ids, -1);
        low = new int[graph.size()];
        edges = new int[graph.size()];
        onStack = new boolean[graph.size()];
        bridges = new ArrayList<>();
        stack = new ArrayDeque<>();
        edgesInCycles = new ArrayList<>();
        id = 0;
        for (int i = 0; i < graph.size(); i++) {
            if (ids[i] == -1) {
                dfs(0);
            }
        }
    }

    private void dfs(int at) {
        stack.push(at);
        onStack[at] = true;
        ids[at] = low[at] = id++;
        for (int to : graph.adj(at)) {
            if (to == edges[at]) continue;
            if (ids[to] == -1) {
                edges[to] = at;
                dfs(to);
                low[at] = Math.min(low[at], low[to]);
                if (ids[at] < low[to]) {
                    bridges.add(new int[]{at, to});
                }
            } 
            if (onStack[to]) {
                low[at] = Math.min(low[at], ids[to]);
            }
        }
        if (ids[at] == low[at]) {
            int node = stack.pop();
            edgesInCycles.add(new int[]{node, at});
            while (node != at) {
                onStack[node] = false;
                low[node] = ids[at];
                edgesInCycles.add(new int[]{edges[node], node});
                node = edges[node];
            }
            sccCount++;
        }
    }
}
