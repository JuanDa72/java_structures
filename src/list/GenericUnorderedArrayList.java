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

    public static void main(String [] args){
        GenericUnorderedArrayList<Integer> list=new GenericUnorderedArrayList<>();
        System.out.println(list.isEmpty());
        System.out.println(list.isFull());
        System.out.println(list.insert(4));
        list.insert(5);
        list.insert(4);
        list.insert(56);
        System.out.println(list);
        System.out.println(list.setValue(4,6));
        System.out.println(list);
        int value=list.getValue(0);
    }

}
