import java.util.Scanner;
import list.UnorderedLinkedList;
import stackR.StackArrayGeneric;

public class Main {
    public static void main(String[] args) {

        Scanner sc=new Scanner(System.in);
        String text=sc.nextLine();

        list.UnorderedLinkedList<Character> list=new list.UnorderedLinkedList<>(text.length());

        int j=0;
        while(j<text.length()){
            char c=text.charAt(j);
            Character cc=Character.valueOf(c);
            if(!cc.equals('[')){
                list.pushBack(cc);
                j++;
            }
            else{
                boolean end=false;
                StackArrayGeneric<Character> stack=new StackArrayGeneric<>();
                while(!end){
                    int start=0;
                    j++;
                    c=text.charAt(j);
                    cc=Character.valueOf(c);
                    if(!cc.equals(']') && !cc.equals('[')){
                        //System.out.println("ent");
                        stack.push(cc);
                    }
                    else if(!cc.equals(']') && cc.equals('[')){
                        j++;
                        start++;
                    }

                    else{
                        j++;
                        if(start==0){
                            end=true;
                        }
                        else{
                            start--;
                        }
                    }
                }
                int counter=stack.getCount();
                for (int k=0; k<counter ;k++) {
                    //System.out.println(k);
                    list.pushFront(stack.pop());
                }
            }
        }

        StringBuilder sb = new StringBuilder();

        for(int i=0; i<list.getCount(); i++){
            sb.append(list.getValue(i));
        }

        System.out.println(sb);


    }
}