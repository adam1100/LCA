from LCA import Node, findLCA   # The code to test
import unittest   # The test framework

class TestLCA(unittest.TestCase):
    def test_findLCA(self):
        
        root = Node(1) 

        self.assertEqual((findLCA(root, 1, 2,)), -1, 'Testing LCA with 1 Node tree')

        root.left = Node(2) 
        root.right = Node(3) 

        self.assertEqual((findLCA(root, 2, 3,)), 1, 'Testing LCA with 3 Node tree')

        root.left.left = Node(4) 
        root.left.right = Node(5) 
        root.right.left = Node(6) 
        root.right.right = Node(7) 
        
        self.assertEqual((findLCA(root, 4, 5,)), 2, 'Testing LCA(4,5)')
        self.assertEqual((findLCA(root, 4, 6,)), 1, 'Testing LCA(4,6)') 
        self.assertEqual((findLCA(root,3,4,)), 1, 'Testing LCA(3,4)' )
        self.assertEqual((findLCA(root,2, 4,)), 2, 'Testing LCA(2,4)')
