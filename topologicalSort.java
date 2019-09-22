class Graph {
    Set<Integer>[] graph;
    public Graph(int size) {
        graph = new HashSet[size];
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new HashSet<>();
        }
    }

    public void addEdge(int node1, int node2) {
        graph[node1].add(node2);
    }

    public Set<Integer> adj(int node) {
        return graph[node];
    }

    public int size() {
        return graph.length;
    }
}

class DFS {
    Graph graph;
    ArrayDeque<Integer> stack;
    boolean cycle;
    boolean[] marked;
    boolean[] onStack;
    public DFS(Graph graph) {
        this.graph = graph;
        stack = new ArrayDeque();
        cycle = false;
        marked = new boolean[graph.size()];
        onStack = new boolean[graph.size()];
        for (int i = 0; i < graph.size(); i++) {
            if (cycle || marked[i]) continue;
            dfs(i, stack);
        }
    }

    private void dfs(int node, ArrayDeque<Integer> stack) {
        marked[node] = true;
        onStack[node] = true;
        for (int to : graph.adj(node)) {
            if (onStack[to]) {
                cycle = true;
                return;
            }
            if (marked[to]) continue;
            dfs(to, stack);
            if (cycle) return;
        }
        stack.push(node);
        onStack[node] = false;
    }

    public boolean cycle() {
        return cycle;
    }
    public List<Integer> topologicalSort() {
        return new ArrayList<>(stack);
    }
} 
