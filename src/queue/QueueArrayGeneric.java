package queue;

public class QueueArrayGeneric <T> {

    private static int size=5;
    private int front, rear, count;
    private T[] qArray;

    public QueueArrayGeneric(int n){
        qArray= (T[]) new Object [n];
        front=rear=count=0;
    }

    public QueueArrayGeneric(){
        this(size);
    }

    public boolean empty (){
        return count<=0;
    }

    public boolean full () {
        return count>= qArray.length;
    }

    public T dequeue () {
        if (empty()) {
            System.err.println("Error: Queue is empty");
            throw new RuntimeException("Queue is empty");
        }
        T item=qArray[front];
        count--;
        front=(front+1)% qArray.length;
        return item;
    }

    public int getCount(){
        return count;
    }

    //Void Method
    public void enqueue (T item) {
        if (full()) {
            System.err.println("Error: queue is full");
            throw new RuntimeException("Queue is full");
        }
        qArray[rear]=item;
        count++;
        rear=(rear+1)%qArray.length;
    }

    public static void main(String [] args) {
        /*
        QueueArrayGeneric <Integer> cola=new QueueArrayGeneric<> ();
        cola.enqueue(5);
        cola.enqueue(1);
        cola.enqueue(4);
        cola.enqueue(2);
        cola.enqueue(9);
        cola.dequeue();
        cola.enqueue(1);
        cola.dequeue();
        cola.dequeue();
        cola.dequeue();
        cola.dequeue();
        cola.dequeue();
        //cola.dequeue();
        System.out.println(cola.getCount());
        System.out.println(cola.empty());
        System.out.println(cola.full());
        */

        //String queue
        QueueArrayGeneric <String> cola=new QueueArrayGeneric<>(3);
        cola.enqueue("life");
        cola.enqueue("nieve");
        cola.enqueue("time");
        cola.dequeue();
        cola.enqueue("pendiente");
        cola.dequeue();
        cola.dequeue();
        cola.dequeue();
        cola.enqueue("myself");
        System.out.println(cola.empty());
        System.out.println(cola.full());
        System.out.println(cola.getCount());
    }

}
