//Untested Yet!!!!! May contain bugs
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
    Graph graph;
    int[] ids;
    int[] low;
    int[] edges;
    int id;
    List<List<Integer>> bridges;
    public DFS(Graph graph) {
        this.graph = graph;
        bridges = new ArrayList<>();
        ids = new int[graph.size()];
        low = new int[graph.size()];
        edges = new int[graph.size()];
        id = 0;
        Arrays.fill(ids, -1);
        dfs(0);
    }  
    private void dfs(int at) {
        ids[at] = low[at] = id++;
        for (int to : graph.adj(at)) {
           if (to == edges[at]) continue;
            if (ids[to] == -1) {
                edges[to] = at;
                dfs(to);
                low[at] = Math.min(low[at], low[to]);
                if (ids[at] < low[to]) bridges.add(new ArrayList<>(Arrays.asList(at, to)));
            } else {
                low[at] = Math.min(low[at], ids[to]);
            }
        }
    }
}
