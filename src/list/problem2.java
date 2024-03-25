package list;
import list.OrderedArrayList;

import java.util.Scanner;

public class problem2 {

    public static void main(String [] args){
        Scanner sc=new Scanner(System.in);
        int number_elements=sc.nextInt();
        int position=sc.nextInt();

        sc.nextLine();

        String values=sc.nextLine();
        String list[]=values.split(" ");
        OrderedArrayList listJobs=new OrderedArrayList(list.length);

        for (String element: list){
            element="-"+element;
            listJobs.insert(Integer.parseInt(element));
        }

        for(String element: list){
            System.out.println(element);
        }
        System.out.println(listJobs);

    }
}