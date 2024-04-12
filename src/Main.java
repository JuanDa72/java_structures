import java.util.Scanner;
import list.UnorderedLinkedList;
import stackR.StackArrayGeneric;

public class Main {
    public static void main(String[] args) {

        Scanner sc=new Scanner(System.in);
        String text=sc.nextLine();

        list.UnorderedLinkedList<Character> list=new list.UnorderedLinkedList<>(text.length());

        boolean start=false;
        boolean end=false;
        int j=0;
        int place = 0;
        while(j<text.length()) {
            //System.out.println(j);
            char c = text.charAt(j);
            if (c == '[') {
                //System.out.println("incio");
                start=true;
                j++;
                place=0;
                }


            else if(c==']'){
                //System.out.println("j");
                j++;
                //end=true;
                start=false;
                place=0;
                }

            else{
                if(start) {
                    if (place == 0) {
                        //System.out.println(j);
                        list.pushFront(c);
                        place++;
                        j++;
                        //7System.out.println(list);
                    } else {
                        //System.out.println(j+" before");
                        list.addAfter(c, place-1);
                        j++;
                        place++;
                        //System.out.println(list);
                    }
                }

                else if(end){
                    //System.out.println("start else");
                    j++;
                    }

                else{
                    list.pushBack(c);
                    j++;
                    //System.out.println("nao");
                }
            }
        }

        //System.out.println(list);

        StringBuilder sb = new StringBuilder();

        int counter=list.getCount();

        for(int i=0; i<counter; i++){;
            sb.append(list.popFront());
        }

        System.out.println(sb);

    }
}