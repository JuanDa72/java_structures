package trees;
import queue.QueueArrayGeneric;

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
        private Node parent;

        public Node(T key){
            this.key=key;
            left=null;
            right=null;
            parent=null;
        }

        public String toString(){
            return key.toString();
        }

        //Methods for Node class
    }

    private int height(Node node){
        if(node==null){
            return 0;
        }
        return 1+Math.max(height(node.left),height(node.right));
        //return 1+Math.max(0,1);
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
        if(node==null) {
            return null;
        }
        else{
            if (node.key.compareTo(key)==0){
                return node;
            }
            else if(node.key.compareTo(key)<0){
                return find(node.right,key);
            }
            else if(node.key.compareTo(key)>0){
                return find(node.left,key);
            }
            else{
                return null;
            }
        }
    }

    public Node find(T key){
        if (root==null){
            return null;
        }
        return find(root, key);
    }

    private Node leftDescendent(Node node){
        if (node.left==null){
            return node;
        }
        else{
            return leftDescendent(node.left);
        }
    }

    private Node rightAncestor(Node node){
        if (node.key.compareTo(node.parent.key)<0){
            return node.parent;
        }
        else{
            return rightAncestor(node.parent);
        }
    }

    private Node next(T key){
        //Confiando en que el método find funcione correctamente
        Node node=find(key);
        if (node==null){
            System.err.println("Error: Key not in tree");
            throw new RuntimeException("Key not in tree");
        }
        else{
            if (node.right!=null){
                return leftDescendent(node.right);
            }
            else{
                return rightAncestor(node);
            }
        }
    }

    //Implement of range search (on hold)

    public Node insert(T key){
        if(find(key)==null){
            Node current=root;
            if(root==null){
                Node node=new Node((Comparable) key);
                root=node;
                return node;
            }
            while(current!=null){
                if(current.key.compareTo(key)>0){
                    if(current.left==null){
                        Node node=new Node((Comparable) key);
                        current.left=node;
                        node.parent=current;
                        return node;
                    }
                    else{
                        current=current.left;
                    }
                }
                else{
                    if(current.right==null){
                        Node node=new Node((Comparable) key);
                        current.right=node;
                        node.parent=current;
                        return node;
                    }
                    else{
                        current=current.right;
                    }
                }
            }
        }
        System.err.println("Error: key is already in the tree");
        throw new RuntimeException("Key is already in the tree");
    }

    public static void main(String []args){
        BinarySearchTree<Integer> bst=new BinarySearchTree<>();
        bst.insert(17);
        bst.insert(8);
        bst.insert(14);
        bst.insert(3);
        bst.insert(20);
        bst.insert(5);
        bst.postOrder();
    }

}
