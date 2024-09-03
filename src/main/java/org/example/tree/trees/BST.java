package org.example.tree.trees;

/**
 * 二叉搜索树
 * 树的大小
 * 获取节点、插入节点、删除节点
 */
class BinarySearchTree<K extends Comparable<K>, V> {

    private Node<K, V> root;

    private int size;

    BinarySearchTree(){
        this.root = null;
        this.size = 0;
    }

    public void insert(K key, V value){
        root = insert(root, key, value);
    }

    public Node<K, V> insert(Node<K, V> node, K key, V value) {
        if (node == null) {
            size++;
            return new Node<>(key, value);
        }else if (key.compareTo(node.key) < 0) {
            node.left = insert(node.left, key, value);
        }else if (key.compareTo(node.key) > 0) {
            node.right = insert(node.right, key, value);
        }
        return node;
    }

    public Node<K, V> getNode(K key){
        return getNode(root, key);
    }

    public Node<K, V> getNode(Node<K, V> node, K key){
        if(node == null){
            return null;
        }
        if (key.compareTo(node.key) < 0){
            return getNode(node.left, key);
        }else if (key.compareTo(node.key) > 0){
            return getNode(node.right, key);
        }else return node;
    }

    public Node<K, V> remove(K key) {
        Node<K, V> removedNode = getNode(key);
        if (removedNode == null) {
            return null;
        }
        return root = remove(root, key);
    }

    /**
     * 删除节点
     * @param node 树（子树）的根节点
     * @param key 要删除的节点的key
     * @return 删除后的树
     */
    public Node<K, V> remove(Node<K, V> node, K key){

        if (node == null){
            return null;
        }
        if (key.compareTo(node.key) < 0){
            // 如果key小于当前节点的key,则在左子树中删除
            node.left = remove(node.left, key);
            return node;
        }else if (key.compareTo(node.key) > 0){
            node.right = remove(node.right, key);
            return node;
        }else {
            if (node.left == null && node.right == null){
                // 被删除的节点没有孩子
                size--;
                return null;
            }else if (node.left == null){
                // 被删除的节点只有右孩子,右孩子代替被删除的节点，同时删除右孩子
                size--;
                Node<K, V> rightNode = node.right;
                node.right = null;
                return rightNode;
            }else if (node.right == null){
                // 被删除的节点只有左孩子,左孩子代替被删除的节点，同时删除左孩子
                size--;
                Node<K, V> leftNode = node.left;
                node.left = null;
                return leftNode;
            }else {
                Node<K, V> succ = minimum(node.right);
                // 注意这里，后继节点的右孩子等于删除节点的右子树，但是移除了后继节点
                succ.right = remove(node.right, succ.key); // 这里已经做了size--；
                // 后继节点的左子树等于删除节点的左子树
                succ.left = node.left;
                // 上面两行代码的顺序不能换，因为先执行第一行代码，node.left会被置为null
                node.left = node.right = null;
                return succ;
            }
        }
    }

    /**
     * 获取子树的最小节点
     * @param node 子树的根节点
     * @return Node<K, V>
     */
    public Node<K, V> minimum(Node<K, V> node){
        if (node.left == null){
            return node;
        }
        return minimum(node.left);
    }

    public boolean contains(K key){
        Node<K, V> node = getNode(key);
        return node != null;
    }
}

class Node<K extends Comparable<K>, V>{
    K key;
    V value;
    Node<K, V> left;
    Node<K, V> right;
    Node(K key, V value){
        this.key = key;
        this.value = value;
        this.left = null;
        this.right = null;
    }
}

public class BST{
    public static void main(String[] args) {
        BinarySearchTree<Integer, String> bst = new BinarySearchTree<>();
        bst.insert(2, "b");
        bst.insert(1, "a");
        bst.insert(5, "c");
        bst.insert(4, "c");
        bst.insert(6, "c");
        // assert bst.getNode(bst.getNode(2).right.key).left.key == 4;
        System.out.println(bst.getNode(bst.getNode(2).right.key).left.key);
        System.out.println(bst.getNode(bst.getNode(2).right.key).right.key);
        bst.insert(5, "c");
        System.out.println(bst.getNode(bst.getNode(2).right.key).left.key);
        System.out.println(bst.getNode(bst.getNode(2).right.key).right.key);
        // System.out.println(bst.minimum(bst.getNode(2)).key);
        // System.out.println(bst.getNode(1).key);
        // bst.remove(5);
        // System.out.println(bst.getNode(6).left.key);
    }
}