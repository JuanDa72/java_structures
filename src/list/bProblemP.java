package list;

import java.util.Scanner;

public class bProblemP {

    private static class LinkedList <T> {

        private class GenericNode <T> {

            private T value;

            private GenericNode next;

            public GenericNode(T item){
                this.value=item;
                next=null;
            }

            public T getData(){
                return this.value;
            }

            public GenericNode getNext(){
                return this.next;
            }

            public void setValue(T item){
                this.value=item;
            }

            public void setNext(GenericNode node){
                next=node;
            }

        }

        private GenericNode head,tail;

        private int size,count;

        public LinkedList(int n){
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

        public boolean addBefore(T key,GenericNode node){
            boolean inserted=false;
            if (node==head){
                inserted=pushFront(key);
            }
            else{
                GenericNode current=head;
                while(current.getNext()!=node){
                    current=current.getNext();
                }
                GenericNode nodeNew=new GenericNode<T>(key);
                nodeNew.setNext(current.getNext());
                current.setNext(nodeNew);
                count++;
                inserted=true;
            }
            return inserted;
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
    }

    public static void main(String [] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (!line.isEmpty()) {
                String [] stringArray=line.split(" ");
                int [] intArray=new int[stringArray.length];
                for(int i=0; i<stringArray.length; i++){
                    intArray[i]=Integer.parseInt(stringArray[i]);
                }
                boolean print=false;
                LinkedList<Integer> linked=new LinkedList<>(intArray.length);
                int dad=intArray[0];
                int j=1;
                int value=0;
                linked.pushFront(dad);
                while(j< intArray.length){
                    if(intArray[j]<0){
                        if(Math.abs(intArray[j])<Math.abs(dad) && (Math.abs(value)+Math.abs(intArray[j]))<Math.abs(dad)) {
                            dad = intArray[j];
                            linked.pushFront(dad);
                            j++;
                            value=0;
                        }
                        else{
                            System.out.println(":-( Try again.");
                            print=true;
                            break;
                        }
                    }
                    else{
                        if(intArray[j]==Math.abs(dad)){
                            value=Math.abs(linked.keyTopFront());
                            //System.out.println(intArray[j]);
                            linked.popFront();
                            if(!linked.isEmpty()){
                                dad=linked.keyTopFront();
                            }
                            else{
                                dad=0;
                            }
                            j++;
                        }
                        else{
                            System.out.println(":-( Try again.");
                            print=true;
                            break;
                        }
                    }
                }
                if (linked.isEmpty() && print==false){
                    System.out.println(":-) Matrioshka!");
                }
                else if(!print){
                    System.out.println(":-( Try again.");
                }

            } else {
                break;
            }
        }
    }
}

