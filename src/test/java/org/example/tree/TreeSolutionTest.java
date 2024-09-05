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

    @Test
    public void testMergeTrees(){
        TreeNode mergeTrees = TreeSolution.mergeTrees(root, root2);
        TreeNodeShow.show(mergeTrees);
        assert mergeTrees.val == 6 && mergeTrees.left.val == 18 && mergeTrees.right.val == 32 && mergeTrees.left.left.val == 12 && mergeTrees.left.right == null && mergeTrees.right.left.val == 15 && mergeTrees.right.right.val == 7;
    }

    @Test
    public void testHasPathSum(){
        boolean hasPathSum1 = TreeSolution.hasPathSum(root, 38);
        boolean hasPathSum2 = TreeSolution.hasPathSum(root, 3);
        assert hasPathSum1 && !hasPathSum2;
    }

    @Test
    public void testPathSum(){
        TreeNode root = new TreeNode(1, new TreeNode(-2, new TreeNode(1, new TreeNode(-1), null), new TreeNode(3)), new TreeNode(-3, null, new TreeNode(-2)));
        TreeNodeShow.show(root);
        int count = TreeSolution.pathSum(root, -1);
        assert count == 4;
    }

    @Test
    public void testIsSubtree(){
        TreeNode root = new TreeNode(3, new TreeNode(4, new TreeNode(1, new TreeNode(4), new TreeNode(3)), new TreeNode(2)), new TreeNode(5));
        TreeNode subRoot = new TreeNode(4, new TreeNode(1), new TreeNode(2));
        TreeNodeShow.show(root);
        TreeNodeShow.show(subRoot);
        boolean isSubtree = TreeSolution.isSubtree(root, subRoot);
        assert !isSubtree;
    }

    @Test
    public void testIsSymmetric(){
        TreeNode root = new TreeNode(1, new TreeNode(2, new TreeNode(3), new TreeNode(4)), new TreeNode(2, new TreeNode(4), new TreeNode(3)));
        TreeNodeShow.show(root);
        boolean isSymmetric = TreeSolution.isSymmetric(root);
        assert isSymmetric;
    }

    @Test
    public void testMinDepth(){
        TreeNode root = new TreeNode(2, null, new TreeNode(3, null, new TreeNode(4, null, new TreeNode(5, null, new TreeNode(6)))));
        TreeNodeShow.show(root);
        int minDepth = TreeSolution.minDepth(root);
        assert minDepth == 5;
    }

    @Test
    public void testSumOfLeftLeaves(){
        TreeNode root = new TreeNode(3, new TreeNode(9), new TreeNode(20, new TreeNode(15), new TreeNode(7)));
        TreeNodeShow.show(root);
        int sum = TreeSolution.sumOfLeftLeaves(root);
        assert sum == 24;
    }

}
