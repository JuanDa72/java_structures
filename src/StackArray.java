import java.lang.RuntimeException;
public class StackArray {
    //Esta es la primera implementación que sale en el libro, pagina 29

    private static int size=3;
    private int top;
    private int [] sArray;

    //Constructor
    //En este constructor no pasamos parametro y tenemos el tamaño por defecto
    public StackArray(){
        this(size);
    }

    //En este constructor podemos cambiar el tamaño del stack en tiempo de ejecución mediante el parametro n
    public StackArray(int n){
        top=0;
        sArray=new int[n];
    }

    //value returning methods
    public boolean empty(){
     return top<=0;
    }

    public boolean full(){
        return top>=sArray.length;
    }

    public int pop() {
        //Esta patte de runTimeException no funciona, por lo que la cambiaremos por una simple impresión y retornar
        //un numero extraño, sin embargo, esto no es una correcta implementación del codigo
        if (empty()) {
            System.out.println("Stack is empty");
            return -1234456778;
        } else {
            top--;
            return sArray[top];
        }
    }

    //void methods
    public void push(int value){
        if (full()){
            System.out.println("Stack is full");
        }

        else {
            sArray[top]=value;
            top++;
        }

    }

    //pruebas
    public static void main(String [] args){
        //pruebas con un array en el que no cambiamos el tamaño. tamaño igual a 3
        StackArray myStack=new StackArray();
        myStack.push(5);
        myStack.push(3);
        myStack.push(10);
        //myStack.pop();
        System.out.println(myStack.full());
        //Los metodos parecen funcionar correctamente

    }


}