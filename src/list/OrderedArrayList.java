package list;

import java.util.Scanner;

public class OrderedArrayList {

    private static int size=15;

    private int count;
    private int position;

    private int[] lArray;

    public OrderedArrayList(int n){
        lArray=new int[n];
        count=position=0;
    }

    public OrderedArrayList(){
        this(size);
    }

    public boolean isEmpty(){
        return count==0;
    }

    public boolean isFull(){
        return count==lArray.length;
    }

    public int getCount(){
        return this.count;
    }

    private void getPosition(int item){
        position=0;
        while (position<count){
            if(lArray[position]<item){
                position++;
            }

            else{
                break;
            }

        }
    }

    private boolean search(int item){
        int counter=0;
        boolean stop=false;
        boolean found=false;
        while(counter<count && stop==false){
            if (lArray[counter]<item){
                counter++;
            }
            else if(lArray[counter]==item){
                found=true;
                stop=true;
            }

            else{
                stop=true;
            }
        }
        return found;
    }

    public boolean delete(int item){
        boolean deleted=false;
        if (!isEmpty()){
            if (search(item)){
                getPosition(item);
                for(int i=position; i<count-1; i++){
                    lArray[i]=lArray[i+1];
                }
                count--;
                deleted=true;
            }
        }
        else{
            System.out.println("List is empty");
        }
    return deleted;
    }


        public boolean insert ( int item){
            boolean inserted = false;
            if (!isFull()) {
                getPosition(item);
                for (int i = count; i > position; i--) {
                    lArray[i] = lArray[i - 1];
                }
                lArray[position] = item;
                inserted = true;
                count++;
            } else {
                System.out.println("List is full");
            }
            return inserted;
        }

    public int popFront() {
    int number = lArray[0];
    boolean deleted = delete(number);
    if (deleted) {
        return number;
        }
    else {
        System.out.println("Error: list is empty");
        throw new RuntimeException("List is empty");
        }
    }

        public String toString () {
            String numbers = "";

            if(!isEmpty()){
                for (int i = 0; i < count - 1; i++) {
                    String number_count = "" + lArray[i];
                    numbers += number_count + ", ";
                }
                numbers += lArray[count - 1] + "";
                String list = "[" + numbers + "]";
                return list;
            }

            else{
                return "List is empty";
            }
        }

        public static void main (String[]args){
            Scanner sc=new Scanner(System.in);
            OrderedArrayList list=new OrderedArrayList(4);
            System.out.println(list.insert(4));
            list.insert(2);
            list.insert(1);
            list.insert(0);
            System.out.println(list.delete(1));
            System.out.println(list.getCount());
            System.out.println(list.isEmpty());
            System.out.println(list.isFull());
            System.out.println(list);
        }
    }



