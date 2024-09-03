package org.example.tree;

import com.sun.source.tree.Tree;

public class TreeSolution {

    /**
     * 求二叉树的最大深度
     * @param root 树的根节点
     * @return int 最大深度
     */
    public static int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);
        return Math.max(leftDepth, rightDepth) + 1;
    }

    /**
     * 判断一棵树是否是平衡二叉树
     * @param root 树的根节点
     * @return boolean
     */
    public static boolean isBalanced(TreeNode root){

        if (root == null) {
            return true;
        }

        int leftHeight = maxDepth(root.left);
        int rightHeight = maxDepth(root.right);

        if (Math.abs(leftHeight - rightHeight) > 1) {
            return false;
        }

        return isBalanced(root.left) && isBalanced(root.right);

    }

    /**
     * 求二叉树的直径：任意两个节点之间最长路径的长度
     * @param root 根节点
     * @return int 任意两个节点之间的最长路径
     */
    public static int diameterOfBinaryTree(TreeNode root){
        // 左子树高度 + 右子树高度 --> 遍历每个节点，求max
        if (root == null) {
            return 0;
        }
        int leftHeight = maxDepth(root.left);
        int rightHeight = maxDepth(root.right);
        int sum = leftHeight + rightHeight;
        return Math.max(sum, Math.max(diameterOfBinaryTree(root.left), diameterOfBinaryTree(root.right)));

    }

    /**
     * 翻转二叉树，返回根节点
     * @param root 树的根节点
     * @return 翻转后的根节点
     */
    public static TreeNode invertTree(TreeNode root){

        if (root == null) return null;

        TreeNode treeNode = root.left;
        root.left = invertTree(root.right);
        root.right = invertTree(treeNode);

        return root;

    }


}
