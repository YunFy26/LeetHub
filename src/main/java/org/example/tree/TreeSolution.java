package org.example.tree;


import com.sun.source.tree.Tree;

import java.util.*;

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


    /**
     * 检查一个二叉树是否轴对称
     * @param root 根节点
     * @return boolean
     */
    public static boolean isSymmetric(TreeNode root) {

        return isTrue(root.left, root.right);

    }

    private static boolean isTrue(TreeNode root1, TreeNode root2){

        if(root1 == null && root2 == null) return true;
        return root1 != null && root2 != null && root1.val == root2.val && isTrue(root1.left, root2.right) && isTrue(root1.right, root2.left);
    }

    /**
     * 给定一个二叉树，找出其最小深度
     * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
     * @param root
     * @return
     */
    public static int minDepth(TreeNode root) {
        if (root == null) return 0;
        if (root.left != null && root.right != null) return Math.min(minDepth(root.left), minDepth(root.right)) + 1;
        return Math.max(minDepth(root.left), minDepth(root.right)) + 1;
    }

    /**
     * 给定二叉树的根节点 root ，返回所有左叶子之和
     * @param root
     * @return
     */
    public static int sumOfLeftLeaves(TreeNode root) {
        if (root == null) return 0;
        if (root.left != null && root.left.left == null && root.left.right == null) {
            return root.left.val + sumOfLeftLeaves(root.left) + sumOfLeftLeaves(root.right);
        }
        return sumOfLeftLeaves(root.left) + sumOfLeftLeaves(root.right);
    }

    /**
     * 最长同值路径, 可以经过根节点也可以不经过根节点
     * @param root
     * @return
     */
    static int sum;
    public static int longestUnivaluePath(TreeNode root) {
        sum = 0;
        longestPath(root);
        return sum;
//        if (root == null) return 0;
//        return Math.max(longestPath(root), Math.max(longestUnivaluePath(root.left), longestUnivaluePath(root.right)));

    }

    private static int longestPath(TreeNode root){
        if (root == null) return 0;
        int left1 = longestPath(root.left);
        int right1 = longestPath(root.right);
        int left = 0, right = 0;
        if (root.left != null && root.left.val == root.val) left = left1 + 1;
        if (root.right != null && root.right.val == root.val) right = right1 + 1;
        sum = Math.max(sum, left + right);
        return Math.max(left, right);
    }

    /**
     * 打家劫舍
     * @param root 根节点
     * @return 最大金额
     */
    static Map<TreeNode, Integer> select = new HashMap<>();
    static Map<TreeNode, Integer> noSelect = new HashMap<>();
    public static int rob(TreeNode root){
        dfs(root);
        return Math.max(select.getOrDefault(root, 0), noSelect.getOrDefault(root, 0));
    }

    private static void dfs(TreeNode root){
        if (root == null) return;
        dfs(root.left);
        dfs(root.right);
        select.put(root, root.val + noSelect.getOrDefault(root.left, 0) + noSelect.getOrDefault(root.right, 0));
        noSelect.put(root, Math.max(select.getOrDefault(root.left, 0), noSelect.getOrDefault(root.left, 0)) + Math.max(select.getOrDefault(root.right, 0), noSelect.getOrDefault(root.right, 0)));
    }

    /**
     * 给定一个非空特殊的二叉树，每个节点都是正数，并且每个节点的子节点数量只能为 2 或 0。
     * 如果一个节点有两个子节点的话，那么该节点的值等于两个子节点中较小的一个。
     * 二叉树中第二小的节点
     * @return
     */
    int rootValue;
    int ans;
    public int findSecondMinimumValue(TreeNode root){
        ans = -1;
        rootValue = root.val;
        dfs2(root);
        return ans;
    }

    private void dfs2(TreeNode treeNode){
        if (treeNode == null) return;
        if (ans != -1 && treeNode.val >= ans) return;
        if (treeNode.val > rootValue) ans = treeNode.val;
        dfs2(treeNode.left);
        dfs2(treeNode.right);
    }


    /**
     * 非递归先序遍历二叉树
     * @param root 根节点
     * @return 先序遍历结果
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()){
            res.add(stack.pop().val);
            if (root.right != null) stack.push(root.right);
            if (root.left != null) stack.push(root.left);
        }
        return res;
    }

    /**
     * 非递归中序遍历二叉树
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        while(root != null || !stack.isEmpty()){
            while (root != null){
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            ans.add(root.val);
            root = root.right;
        }
        return ans;
    }

    /**
     * 层次遍历
     * 返回每层节点的平均值
     */
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> ans = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        if (root == null) return ans;
        queue.add(root);
        while(!queue.isEmpty()){
            double sum = 0;
            int size = queue.size();
            for (int i=0; i<size; ++i) {
                TreeNode node = queue.poll();
                sum += node.val;
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
            ans.add(sum / size);
        }
        return ans;
    }

    /**
     * 找非空二叉树的最底层最左边的节点的值
     * @param root
     * @return
     */
    public int findBottomLeftValue(TreeNode root) {
        int height = getHeight(root);
        int init = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        if (root != null) queue.add(root);
        while (!queue.isEmpty()){
            init++;
            if (init == height - 1) break;
            int size = queue.size();
            for (int i=0; i<size; i++){
                TreeNode node = queue.poll();
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
        }
        return queue.poll().val;
    }

    private int getHeight(TreeNode root){
        if (root == null) return 0;
        return Math.max(getHeight(root.left),getHeight(root.right)) + 1;
    }

    /**
     * 修剪二叉搜索树
     * 给你二叉搜索树的根节点 root ，同时给定最小边界low 和最大边界 high。通过修剪二叉搜索树，使得所有节点的值在[low, high]中。
     * 修剪树 不应该 改变保留在树中的元素的相对结构 (即，如果没有被移除，原有的父代子代关系都应当保留)。 可以证明，存在 唯一的答案 。
     * @param root
     * @param low
     * @param high
     * @return
     */
    public static TreeNode trimBST(TreeNode root, int low, int high){
        while (root != null && (root.val < low || root.val > high)){
            if (root.val < low){
                root = root.right;
            }else {
                root = root.left;
            }
        }
        if (root == null) return null;
        for (TreeNode node = root; node.left != null;){
            if (node.left.val < low){
                node.left = node.left.right;
            }else {
                node = node.left;
            }
        }

        for (TreeNode node = root; node.right != null;){
            if (node.right.val > high){
                node.right = node.right.left;
            }else {
                node = node.right;
            }
        }
        return root;

    }



}
