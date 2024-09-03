package org.example.tree;

import org.junit.Test;
import org.example.tree.TreeNode;


public class TreeSolutionTest {

    TreeNode root = new TreeNode(3, new TreeNode(9, new TreeNode(12), null), new TreeNode(20, new TreeNode(15), new TreeNode(7)));
    TreeNode root2 = new TreeNode(3, new TreeNode(9), new TreeNode(12));


    @Test
    public void testMaxDepth() {

        int maxDepth = TreeSolution.maxDepth(root);
        assert maxDepth == 3;
    }

    @Test
    public void testIsBalanced() {

        boolean isBalanced = TreeSolution.isBalanced(root);
        assert isBalanced;
    }

    @Test
    public void testDiameterOfBinaryTree() {
        int diameter = TreeSolution.diameterOfBinaryTree(root2);
        assert diameter == 2;
    }

    @Test
    public void testInvertTree() {

        TreeNodeShow.show(root);
        TreeNode invertTree = TreeSolution.invertTree(root);
        TreeNodeShow.show(invertTree);
        assert invertTree.val == 3 && invertTree.left.val == 20 && invertTree.right.val == 9 && invertTree.left.left.val == 7 && invertTree.left.right.val == 15 && invertTree.right.left == null && invertTree.right.right.val == 12;
    }

}
