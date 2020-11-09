
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
	public void testDAG() {
		Node a, b, c, d;
		LCA tree = new LCA(); 
		a = (tree.root = new Node(1));
		b = (tree.root.left = new Node(2));
		c = (tree.root.right = new Node(3)); 
		d = (tree.root.left.right = new Node(4)); 
		 
		tree.root.right.left = d; 

		assertTrue(tree.findLCA(2,d.data)==tree.findLCA(3, d.data));
		
		// it does not work for DAG
	}
	

}
