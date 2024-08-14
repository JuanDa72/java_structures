package list;
import list.UnorderedLinkedList;
import list.GenericNode;

public class testLinkedList {

    public static void main(String [] args){

        UnorderedLinkedList<Integer> listaEnlazada=new UnorderedLinkedList<>(4);
        listaEnlazada.pushBack(7);
        listaEnlazada.pushBack(5);
        listaEnlazada.pushBack(1);
        listaEnlazada.pushBack(5);
        int sum=0;
        GenericNode n=listaEnlazada.getHead();
        n=n.getNext();
        n=n.getNext();
        int z= (int) n.getNext().getData();
        sum+=z;
        n=n.getNext();
        n=n.getNext();
        //System.out.println(sum);
        sum+=5;
        System.out.println(n);


    }
}