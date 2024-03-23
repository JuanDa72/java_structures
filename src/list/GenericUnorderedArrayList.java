package list;

public class GenericUnorderedArrayList<T extends Comparable<T>> {

    private static int size=15;

    private int count, position;

    private T[] lArray;

    public GenericUnorderedArrayList(int n){
        lArray=(T[]) new Comparable [n];
        count=position=0;
    }

    public GenericUnorderedArrayList(){
        this(size);
    }

    public boolean isEmpty(){
        return count==0;
    }

    public boolean isFull(){
        return count==lArray.length;
    }

    public boolean delete(T item){
        boolean deleted=false;
        int i=0;
        if (!isEmpty()){
            while(i<lArray.length && !deleted){
                if(lArray[i]!=null && lArray[i].compareTo(item)==0){
                    lArray[i]=null;
                    deleted=true;
                    count--;
                }
                else{
                    i++;
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
        return inserted;
    }

    public String toString(){
        String elements="";
        for (int i=0; i<count; i++){
            String element_count=""+lArray[i];
            elements+=element_count+", ";
        }
        elements+=lArray[lArray.length-1]+"";
        String list="["+elements+"]";
        return list;
    }

    public static void main(String [] args){
        GenericUnorderedArrayList<Integer> list=new GenericUnorderedArrayList<>();
        System.out.println(list.isEmpty());
        System.out.println(list.isFull());
        System.out.println(list.insert(4));
        list.insert(5);
        list.insert(4);
        list.insert(56);
        System.out.println(list.delete(3));
        System.out.println(list);
    }

}
