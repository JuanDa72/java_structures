package trees;

public class MinHeap {

    int[] array;
    int maxSize;
    int n;

    public MinHeap(int maxSize) {
        array = new int[maxSize+1];
        this.maxSize = maxSize;
        n = 0;
    }

    private int parent(int i) {
        return (i / 2);
    }

    private int leftSon(int i) {
        return (2 * i);
    }

    private int rightSon(int i) {
        return ((2 * i) + 1);
    }

    public int getMax() {
        return array[1];
    }

    private void siftUp(int i) {
        while (i > 1 && array[i] < array[parent(i)]) {
            int temp = array[i];
            array[i] = array[parent(i)];
            array[parent(i)] = temp;
            i = parent(i);
        }
    }

    private void resize() {
        this.maxSize = 2 * maxSize;
        int[] nArray = new int[maxSize];
        System.arraycopy(array, 1, nArray, 1, n);
        this.array = nArray;
    }


    public void insert(int item) {
        if(n==maxSize){
            resize();
        }
        this.n += 1;
        array[n] = item;
        siftUp(n);
    }

    private void siftDown(int i){
        int left=leftSon(i);
        int right=rightSon(i);
        int maxi=i;
        if(left<n && array[left]<array[maxi]){
            maxi=left;
        }
        if(right<n && array[right]<array[maxi]){
            maxi=right;
        }
        if(maxi!=i){
            int temp=array[i];
            array[i]=array[maxi];
            array[maxi]=temp;
            siftDown(maxi);
        }
    }

    public void extractMax(){
        int r=array[1];
        array[1]=array[n];
        n--;
        siftDown(1);
    }

    public void changePriority(int i, int value){
        int old=array[i];
        array[i]=value;
        if(old>array[i]){
            siftUp(i);
        }
        else{
            siftDown(i);
        }
    }

    public void delete(int i){
        array[i]=Integer.MIN_VALUE;
        siftUp(i);
        extractMax();
    }

    public String toString(){
        String elements="";
        int f=n;
        for(int i=1; i<n; i++){
            elements+=array[i]+", ";
        }
        elements+=array[f];
        return elements;
    }

    public static void main(String[] args){
        MinHeap heap=new MinHeap(10);
        heap.insert(21);
        heap.insert(1);
        heap.insert(11);
        heap.insert(20);
        heap.insert(4);
        heap.insert(9);
        heap.insert(7);
        heap.insert(6);
        heap.insert(5);
        heap.insert(20);
        heap.insert(7);
        heap.extractMax();
        heap.changePriority(9,2);
        heap.delete(6);
        System.out.println(heap);
        //System.out.println(heap);
        //System.out.println(heap);
    }

}
