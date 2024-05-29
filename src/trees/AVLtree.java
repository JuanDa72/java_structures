package trees;

import queue.QueueArrayGeneric;

import java.util.ArrayList;

public class AVLtree <T> {

    private Node root;

    //constructor
    public AVLtree(){
        root=null;
    }

    //class Node
    private class Node <T extends Comparable<T>> {

        private T key;
        private Node left;
        private Node right;
        private Node parent;

        private int height;

        public Node(T key){
            this.key=key;
            left=null;
            right=null;
            parent=null;
        }

        public String toString(){
            return key.toString();
        }
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

    public void levelTraversal(){
        QueueArrayGeneric<Node> queue= new QueueArrayGeneric<>(size());
        if(root==null){
            System.out.println("Tree is empty");
        }
        else{
            queue.enqueue(root);
            while (!queue.empty()){
                Node node=queue.dequeue();
                System.out.println(node.key);

                if (node.left!=null){
                    queue.enqueue(node.left);
                }
                if (node.right!=null){
                    queue.enqueue(node.right);
                }
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

    //return the left descendent if node has
    private Node leftDescendent(Node node){
        if (node.left==null){
            return node;
        }
        else{
            return leftDescendent(node.left);
        }
    }

    private Node rightAncestor(Node node){
        if(node.parent==null){
            return null;
        }
        else{
            if (node.key.compareTo(node.parent.key)<0){
                return node.parent;
            }
            else{
                return rightAncestor(node.parent);
            }
        }
    }

    private Node next(T key){
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

    public ArrayList<T> rangeSearch(T lower, T higher){
        ArrayList<T> range=new ArrayList<>();
        Node limit=find(lower);
        if (limit==null){
            insert(lower);
            limit=next(lower);
            delete(lower);
        }
        while(limit.key.compareTo(higher)<=0){
            range.add((T) limit.key);
            limit=next((T) limit.key);
        }
        return range;
    }

    private void rotateRight(Node node){
        //System.out.println("nodo "+node);
        Node p=node.parent;
        Node y=node.left;
        Node b=y.right;
        if(node.parent!=null){
            y.parent=p;
            if(leftSon(p,node)){
                p.left=y;
            }
            else if(rightSon(p,node)){
                p.right=y;
            }
        }
        else{
            y.parent=null;
        }
        node.parent=y;
        node.left=y.right;
        y.right=node;
        if(b!=null){
            b.parent=node;
        }
        if(node==root){
            root=y;
        }
        node.height=height(node);
        Node start=y.left;
        if(start==null){
            start=y;
            y.height=height(y);
        }
        else{
            y.left.height=height(y.left);
        }
        if(node.right!=null){
            node.right.height=height(node.right);
        }
        //node.right.height=height(node.right);
        while(start!=null){
            start.height=height(start.parent);
            start=start.parent;
        }
    }

    private void rotateLeft(Node node){
        Node p=node.parent;
        Node y=node.right;
        Node c=y.left;
        if(node.parent!=null){
            y.parent=p;
            if(leftSon(p,node)){
                p.left=y;
            }
            else if(rightSon(p,node)){
                p.right=y;
            }
        }
        else{
            y.parent=null;
        }
        node.parent=y;
        node.right=c;
        y.left=node;
        if(c!=null){
            c.parent=node;
        }
        if(node==root){
            root=y;
        }
        node.height=height(node);
        Node start=y.right;
        if(start==null){
            start=node;
            y.height=height(y);
        }
        else{
            y.left.height=height(y.left);
        }
        if(node.left!=null){
            node.left.height=height(node.left);
        }
        //node.left.height=height(node.left);
        while(start!=null){
            start.height=height(start.parent);
            start=start.parent;
        }
    }

    private void rebalanceRight(Node node){
        Node m=node.left;
        if(height(m.right)>height(m.left)){
            rotateLeft(m);
        }
        rotateRight(node);
        //Maybe make the adjustHeight here
    }

    private void rebalanceLeft(Node node){
        Node m=node.right;
        if(height(m.left)>height(m.right)){
            rotateRight(m);
         }
        rotateLeft(node);
    }

    private void rebalance(Node node){
        if(node!=null){
            node.height=height(node);
            //System.out.println("rebalance "+node+" "+node.height);
            Node p=node.parent;
            //System.out.println(p);
            if(height(node.left)>height(node.right)+1){
                //System.out.println("Entrada 1 "+node);
                rebalanceRight(node);
            }
            if(height(node.right)>height(node.left)+1){
                //System.out.println("Entrada 2 "+node);
                rebalanceLeft(node);
            }
            node.height=height(node);
            //System.out.println(node.height);
            if(p!=null){
                //System.out.println("Entrada 3 "+p);
                rebalance(p);
            }
        }
        else{
            return;
        }
    }

    //We must modify this method
    private void insert(T key){
        if(find(key)!=null){
            System.err.println("Error: key is already in the tree");
            throw new RuntimeException("Key is already in the tree");
        }
        else{
            //System.out.println("Insert else");
            Node current=root;
            if(root==null){
                Node node=new Node((Comparable) key);
                root=node;
            }
            else{
                while(current!=null){
                    if(current.key.compareTo(key)>0){
                        if(current.left==null){
                            Node node=new Node((Comparable) key);
                            current.left=node;
                            node.parent=current;
                            break;
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
                            break;
                        }
                        else{
                            current=current.right;
                        }
                    }
                }
            }
        }
        Node n=find(key);
        //System.out.println("Nodo insertado "+ n);
        rebalance(n);
    }


    //Determina si el hijo es izquierdo
    private boolean leftSon(Node father,Node son){
        if (father.left==son){
            return true;
        }
        else{
            return false;
        }
    }

    private boolean rightSon(Node father, Node son){
        if(father.right==son){
            return true;
        }
        else{
            return false;
        }
    }

    //promueve el hijo derecho a la posición del padre
    private void promote(Node parent, Node son){
        if(leftSon(parent.parent,parent)){
            parent.parent.left=son;
            son.parent=parent.parent;
        }
        else{
            parent.parent.right=son;
            son.parent=parent.parent;
        }
    }

    public void delete(T key){
        Node node=find(key);
        Node start;
        if(node==null){
            System.err.println("Error: key is not in the tree");
            throw new RuntimeException("Key is not in the tree");
        }
        else{
            if (node==root){
                if (root.right==null && root.left==null){
                    root=null;
                    start=null;

                }
                else if(node.right==null){
                    root=node.left;
                    start=null;
                }
                else{
                    //Arreglar esta parte, con el caso que esta en el main
                    Node n=next(key);
                    start=n.parent;
                    //System.out.println("n "+n);
                    n.left=root.left;
                    if(root.left!=null){
                        root.left.parent=n;
                    }
                    if(n.right!=null){
                        promote(n,n.right);
                    }
                    if (node.right!=n){
                        n.right=node.right;
                        node.right.parent=n;
                    }
                    if(leftSon(n.parent,n)){
                        n.parent.left=null;
                    }
                    else if(rightSon(n.parent,n)){
                        n.parent.right=null;
                    }
                    root=n;
                    n.parent=null;
                    //System.out.println(root);
                    //System.out.println(root.right.parent);
                }
            }
            else if(node.right==null){
                if(node==node.parent.left){
                    node.parent.left=node.left;
                    start=node.parent;
                    if(node.left!=null){
                        node.left.parent=node.parent;
                    }
                }
                else{
                    node.parent.right=node.left;
                    start=node.parent;
                    if(node.left!=null){
                        node.left.parent=node.parent;
                    }
                }
            }
            else{
                Node n=next(key);
                //System.out.print("Node "+node);
                //System.out.println("Next "+n);
                //System.out.println();
                if(n.parent==node){
                    start=null;
                }
                else{
                    start=n.parent;
                }
                //System.out.println(n);
                if(n.right==null){
                    if(node.parent.left==node){
                        node.parent.left=n;
                        n.left=node.left;
                        if(node.left!=null){
                            node.left.parent=n;
                        }
                        if(node.right!=n){
                            n.right=node.right;
                            node.right.parent=n;
                        }
                        if(leftSon(n.parent,n)){
                            n.parent.left=null;
                        }
                        else if (rightSon(n.parent,n)){
                            n.parent.right=null;
                        }
                        n.parent=node.parent;
                    }
                    else{
                        node.parent.right=n;
                        n.left=node.left;
                        if(node.left!=null){
                            node.left.parent=n;
                        }
                        //System.out.println(n.parent);
                        //System.out.println(n.parent.left);
                        //System.out.println(n.parent.right);
                        if(node.right!=n){
                            //System.out.println("jhi");
                            //System.out.println(node.right);
                            n.right=node.right;
                            node.right.parent=n;
                        }
                        if(leftSon(n.parent,n)){
                            //System.out.println("hi");
                            n.parent.left=null;
                        }
                        else{
                            n.parent.right=null;
                        }
                        n.parent=node.parent;
                        //Aqui esta el problema, ver mañana
                        //System.out.println(node.right.left);
                    }
                }
                else {
                    //System.out.println("nodo "+node+" node.parent" +node.parent);
                    //System.out.println(node.right);
                    //System.out.println(node.left);
                    if(node.parent.left==node){
                        node.parent.left=n;
                        n.left=node.left;
                        if(node.left!=null){
                            node.left.parent=n;
                        }
                        promote(n,n.right);
                        n.parent=node.parent;
                        if(node.right!=n){
                            n.right=node.right;
                            node.right.parent=n;
                        }
                    }
                    else{
                        node.parent.right=n;
                        n.left=node.left;
                        if(node.left!=null){
                            node.left.parent=n;
                        }
                        promote(n,n.right);
                        if(node.right!=n){
                            n.right=node.right;
                            node.right.parent=n;
                        }
                        n.parent=node.parent;
                        //System.out.println("n  es "+n);
                        //System.out.println("n.parent es"+n.parent);
                        //System.out.println("node.parent es "+node.parent);
                        //System.out.println(n.left);
                        //System.out.println(n.right);
                        //System.out.println(n.right.left);
                        //System.out.println(n.right);
                    }
                }
                if(start==null){
                    start=n;
            }
            }

        }
        rebalance(start);

    }


    //This method must be modified to implement rotations


    public static void main(String [] args){
        AVLtree<Integer> avl=new AVLtree<>();
        avl.insert(23);
        avl.insert(9);
        avl.insert(45);
        avl.insert(8);
        avl.insert(18);
        avl.insert(39);
        avl.insert(47);
        avl.insert(5);
        avl.insert(11);
        avl.insert(21);
        avl.insert(40);
        avl.insert(49);
        avl.delete(23);
        avl.delete(11);
        avl.insert(20);
        avl.delete(21);
        avl.delete(20);
        avl.insert(48);
        avl.delete(39);
        avl.delete(40);
        avl.insert(46);
        avl.delete(18);
        avl.delete(9);
        avl.insert(2);
        avl.delete(5);
        avl.delete(49);
        avl.insert(18);
        avl.delete(2);
        avl.delete(48);
        avl.insert(26);
        avl.delete(45);
        avl.delete(26);
        avl.insert(49);
        avl.delete(18);
        avl.delete(8);
        avl.insert(28);
        avl.delete(28);
        avl.delete(46);
        avl.insert(38);
        avl.delete(38);
        avl.delete(47);
        avl.insert(37);
        avl.delete(37);
        avl.delete(49);
        //avl.delete(74);
        //avl.delete(75);
        //System.out.println();
        avl.levelTraversal();
    }





}
