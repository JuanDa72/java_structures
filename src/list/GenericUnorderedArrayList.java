package list;

public class GenericUnorderedArrayList<T extends Comparable<T>> {

    private static int size=3;

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
        else{
            System.err.println("Error: List is full or position is  out of range");
            throw new RuntimeException("List is full or position is out of range");
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
        System.arraycopy(lArray,0,newArray,0,size);
        lArray=newArray;
    }

    public static void main(String [] args){
        GenericUnorderedArrayList<Integer> list=new GenericUnorderedArrayList<>(57);
        System.out.println(list.isEmpty());
        System.out.println(list.isFull());
        System.out.println(list.insert(4));
        list.insert(5);
        list.insert(4);
        list.insert(2);
        System.out.println(list);
    }
}
