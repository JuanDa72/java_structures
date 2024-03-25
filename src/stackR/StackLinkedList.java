package stackR;
import list.GenericNode;
import list.UnorderedLinkedList;

public class StackLinkedList <T> {

    public static int defaultSize=10;

    UnorderedLinkedList sList;

    public StackLinkedList(int n){
        sList=new UnorderedLinkedList(n);
    }

    public StackLinkedList(){
        this(defaultSize);
    }

    public boolean isEmpty(){
        return sList.getCount()==0;
    }

    public boolean isFull(){
        return sList.getCount()==sList.length();
    }

    public T pop(){
        return (T) sList.popFront();
    }

    public boolean push(T key){
        return (boolean) sList.pushFront(key);
    }

    public String toString(){
        return sList.toString();
    }

    public int getCount(){
        return sList.getCount();
    }

    public int length(){
        return sList.length();
    }

    public static void main (String[] args){
        StackLinkedList stack=new StackLinkedList(3);
        System.out.println(stack.isFull());
        stack.push(3);
        stack.push(8);
        stack.push(4);
        System.out.println(stack.pop());
        System.out.println(stack);
        stack.pop();
        System.out.println(stack);
        System.out.println(stack.getCount());
        System.out.println(stack.length());
    }

}
