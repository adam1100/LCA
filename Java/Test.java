
import static org.junit.Assert.*;

public class Test {

	@org.junit.Test
	public void testEmpty() {
		LCA tree = new LCA();
		assertEquals("Empty tree", -1, tree.findLCA(1,2)); 
		
	}
	@org.junit.Test
	public void testSingleNode() {
		LCA tree = new LCA();
		tree.root = new Node(1);
		assertEquals("One Node tree", -1, tree.findLCA(1,2)); 
	}
	
	@org.junit.Test
	public void testThreeNodes() {
		LCA tree = new LCA();
		tree.root = new Node(1);
	    tree.root.left = new Node(2); 
	    tree.root.right = new Node(3); 
		assertEquals("Three node tree", 1, tree.findLCA(2,3)); 
	}
	
	@org.junit.Test
	public void testBiggerTree() {
	    LCA tree = new LCA(); 
	    tree.root = new Node(1); 
	    tree.root.left = new Node(2); 
	    tree.root.right = new Node(3); 
	    tree.root.left.left = new Node(4); 
	    tree.root.left.right = new Node(5); 
	    tree.root.right.left = new Node(6); 
	    tree.root.right.right = new Node(7); 
	    
	   assertEquals("LCA(4, 5): ", 2, tree.findLCA(4,5)); 
	   assertEquals("LCA(4, 6): ", 1, tree.findLCA(4,6)); 
	   assertEquals("LCA(3, 4): ", 1, tree.findLCA(3,4)); 
	   assertEquals("LCA(2, 4): ", 2, tree.findLCA(2,4)); 
	   assertEquals("LCA(4, 7): ", 1, tree.findLCA(4,7)); 
	}


	// new branch here

	@org.junit.Test
	public void testTreeDAG() {
		Node a, b, c, d, e;
		LCA tree = new LCA(); 
		a = (tree.root = new Node(1));
		b = (a.left = new Node(2));
		c = (a.right = new Node(3)); 
		d = (b.right = new Node(4)); 
		e = (c.left = d);
		
		assertTrue(tree.findLCA(b.data, d.data) == b.data); //true
		assertFalse(tree.findLCA(c.data, e.data) == c.data); //false
		
		// it does not work for tree DAG
		// update: same test passed with DAG implementation (below)
	}
	
	@org.junit.Test
	public void testAddEdge() {
		DAG dag = new DAG(3);
		assertEquals(0, dag.E);

		dag.addEdge(0, 1);
		assertEquals(1, dag.E);
	}
	
	@org.junit.Test
	public void testCycles() {
		DAG dag = new DAG(6);
		dag.addEdge(0, 1);
		dag.addEdge(1, 2);
		dag.addEdge(2, 0);
		dag.findCycle(0);
		assertTrue(dag.hasCycle);
		
		DAG dag1 = new DAG(6);
		dag1.addEdge(1, 2);
		dag1.addEdge(1, 3);
		dag1.addEdge(2, 4);
		dag1.addEdge(3, 4);
		dag1.findCycle(0);
		assertFalse(dag1.hasCycle);
	}
	
	@org.junit.Test
	public void testDAG() {
		DAG dag = new DAG(6);
		dag.addEdge(1, 2);
		dag.addEdge(1, 3);
		dag.addEdge(2, 4);
		dag.addEdge(3, 4);
		
		assertTrue(dag.findLCA(2, 4) == 2); //true
		assertTrue(dag.findLCA(3, 4) == 3); //true
		
		assertEquals("Testing invalid vertices", -1, dag.findLCA(4,5));
		
		DAG dag1 = new DAG(10);
		dag1.addEdge(1, 2);
		dag1.addEdge(1, 3);
		dag1.addEdge(2, 4);
		dag1.addEdge(2, 5);
		dag1.addEdge(3, 6);
		dag1.addEdge(3, 7);
		
		assertEquals("LCA(4,5)", 2, dag1.findLCA(4, 5)); 
		assertEquals("LCA(4,6)", 1, dag1.findLCA(4, 6)); 
		assertEquals("LCA(3,4)", 1, dag1.findLCA(3, 4)); 
		assertEquals("LCA(2,4)", 2, dag1.findLCA(2, 4)); 
		assertEquals("LCA(4,7)", 1, dag1.findLCA(4, 7)); 

		
	}

}
