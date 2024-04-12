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

    public boolean addBefore(T key, int position){
        while(!isFull() && position<=getCount()-1){
            boolean inserted=true;
            int counter=0;
            GenericNode current=head;
            if(position==0){
                inserted=pushFront(key);
            }
            else{
                while(counter<position-1){
                    current=current.getNext();
                    counter++;
                }
                GenericNode post=current.getNext();
                GenericNode n=new GenericNode<>(key);
                current.setNext(n);
                n.setNext(post);
                inserted=true;
                count++;
            }
            return inserted;
        }
        System.err.println("Error: Linked list is full or position is out of range");
        throw new RuntimeException();
        }

    public boolean addAfter(T key, int position){
        while(!isFull() && position<getCount()){
            boolean inserted=false;
            int counter=0;
            GenericNode current=head;
            if (position==getCount()-1){
                inserted=pushBack(key);
            }
            else{
                while(counter<position){
                    current=current.getNext();
                    counter++;
                }
                GenericNode post=current.getNext();
                GenericNode n=new GenericNode(key);
                current.setNext(n);
                n.setNext(post);
                inserted=true;
                count++;
            }
            return inserted;
        }
        System.err.println("Error: List is full or position is  out of range");
        throw new RuntimeException("List is full or position is out of range");
    }



    public T getValue(int position){
        int counter=0;
        GenericNode current=head;
        if(!isEmpty() && position<count){
            while(counter<position){
                current=current.getNext();
                counter++;
            }
            return (T) current.getData();
        }
        else{
            System.err.println("Error: List is empty or position is out of range");
            throw new RuntimeException("List is empty or position is out of range");
        }
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
        System.out.println(linkedlist.addBefore(7,0));
        System.out.println(linkedlist);
        System.out.println(linkedlist.getCount());
    }
}
