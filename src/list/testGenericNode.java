package list;
import list.GenericNode;

public class testGenericNode {

    public static void main(String [] args){
        GenericNode<Integer> p =new GenericNode<>(14);
        GenericNode<Integer> q=new GenericNode<>(6);
        p.setNext(q);
        q.setNext(p);
        System.out.println(q.getNext());

    }

}
