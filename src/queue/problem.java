package queue;
import queue.QueueArrayGeneric;

import java.util.Scanner;

public class problem {

    public static void main(String [] args){
        Scanner sc=new Scanner(System.in);
        int number_elements=sc.nextInt();
        int position=sc.nextInt();

        sc.nextLine();

        String values=sc.nextLine();
        String list[]=values.split(" ");
        QueueArrayGeneric queue=new QueueArrayGeneric<Integer>(list.length);

        for (String data: list){
            Integer value=Integer.valueOf(data);
            queue.enqueue(value);
        }

        int sJob=(int) queue.getEspecificPosition(position);
        boolean deleted=false;
        while (!deleted){
            for(int i=0; i<queue.getCount(); i++){
                if ((int) queue.getEspecificPosition(0)<(int) queue.getEspecificPosition(i)){

                }
            }
        }



    }

}
