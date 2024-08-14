package list;
import java.beans.Introspector;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class aProblemP {

    public class GenericUnorderedArrayList<T extends Comparable<T>> {

        private int size=3;

        private int count, position;

        private T[] lArray;

        public GenericUnorderedArrayList(int n){
            lArray=(T[]) new Comparable [n];
            count=position=0;
        }


        public boolean isEmpty(){
            return count==0;
        }

        public boolean isFull(){
            return count==lArray.length;
        }


        public boolean delete(T item){
            boolean deleted=false;
            boolean found=false;
            int position=0;
            if (!isEmpty()){
                for (int i=0; i<getCount(); i++){
                    if ((T) lArray[i]==(T) item){
                        found=true;
                        position=i;
                    }
                    if (found){
                        for(int j=position; j<getCount()-1; j++){
                            lArray[j]=lArray[j+1];
                        }
                        deleted=true;
                        count--;
                        return deleted;
                    }
                    else{
                        return deleted;
                    }
                }

            }
            return deleted;
        }

        public boolean insert(T item){
            boolean inserted=false;
            if(!isFull()){
                lArray[count]=item;
                inserted=true;
                count++;
            }
            else{
                resize();
                lArray[count]=item;
                inserted=true;
                count++;
            }
            return inserted;
        }

        public T getValue(int position){
            return (T) lArray[position];
        }

        public boolean setValue(int position, T item){
            boolean found=false;
            if (position>count-1){
                return found;
            }
            else{
                lArray[position]=item;
                found=true;
                return found;
            }
        }

        public String toString(){
            String elements="";
            for (int i=0; i<count-1; i++){
                String element_count=""+lArray[i];
                elements+=element_count+", ";
            }
            elements+=lArray[count-1]+"";
            String list="["+elements+"]";
            return list;
        }

        public int getCount(){
            return count;
        }

        private void resize(){
            int newSize=size*2;
            T [] newArray=(T[]) new Comparable [newSize];
            System.arraycopy(lArray,0,newArray,0,count);
            lArray=newArray;
            size=newSize;
        }

        public boolean checkRange(int position){
            if(position>count-1){
                return false;
            }
            else{
                return true;
            }
        }
    }

    public GenericUnorderedArrayList<Integer> makeFirstCycle(int a, int b, int c, PriorityQueue<Integer> queue){
        GenericUnorderedArrayList<Integer> list=new GenericUnorderedArrayList<>(100);
        if(c<=a){
            for(int i=0; i<a; i++){
                list.insert(0);
            }
        }
        else{
            int t=c-(a+1);
            for(int i=t;i<b;i++){
                list.insert(1);
            }
            list.insert(0);
        }
        queue.add(list.getCount());
        return list;
    }

    public boolean getStatus(int number){
        return(number==0);
    }

    public void newCycle(ArrayList<GenericUnorderedArrayList> students, int position, int a, int b, GenericUnorderedArrayList ss,PriorityQueue<Integer> queue){
        int counter=0;
        boolean sleep=false;
        int limit= (students.size()/2)+1;
        //System.out.println("limit "+limit);
        for(int i=0; i<students.size();i++){
            if((int) students.get(i).getValue(position)==1){
                //System.out.println(i);
                //System.out.println("durmiendo");
                counter++;
                if(counter==limit){
                    sleep=true;
                    break;
                }
            }
        }
        if(sleep){
            //System.out.println("sleep");
            for(int i=0; i<b; i++){
                ss.insert(1);
            }
            for(int i=0; i<a; i++){
                ss.insert(0);
            }
            //System.out.println(ss);

        }
        else{
            for(int i=0; i<a; i++){
                ss.insert(0);
            }
        }
        //System.out.println(ss);

        queue.add(ss.getCount());
        queue.poll();

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        aProblemP main=new aProblemP();
        int cases=0;


        while (true) {
            int n = sc.nextInt();
            //System.out.println(n);
            if (n == 0) {
                //xSystem.out.println("salir");
                break;
            }
            cases++;
            sc.nextLine();
            ArrayList<GenericUnorderedArrayList> students=new ArrayList<>();
            PriorityQueue<Integer> minHeap=new PriorityQueue<>();

            int[] a = new int[n];
            int[] b = new int[n];
            int[] c = new int[n];

            for (int i = 0; i < n; i++) {
                String[] st = sc.nextLine().split(" ");
                a[i] = Integer.parseInt(st[0]);
                b[i] = Integer.parseInt(st[1]);
                c[i] = Integer.parseInt(st[2]);
            }
            for(int i=0; i<n; i++){
                students.add(main.makeFirstCycle(a[i],b[i],c[i],minHeap));
            }

            int studentIndex=0;
            int time=1;
            boolean awake=false;
            int count=0;
            int maxMinuteWithData=minHeap.peek();
            while(studentIndex<students.size() && time<31){
                maxMinuteWithData=minHeap.peek();
                if(time<=maxMinuteWithData){
                    GenericUnorderedArrayList specificS= students.get(studentIndex);
                    //System.out.println(specificS);
                    //System.out.println("en rango");
                    if(!(main.getStatus((int) specificS.getValue(time-1)))){
                        //System.out.println("dormido");
                        time++;
                        count=0;
                        studentIndex=0;
                    }
                    else{
                        //System.out.println("despierto");
                        studentIndex++;
                        count++;
                        if(count==students.size()){
                            awake=true;
                            System.out.println("Case "+cases+": "+time);
                            break;
                        }
                    }
                }
                else{
                    //System.out.println("nuevo ciclo");
                    for(GenericUnorderedArrayList s: students){
                        if(!(s.checkRange(time-1))){
                            //System.out.println("imprime algooo");
                            main.newCycle(students,time-2,a[students.indexOf(s)],b[students.indexOf(s)],s,minHeap);
                        }
                    }
                }
            }
            if(!awake){
                System.out.println("Case "+cases+": "+"-1");
            }

            //sc.nextLine();

            //for (int i : a) {
                //System.out.println("a " + i);
            //}
            //for (int i : b) {
                //System.out.println("b " + i);
            //}
            //for (int i : c) {
                //System.out.println("c " + i);
            //}
        }

        sc.close();
    }
}
