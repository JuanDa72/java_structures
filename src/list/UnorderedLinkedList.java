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

    public int getCount(){
        return count;
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
                //GenericNode current=head;
                //while(current.getNext()!=null){
                //    current=current.getNext();
                //}
                //tail=current;
            }
        }
        return conditional;
    }

    public T keyTopFront(){
        return (T) head.getData();
    }

    public T popFront(){
        if (head==null){
            System.err.println("Error: Linked list is empty");
            throw new RuntimeException("Linked list is empty");
        }
        else{
            GenericNode current=head;
            head=head.getNext();
            if (count==0){
                tail=null;
            }
            count--;
            return (T) current.getData();
        }
    }

    public boolean pushBack(T key){
        boolean inserted=false;
        if(!isFull()) {
            GenericNode node=new GenericNode(key);
            if(tail==null){
                head=tail=node;
                count++;
                inserted=true;
        }
            else {
                tail.setNext(node);
                tail = tail.getNext();
                count++;
                inserted=true;
            }
        }
        return inserted;
    }

    public T keyTopBack(){
        return (T) tail.getData();
    }

    public T popBack(){
        GenericNode current=head;
        if(head==null){
            System.err.println("Error: linked list is empty");
            throw new RuntimeException("Linked llist is empty");
        }
        else{
            if (head==tail){
                head=tail=null;
                count--;
                return (T) current.getData();
            }
            else{
                while(current.getNext()!=null){
                    current=current.getNext();
                }
                tail=current;
                tail.setNext(null);
                count--;
                return (T) current.getData();
            }
        }
    }

    public boolean find(T key){
        boolean found=false;
        GenericNode current=head;
        while(current!=null && !found){
            if(current.getData()==key){
                found=true;
            }
            else{
                current=current.getNext();
            }
        }
        return found;
    }

    public T erase(T key){
        boolean deleted=false;
        GenericNode current=head;
        if(isEmpty()){
            System.err.println("Error: linked list is empty");
            throw new RuntimeException("linked list is empty");
        }

        else{
            while(current!=null && !deleted){
                if(current.getData()==key){
                    if(current==head && current==tail){
                        head=tail=null;
                        count--;
                    }
                    else if(current==head  && current!=tail){
                        head=current.getNext();
                        count--;
                    }
                    else  if(current!=head && current==tail){
                        GenericNode p=head;
                        while(p.getNext()!=null){
                            p=p.getNext();
                        }
                        tail=p;
                        tail.setNext(null);
                        count--;
                    }

                    deleted=true;
                }
                else{
                    current=current.getNext();
                }
            }
        }
        return (T) current.getData();
    }



    public static void main(String [] args){
        UnorderedLinkedList<Integer> linkedlist=new UnorderedLinkedList<>(4);
        System.out.println(linkedlist.pushFront(3));
        System.out.println(linkedlist.pushFront(2));
        //System.out.println(linkedlist.popFront());
        //System.out.println(linkedlist.pushBack(8));
        linkedlist.pushBack(7);
        linkedlist.pushBack(34);
        System.out.println(linkedlist.find(2));
        System.out.println(linkedlist.getCount());

    }

}
