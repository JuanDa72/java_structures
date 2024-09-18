package map;
import java.util.ArrayList;
import java.util.LinkedList;

public class HashMap<K,V> {

    public class HashNode<K, V> {
        K key;
        V value;
        int hashCode;
        HashNode<K,V> next;

        public HashNode(K key, V value){
            this.key=key;
            this.value=value;
        }

    }

    private ArrayList<HashNode<K,V>> bucket;
    private int capacity;
    private int size;
    private Double loadFactor;

    public HashMap(double loadFactor){

        this.capacity=10;
        this.loadFactor=loadFactor;
        this.size=0;
        bucket=new ArrayList<>(capacity);
        for (int i=0; i<capacity; i++){
            bucket.add(null);
        }
    }

     public int hash(K key){
        return Math.abs(key.hashCode())%capacity;
    }

    public void add(K key, V value){
        int position=hash(key);
        HashNode<K,V> head=bucket.get(position);
        while(head!=null){
            //System.out.println("colsión");
            if(head.key.equals(key)){
                head.value=value;
                return;
            }
            head=head.next;
        }

        head=bucket.get(position);
        HashNode<K,V> node=new HashNode<>(key, value);
        node.next=head;
        bucket.set(position, node);
        size++;
        //System.out.println(size+" "+key+" hash: "+hash(key));

        if(size*1.0/capacity>=loadFactor){
            resize();
        }

    }

    private void resize() {
        //System.out.println("redimensionamiento");
        ArrayList<HashNode<K, V>> temp = bucket;
        bucket = new ArrayList<>();
        this.capacity = 2 * capacity;
        for (int i = 0; i < capacity; i++) {
            bucket.add(null);
        }
        size = 0;
        for (HashNode<K, V> headNode : temp) {
            while (headNode != null) {
                //System.out.println(headNode);
                add(headNode.key, headNode.value);
                headNode = headNode.next;
            }
        }
    }

    public int size(){
        return this.size;
    }

    public boolean isEmpty(){
        return(size==0);
    }

    public V remove(K key){
        int position=hash(key);
        HashNode<K,V> head=bucket.get(position);
        HashNode<K,V> prev=null;
        while(head!=null){
            if(head.key.equals(key)){
                break;
            }
            prev=head;
            head=head.next;
        }

        if(head==null){
            return null;
        }

        size--;
        if(prev!=null){
            prev.next=head.next;
        }
        else{
            bucket.set(position,head.next);
        }

        return head.value;

    }

    public V get(K key){
        int position=hash(key);
        HashNode<K,V> head=bucket.get(position);
        while(head!=null){
            if(head.key.equals(key)){
                return head.value;
            }
            head=head.next;
        }

        return null;
    }



    public static void main(String [] args) {

        HashMap<String, Integer> table = new HashMap<>(0.5);;
        //System.out.println(table.hash("mira"));
        table.add("mira",5);
        //System.out.println(table.hash("cuaderno"));
        table.add("cuaderno",1);
        //System.out.println(table.hash("loctron"));
        table.add("loctron",1);
        //System.out.println(table.hash("juga"));
        table.add("juga",7);
        //System.out.println(table.hash("tiktok"));
        table.add("tiktok",2);
        table.add("corazon",0);
        table.add("yeah",9);
        table.add("who",4);
        System.out.println(table.get("loctron"));
        System.out.println(table.size());

        System.out.println(table.remove("loctron"));
        System.out.println(table.size());
        System.out.println(table.get("loctron"));
        System.out.println();

        System.out.println(table.get("who"));
        System.out.println(table.size());

        System.out.println(table.remove("who"));
        System.out.println(table.size());
        System.out.println(table.get("who"));
        System.out.println();

        System.out.println(table.remove("mira"));
        System.out.println(table.size());
        System.out.println(table.get("mira"));
        System.out.println(table.size);




    }


}
