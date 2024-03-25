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

    public int length(){
        return size;
    }

    public int getCount(){
        return count;
    }

    public boolean pushFront(T key){
        boolean conditional=false;
        if (!isFull()){
            if (head==null){
                head= new GenericNode<>(key);
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
        if(head==null){
            System.err.println("Error: head is null");
            throw new RuntimeException("head is null");
        }
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
        if (tail==null){
            System.err.println("Error: tail is null");
            throw new RuntimeException("tail is null");
        }
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
                while(current.getNext().getNext()!=null){
                    current=current.getNext();
                }
                T item=(T) current.getNext().getData();
                tail=current;
                tail.setNext(null);
                count--;
                return item;
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
        GenericNode current=head;
        if(isEmpty()){
            System.err.println("Error: linked list is empty");
            throw new RuntimeException("linked list is empty");
        }

        else{
            if(find(key)){
                if(count==1){
                    T item= (T) current.getData();
                    head=tail=null;
                    count--;
                    return item;
                }

                else if(current.getData()==key) {
                    head = current.getNext();
                    count--;
                    return (T) current.getData();
                }
                else {
                    while(current.getNext().getData()!=key) {
                        current = current.getNext();
                    }
                    if(current.getNext()==tail) {
                        T item = (T) current.getNext().getData();
                        tail = current;
                        tail.setNext(null);
                        count--;
                        return item;
                    }

                    else {
                        T item=(T) current.getNext().getData();
                        current.setNext(current.getNext().getNext());
                        count--;
                        return item;
                    }
                }

                }
            else{
                System.err.println("Error: key isn't in the list");
                throw new RuntimeException("key isn´t in the list");
                        }
                    }
    }

    public boolean addBefore(T key, GenericNode node){
        boolean inserted=false;
        if (node==head){
            inserted=pushFront(key);
        }
        else{
            GenericNode current=head;
            while(current.getNext()!=node){
                current=current.getNext();
            }
            GenericNode nodeNew=new GenericNode<>(key);
            nodeNew.setNext(current.getNext());
            current.setNext(nodeNew);
            count++;
            inserted=true;
        }
        return inserted;
    }

    public String toString(){
        String values="";
        GenericNode current=head;
        if(!isEmpty() && getCount()!=1){
            while (current.getNext()!=null){
                values+=""+current.getData()+", ";
                current=current.getNext();
            }
            values+=""+current.getData();
            String f="["+values+"]";
            return f;
        }

        else if(!isEmpty() && getCount()==1){
            values+="["+current.getData()+"]";
            return values;
        }

        else{
            return "[]";
        }
    }



    public static void main(String [] args) {
        UnorderedLinkedList<Integer> linkedlist = new UnorderedLinkedList<>(4);
        linkedlist.pushFront(4);
        //System.out.println(linkedlist);
    }
}
