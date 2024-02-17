package stackR;

public class StackArrayGeneric <T> {
    //Debemos intercambiar cualquier int por T para establecer el generico
    private static final int size=3;

    private int top;

    private T[] sArray;


    //Constructor
    public StackArrayGeneric(int n){
        sArray= (T[]) new Object [n];
        top=0;
    }

    public StackArrayGeneric(){
        this(size);
    }

    //returning methods
    public boolean empty(){
        return top<=0;
    }

    public boolean full(){
        return top>=sArray.length;
    }

    public T pop(){
        if (empty()){
            System.err.println("Error: Stack is empty");
            throw new RuntimeException("Stack is empty");
        }

        else {
            top--;
            return sArray[top];
        }
    }

    //void methods
    public void push(T item){
        if (full()){
            System.err.println("Error: Stack is full");
            throw new RuntimeException("Stack is full");
        }

        else {
            sArray[top]=item;
            top++;
        }
    }

    public static void main(String [] args){
        //pruebas
        //La forma de instanciar la clase es como si fuera un arrayList

        StackArrayGeneric<String> stack=new StackArrayGeneric<>(5);
        stack.push("alone");
        stack.push("nobody");
        stack.push("life");

        while (!stack.empty()){
            System.out.println(stack.pop()+" ");
        }
        //Parece funcionar correctamente
    }


}
