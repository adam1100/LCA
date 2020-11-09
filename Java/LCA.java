import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

class Node { 
    int data; 
    Node left, right;
  
    Node(int value) { 
        data = value; 
        left = right = null; 
    } 
}

class DAG { //inspired by Sedgewick & Wayne Digraph.java
    public final int V;
    public int E;        
    public boolean[] marked;   
    public ArrayList<Integer>[] adj; 
    public int[] indegree;
    public int[] outdegree;
    public boolean hasCycle;
    public boolean stack[];

    public DAG(int V) {
        if (V < 0) throw new IllegalArgumentException("vertices must be > 0");
        this.V = V;
        this.E = 0;
        indegree = new int[V];
        outdegree = new int[V];
	    marked = new boolean[V];
	    stack = new boolean[V];
		adj = (ArrayList<Integer>[]) new ArrayList[V];

		for (int v = 0; v < V; v++) {
			adj[v] = new ArrayList<Integer>();
		}
    }

    private void validateVertex(int v) {
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }
    
    public Iterable<Integer> adj(int v) {
		return adj[v];
	}

    public void addEdge(int v, int w){
        validateVertex(v);
        validateVertex(w);
        adj[v].add(w);
        indegree[w]++;
        outdegree[v]++;
        E++;
    }
    

	public void findCycle(int v) {
		marked[v] = true;
		stack[v] = true;

		for (int w : adj(v)) {
			if (!marked[w]) {
				findCycle(w);
			} else if (stack[w]) {
				hasCycle = true;
				return;
			}
		}
		stack[v] = false;
	}

	public int findLCA(int v, int w) {
		findCycle(0);
		validateVertex(v);
		validateVertex(w);
		if (hasCycle) {
			return -1;
		}
		else if (E == 0) {
			return -1;
		}

		DAG reverse = reverse();

		ArrayList<Integer> array1 = reverse.BFS(v);
		ArrayList<Integer> array2 = reverse.BFS(w);
		ArrayList<Integer> commonAncestors = new ArrayList<Integer>();

		boolean found = false;

		for (int i = 0; i < array1.size(); i++) {
			for (int j = 0; j < array2.size(); j++) {
				if (array1.get(i) == array2.get(j)) {
					commonAncestors.add(array1.get(i));
					found = true;
				}
			}
		}

		if (found) {
			return commonAncestors.get(0);
		} else {
			return -1;
		}
	}

	public ArrayList<Integer> BFS(int s) {
		ArrayList<Integer> order = new ArrayList<Integer>();
		boolean visited[] = new boolean[V]; 
		LinkedList<Integer> queue = new LinkedList<Integer>();

		visited[s] = true;
		queue.add(s);

		while (queue.size() != 0) {
			s = queue.poll(); 
			order.add(s);

			Iterator<Integer> i = adj[s].listIterator();

			while (i.hasNext()) {
				int n = i.next();
				if (!visited[n]) {
					visited[n] = true;
					queue.add(n);
				}
			}
		}
		return order;
	}

	public DAG reverse() {
		DAG reverse = new DAG(V);
		for (int v = 0; v < V; v++) {
			for (int w : adj(v)) {
				reverse.addEdge(w, v);
			}
		}
		return reverse;
	}
}

public class LCA {
    Node root; 
    private List<Integer> path1 = new ArrayList<>(); 
    private List<Integer> path2 = new ArrayList<>(); 
  
    int findLCA(int n1, int n2) { 
        path1.clear(); 
        path2.clear();
        return findLCAInternal(root, n1, n2); 
    } 
  
    private int findLCAInternal(Node root, int n1, int n2) { 
  
        if (!findPath(root, n1, path1) || !findPath(root, n2, path2)) { 
            return -1; 
        } 
  
        int i; 
        for (i = 0; i < path1.size() && i < path2.size(); i++) { 
              
            if (!path1.get(i).equals(path2.get(i))) 
                break; 
        } 
  
        return path1.get(i-1); 
    } 
      
    private boolean findPath(Node root, int n, List<Integer> path) 
    { 
   
        if (root == null) { 
            return false; 
        } 
          
        path.add(root.data); 
  
        if (root.data == n) { 
            return true; 
        } 
  
        if (root.left != null && findPath(root.left, n, path)) { 
            return true; 
        } 
  
        if (root.right != null && findPath(root.right, n, path)) { 
            return true; 
        } 

        path.remove(path.size()-1); 
  
        return false; 
    } 


}
