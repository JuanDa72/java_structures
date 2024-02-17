package stackR;
import java.util.*;

public class StackArrayTest {

    public static void main (String [] args){

        System.out.println("Introduce la cantidad de números que quieres revertir");
        Scanner sc=new Scanner(System.in);
        int size=sc.nextInt();

        StackArray stack=new StackArray(size);
        System.out.println("Introduce un número: ");

        while (!stack.full() ){
            int number=sc.nextInt();
            stack.push(number);
            System.out.println("Introduce un número: ");
        }

        while (!stack.empty()){
            System.out.print(stack.pop() +" ");

        }
        //Funciona bien
    }

}
