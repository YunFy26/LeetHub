package org.example.tree.trees;

class AVLTree<K extends Comparable<K>, V>{

    private TreeNode<K, V> root;

    private int size;

    AVLTree(){
        this.root = null;
        this.size = 0;
    }

    public TreeNode<K, V> insert(K key, V value){
        return root = insert(root, key, value);
    }

    public TreeNode<K, V> insert(TreeNode<K, V> node, K key, V value){
        if (node == null) {
            size++;
            return new TreeNode<>(key, value);
        }
        if (key.compareTo(node.key) < 0) {
            node.left = insert(node.left, key, value);
        }else if (key.compareTo(node.key) > 0 ){
            node.right = insert(node.right, key, value);
        }
        // update height
        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;

        if (getBalanceFactor(node) == 2 && getBalanceFactor(node.left) == 1)
            // LL
            return LLRotate(node);
        if (getBalanceFactor(node) == 2 && getBalanceFactor(node.left) == -1){
            // LR
            node.left = RRRotate(node.left);
            return LLRotate(node);
        }
        if (getBalanceFactor(node) == -2 && getBalanceFactor(node.right) == 1){
            // RL
            node.right = LLRotate(node.right);
            return RRRotate(node);
        }
        if (getBalanceFactor(node) == -2 && getBalanceFactor(node.right) == -1)
            // RR
            return RRRotate(node);
        return node;
    }

    /**
     * LL型 --> 右旋
     * @param node 需要旋转的节点
     * @return 返回旋转后的树
     */
    private TreeNode<K, V> LLRotate(TreeNode<K, V> node){
        // The node is left child of @param node
        TreeNode<K, V> oldLeft = node.left;
        // The node is right child of oldLeft
        TreeNode<K, V> oldRight = oldLeft.right;
        oldLeft.right = node;
        node.left = oldRight;
        // update height
        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
        oldLeft.height = Math.max(getHeight(oldLeft.left), getHeight(oldLeft.right)) + 1;
        return oldLeft;
    }

    /**
     * RR型 --> 左旋
     * @param node 待旋转的节点
     * @return 返回旋转后的树
     */
    private TreeNode<K, V> RRRotate(TreeNode<K, V> node){
        // The node is right child of @param node
        TreeNode<K, V> oldRight = node.right;
        // The node is left child of oldRight
        TreeNode<K, V> oldLeft = oldRight.left;
        oldRight.left = node;
        node.right = oldLeft;
        // update height
        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
        oldRight.height = Math.max(getHeight(oldRight.left), getHeight(oldRight.right)) + 1;

        return oldRight;
    }

    /**
     * get balance factor of node
     * @param node the specify node
     * @return the balance factor
     */
    private int getBalanceFactor(TreeNode<K, V> node){
        if(node == null) return 0;
        return getHeight(node.left)-getHeight(node.right);
    }

    /**
     * the height of avl tree
     * @param node node
     * @return height of this tree
     */
    private int getHeight(TreeNode<K, V> node){
        if (node != null) return node.height;
        return -1;
    }

    public TreeNode<K, V> getRoot() {
        return root;
    }

    /**
     * get node from avl tree
     */
    public TreeNode<K, V> getNode(K key){
        return getNode(root, key);
    }

    public TreeNode<K, V> getNode(TreeNode<K, V> node, K key){
        if (node == null) return null;
        // key < node.key, search in left subtree
        // key > node.key, search in right subtree
        if (key.compareTo(node.key) < 0){
            return getNode(node.left, key);
        }else if (key.compareTo(node.key) > 0){
            return getNode(node.right, key);
        }else return node;
    }

    /**
     * get successor of specify node
     * @param node the specify node
     * @return the successor of this node   (the successor in In-order)
     */
    public TreeNode<K, V> getSucc(TreeNode<K, V> node){
        if (node.left == null){
            return node;
        }
        return getSucc(node.left);
    }

    /**
     * remove the specify node from avl tree
     * @param key the key of node to be removed
     * @return avl tree after removing specify node
     */
    public TreeNode<K, V> remove(K key){
        return root = remove(root, key);
    }

