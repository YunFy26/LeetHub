package org.example.tree.trees;

public class RBT {

}

class RedBlackTree<K extends Comparable<? super K>, V>{

    RBTNode<K, V> root;

    int size;

    RedBlackTree(){
        this.root = null;
        size = 0;
    }

    /**
     *
     * @param key key
     * @param value value
     * @return tree after inserting node
     */
    public RBTNode<K, V> insert(K key, V value){
        return insert(root, key, value);
    }

    public RBTNode<K, V> insert(RBTNode<K, V> node, K key, V value){
        // The inserted node is the root node
        if (node == null){
            size++;
            return new RBTNode<>(key, value, false);
        }
        // the color of uncle node(of inserted node) is red

        // the color of uncle node(of inserted node) is black
        return node;
    }

    /**
     * left rotate
     * @param node node
     * @return tree after rotating
     */
    public RBTNode<K, V> leftRotate(RBTNode<K, V> node){

        RBTNode<K, V> rightChild = node.right;
        RBTNode<K, V> rightChildLeft = rightChild.left;
        rightChild.left = node;
        node.right = rightChildLeft;
        // update height
        return rightChild;
    }

    /**
     * right rotate
     * @param node node
     * @return tree after rotating
     */
    public RBTNode<K, V> rightRotate(RBTNode<K, V> node){

        RBTNode<K, V> leftChild = node.left;
        RBTNode<K, V> leftChildRight = leftChild.right;
        leftChild.right = node;
        node.left = leftChildRight;
        // update height
        node.height = Math.max(getHeight(node.left), getHeight(node.right));
        leftChild.height = Math.max(getHeight(leftChild.left), getHeight(leftChild.right));
        return leftChild;
    }

    /**
     * get height of node
     * @param node node
     * @return height
     */
    public int getHeight(RBTNode<K, V> node){
        if (node == null){
            return -1;
        }
        return node.height;
    }

//    public RBTNode<K, V> getUncle(RBTNode<K, V> node){
//        if (node == null)
//            return null;
//        if (node)
//    }

}

class RBTNode<K extends Comparable<? super K>, V>{

    K key;

    V value;

    RBTNode<K, V> left;

    RBTNode<K, V> right;

    RBTNode<K, V> parent;

    int height;

    boolean isRed;

    RBTNode(K key, V value){
        this.key = key;
        this.value = value;
        this.left = null;
        this.right = null;
        this.height = 0;
        this.isRed = true;
    }

    RBTNode(K key, V value, boolean isRed){
        this.key = key;
        this.value = value;
        this.left = null;
        this.right = null;
        this.height = 0;
        this.isRed = isRed;
    }
}