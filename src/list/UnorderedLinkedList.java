package list;
import list.GenericNode;

public class UnorderedLinkedList<T> {

    private GenericNode head,tail;

    private int size,count;

    public UnorderedLinkedList(int n){
        size=n;
        head=null;
        tail=null;
        count=0;
    }

    public boolean isEmpty(){
        return count==0;
    }

    public boolean isFull(){
        return count==size;
    }

    public boolean pushFront(T key){
        boolean conditional=false;
        if (!isFull()){
            if (head==null){
                head= new GenericNode(key);
                conditional=true;
                count++;
                tail=head;
            }
            else{
                GenericNode node=new GenericNode<>(key);
                node.setNext(head);
                this.head=node;
                conditional=true;
                count++;
            }
        }
        return conditional;
    }

    public T keyTopFront(){
        return (T) head.getData();
    }



    public static void main(String [] args){
        UnorderedLinkedList<Integer> linkedlist=new UnorderedLinkedList<>(4);
        System.out.println(linkedlist.pushFront(3));
        System.out.println(linkedlist.pushFront(2));
    }

}
