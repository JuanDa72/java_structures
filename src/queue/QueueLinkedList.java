package queue;
import list.GenericNode;
import list.UnorderedLinkedList;

public class QueueLinkedList <T> {

    public static int defaultSize=20;

    UnorderedLinkedList qList;

    public QueueLinkedList(int n){
        qList=new UnorderedLinkedList<>(n);
    }

    public QueueLinkedList(){
        this(defaultSize);
    }

    public boolean isEmpty(){
        return qList.getCount()==0;
    }

    public boolean isFull(){
        return qList.getCount()==qList.length();
    }

    public T dequeue(){
        return (T) qList.popFront();
    }

    public boolean enqueue(T key){
        return (boolean) qList.pushBack(key);
    }

    public int getCount(){
        return qList.getCount();
    }

    public int length(){
        return qList.length();
    }

    @Override
    public String toString() {
        return qList.toString();
    }

    public static void main(String [] args) {
        QueueLinkedList cola = new QueueLinkedList();
        System.out.println(cola.enqueue(5));
        cola.enqueue(4);
        cola.enqueue(8);
        System.out.println(cola);
        System.out.println(cola.dequeue());
        System.out.println(cola);
        cola.dequeue();
        cola.dequeue();
        cola.dequeue();
    }
}
