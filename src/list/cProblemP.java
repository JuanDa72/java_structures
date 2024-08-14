package list;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;
import java.util.Queue;

public class cProblemP {

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

    public static void main(String [] args){
        Scanner sc=new Scanner(System.in);
        int arrayL=sc.nextInt();
        LinkedList<Integer> stack=new LinkedList<>(arrayL);;
        int [] values=new int[arrayL];
        int [] position=new int[arrayL];

        for(int i=0; i<arrayL; i++){
            values[i]=sc.nextInt();
        }

        for(int i=0; i<arrayL; i++){
            while(!stack.isEmpty() && values[stack.keyTopFront()]>=values[i]){
                stack.popFront();
            }
            if(stack.isEmpty()){
                position[i]=0;
            }
            else{
                position[i]=stack.keyTopFront()+1;
            }

            stack.pushFront(i);
        }

        String sf="";

        for(int i=0; i<arrayL; i++){
            sf+=position[i]+" ";
        }

        System.out.println(sf.trim());

    }


}