    public TreeNode<K, V> remove(TreeNode<K, V> node, K key){
        TreeNode<K, V> returnNode;

        if(node == null){
            return null;
        }else if (key.compareTo(node.key) < 0){
            node.left = remove(node.left, key);
            returnNode = node;
        }else if (key.compareTo(node.key) > 0){
            node.right = remove(node.right, key);
            returnNode = node;
        }else {
            // first, delete the specify node, consider follows
            // left subtree and right subtree are null
            if (node.left == null && node.right == null){
                size--;
                return null;
            }else if (node.left == null){
                // only right subtree, let node.right replace node and then delete node.right
                TreeNode<K, V> nodeRight = node.right;
                nodeRight.right = null;
                size--;
                returnNode = nodeRight;
            }else if (node.right == null){
                // only left subtree, let node.left replace node and then delete node.left
                TreeNode<K, V> nodeLeft = node.left;
                node.left = null;
                size--;
                returnNode = nodeLeft;
            }else {
                // left and right subtree are not null, get the successor of node
                TreeNode<K, V> succ = getSucc(node);
                // let succ replace node and then delete succ
                // succ.right <-- node.right but remove succ, succ.left <-- node.left
                succ.right = remove(node.right, key);
                succ.left = node.left;
                // let node.left and right be null
                node.left = node.right = null;
                returnNode = succ;
            }
        }
        // second, get balance factor and then rotate
        // update height
        node.height = Math.max(getHeight(returnNode.left), getHeight(returnNode.right)) + 1;
        int balanceFactor = getBalanceFactor(returnNode);
        if (balanceFactor == 2 && getBalanceFactor(returnNode.left) == 1){
            // LL --> right rotate node
            return LLRotate(returnNode);
        }else if (balanceFactor == 2 && getBalanceFactor(returnNode.left) == -1){
            // LR --> left rotate node.left, and then right rotate node
            returnNode.left = RRRotate(returnNode.left);
            return LLRotate(returnNode);
        }else if (balanceFactor == -2 && getBalanceFactor(returnNode.right) == 1){
            // RL --> right rotate for node.right, and then left rotate node
            returnNode.right = LLRotate(returnNode.right);
            return RRRotate(returnNode);
        }else if (balanceFactor == -2 && getBalanceFactor(returnNode.right) == -1){
            // RR --> left rotate node
            return RRRotate(returnNode);
        }
        return returnNode;
    }
}

class TreeNode<K extends Comparable<K>, V> {
    K key;

    V value;

    TreeNode<K, V> left;

    TreeNode<K, V> right;

    int height;

    TreeNode(K key, V value) {
        this.key = key;
        this.value = value;
        this.left = null;
        this.right = null;
        this.height = 0;
    }


}

public class AVL<K extends Comparable<? super K>, V>{
    public static void main(String[] args) {
        AVLTree<Integer, String> avlTree= new AVLTree<>();
        avlTree.insert(11,"a");
        avlTree.insert(7,"b");
        avlTree.insert(17,"c");
        avlTree.insert(5,"c");
        avlTree.insert(9,"c");
        avlTree.insert(14,"c");
        avlTree.insert(19,"c");
        avlTree.insert(8,"c");
        avlTree.insert(13,"c");
        avlTree.insert(18,"c");
        avlTree.insert(27,"c");
        avlTree.insert(23,"c");
        printAVLTree(avlTree.getRoot(), "", true);
        avlTree.remove(5);
        System.out.println("------------------------------");
        printAVLTree(avlTree.getRoot(), "", true);
    }

    /**
     * print tree
     * @param node root
     */
    private static <K extends Comparable<K>, V> void printAVLTree(TreeNode<K, V> node, String prefix, boolean isLeft) {
        if (node != null) {
            System.out.println(prefix + (isLeft ? "├── " : "└── ") + node.key + " (" + node.height + ")");
            printAVLTree(node.left, prefix + (isLeft ? "│   " : "    "), true);
            printAVLTree(node.right, prefix + (isLeft ? "│   " : "    "), false);
        }
    }
}

