package org.example.tree;


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

    /**
     * 合并两棵树, 如果两个节点重叠, 那么将这两个节点的值相加作为合并后节点的新值; 否则, 不为 null 的节点将直接作为新二叉树的节点
     * @param root1 树1的根节点
     * @param root2 树2的根节点
     * @return 合并后的根节点
     */
    public static TreeNode mergeTrees(TreeNode root1, TreeNode root2) {

        if (root1 == null) return root2;
        if (root2 == null) return root1;

        root1.val += root2.val;
        root1.left = mergeTrees(root1.left, root2.left);
        root1.right = mergeTrees(root1.right, root2.right);
        return root1;

    }

    /**
     * 给你二叉树的根节点 root 和一个表示目标和的整数 targetSum
     * 判断该树中是否存在 根节点到叶子节点 的路径，这条路径上所有节点值相加等于目标和 targetSum
     * @param root 根节点
     * @param targetSum 目标和
     * @return boolean
     */
    public static boolean hasPathSum(TreeNode root, int targetSum){

        if (root == null) return false;
        // 需要加上条件 root.left == null && root.right == null 因为题目要求是 根节点到叶子节点
        if (root.val == targetSum && root.left == null && root.right == null) return true;
        return hasPathSum(root.left, targetSum - root.val) || hasPathSum(root.right, targetSum - root.val);

    }


    /**
     * <br>给定一个二叉树的根节点 root ，和一个整数 targetSum ，求该二叉树里节点值之和等于 targetSum 的 路径 的数目<br/>
     * <br>路径不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）<br/>
     * @param root 根节点
     * @param targetSum 目标和
     * @return int 路径数量
     */
    public static int pathSum(TreeNode root, int targetSum) {
        // 取模防止溢出
        targetSum = targetSum % 1000000007;
        if (root == null) return 0;
        return path(root, targetSum) + pathSum(root.left, targetSum) + pathSum(root.right, targetSum);

    }

    private static int path(TreeNode root, int targetSum){
        targetSum = targetSum % 1000000007;
        if (root == null) return 0;
        if (root.val == targetSum) {
            /**
             *             1
             *          /     \
             *       -2          -3
             *     /   \           \
             *   1       3           -2
             *  /
             * -1
             * 对于上面这种情况，如果不加上下面这个判断，会漏掉 1 -2  1 -1 这条路径
             */
            if (root.left == null && root.right == null) return 1;
            else return 1 + path(root.left, 0) + path(root.right, 0);
        };
        return path(root.left, targetSum - root.val) + path(root.right, targetSum - root.val);
    }


    /**
     * <br>两棵二叉树 root 和 subRoot, 看subRoot是否是root的子树</br>
     * <br>子树: 某个节点和这个节点的所有后代节点</br>
     * @param root 根节点
     * @param subRoot 子树的根节点
     * @return boolean
     */
    public static boolean isSubtree(TreeNode root, TreeNode subRoot) {

        if (root == null) return false;
        if (subRoot == null) return true;

        return subTree(root, subRoot) || isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);

    }

    private static boolean subTree(TreeNode root, TreeNode subRoot){
        if (root == null && subRoot == null) return true;
        if (root == null || subRoot == null) return false;
        return root.val == subRoot.val && subTree(root.left, subRoot.left) && subTree(root.right, subRoot.right);
    }


}
