package stackR;

import java.util.Scanner;
import java.util.*;

public class IsPalindrome {

    public static void main(String [] args) {

        int i,j;
        String word;
        Scanner sc=new Scanner(System.in);
        System.out.println("Introduce una palabra para determinar si es palindrome o no: ");
        word=sc.nextLine();
        StackArrayGeneric<Character> stack= new StackArrayGeneric<>(word.length()/2);
        //No olvides que tenemos un constructor que nos permite definir el tamaño del array

        boolean isPalindrome=true;

        for (i=0; i<word.length()/2; i++){
            stack.push(word.charAt(i));
        }

        j=i;

        if (word.length()%2!=0){
            j++;
        }

        //System.out.println(isPalindrome);

        while (!stack.empty() && isPalindrome){
            if (stack.pop()!=word.charAt(j)) {
                isPalindrome = false;
                //System.out.println(word.charAt(j));
                //System.out.println(j);
            }
            j++;
        }

        if (isPalindrome){
            System.out.println("La palabra es palindrome");
        }

        else {
            System.out.println("La palabra NO es palindrome");
        }
        //Parece funcionar correctamente

    }
}
