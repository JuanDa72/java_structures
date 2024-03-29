package list;
import list.GenericUnorderedArrayList;

import java.util.Scanner;

public class problem2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numCases= sc.nextInt();
        sc.nextLine();
        int times[]=new int[numCases];

        for(int k=0; k<numCases; k++){
            int number_elements = sc.nextInt();
            int position = sc.nextInt();

            sc.nextLine();

            String values = sc.nextLine();
            String list[] = values.split(" ");

            UnorderedLinkedList listJobs = new UnorderedLinkedList<Integer>(number_elements);

            for (String element : list) {
                listJobs.pushBack(Integer.parseInt(element));
            }

            //System.out.println(listJobs);
            int time=0;
            boolean print=false;
            while (!print){
                int firstValue=(int) listJobs.getValue(0);
                int max= (int) listJobs.getValue(0);
                int maxPosition=0;
                for(int j=1; j<listJobs.getCount(); j++){
                    if((int) listJobs.getValue(j)>max){
                        max=(int) listJobs.getValue(j);
                        maxPosition=j;
                    }
                }

                //System.out.println(max+" "+maxPosition);

                if(max!=firstValue){
                    listJobs.erase(firstValue);
                    listJobs.erase(max);
                    listJobs.pushBack(firstValue);
                    listJobs.pushFront(max);
                }

                //System.out.println(listJobs);
                //System.out.println(position);

                if(position==0){
                    position= listJobs.getCount()-1;
                }
                else if(position==maxPosition){
                    position=0;
                }

                listJobs.popFront();
                if(position==0){
                    //System.out.println("fjjf");
                    time++;
                    print=true;
                }

                else{
                    time++;
                    position--;
                }
            }
            times[k]=time;
        }

        for(int t: times){
            System.out.println(t);
        }

    }

}