package trees;
import queue.QueueArrayGeneric;

import javax.print.attribute.standard.Compression;

public class BinarySearchTree <T> {

    private Node root;

    //Constructor of BST
    public BinarySearchTree(){
        root=null;
    }

    //class node
    private class Node <T extends Comparable<T>> {

        private T key;
        private Node left;
        private Node right;

        public Node(T key){
            this.key=key;
            left=null;
            right=null;
        }

        //Methods for Node class
    }

    private int height(Node node){
        if(node==null){
            return 0;
        }
        return 1+Math.max(height(node.left),height(node.right));
    }

    public int height(){
        return height(root);
    }

    private int size(Node node){
        if(node==null){
            return 0;
        }
        return 1 +size(node.left) +size(node.right);
    }

    public int size(){
        return size(root);
    }

    //Depth-first method
    private void inOrder(Node node){
        if (node==null){
            return;
        }
        inOrder(node.left);
        System.out.println(node.key);
        inOrder(node.right);
    }

    public void inOrder(){
        inOrder(root);
    }

    private void preOrder(Node node){
        if (node==null){
            return;
        }
        System.out.println(node.key);
        preOrder(node.left);
        preOrder(node.right);
    }

    public void preOrder(){
        preOrder(root);
    }

    private void postOrder(Node node){
        if (node==null){
            return;
        }
        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.key);
    }

    public void postOrder(){
        postOrder(root);
    }

    //breadth-first
    public void levelTraversal(){
        QueueArrayGeneric<Object> queue= new QueueArrayGeneric<>(size());
        while (!queue.empty()){
            Node node=new Node((Comparable) queue.dequeue());
            System.out.println(node.key);

            if (node.left!=null){
                queue.enqueue(node.left);
            }
            if (node.right!=null){
                queue.enqueue(node.right);
            }
        }
    }

    private Node find(Node node, T key){
        if (node.key.compareTo(key)==0){
            return node;
        }
        else if(node.key.compareTo(key)<0){
            return find(
                    node.right,key);
        }
        else if(node.key.compareTo(key)>0){
            return find(node.left,key);
        }
        else return null;
    }

    public Node find(T key){
        return find(root, key);
    }



}
