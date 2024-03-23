package list;

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
                for(int i=position; i<count; i++){
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

    public boolean insert(int item){
        boolean inserted=false;
        if(!isFull()){
            getPosition(item);
            for(int i=count;i>position;i--){
                lArray[i]=lArray[i-1];
            }
            lArray[position]=item;
            inserted=true;
            count++;
        }
        else{
            System.out.println("List is full");
        }
        return inserted;
    }

    public String toString(){
        String numbers="";
        for (int i=0; i<count; i++){
            String number_count=""+lArray[i];
            numbers+=number_count+", ";
        }
        numbers+=lArray[lArray.length-1]+"";
        String list="["+numbers+"]";
        return list;
    }

    public static void main(String [] args){
        OrderedArrayList list=new OrderedArrayList();
        System.out.println(list.insert(2));
        System.out.println(list.isEmpty());
        System.out.println(list.isFull());
        System.out.println(list.insert(0));
        list.insert(-10);
        list.insert(22);
        list.insert(1);
        list.insert(6);
        list.delete(-10);
        System.out.println(list);
        System.out.println(list.getCount());
    }


}
