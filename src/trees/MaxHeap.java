package trees;

public class MaxHeap {
    int[] array;
    int maxSize;
    int n;

    public MaxHeap(int maxSize) {
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
        while (i > 1 && array[i] > array[parent(i)]) {
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
        if(left<n && array[left]>array[maxi]){
            maxi=left;
        }
        if(right<n && array[right]>array[maxi]){
            maxi=right;
        }
        if(maxi!=i){
            int temp=array[i];
            array[i]=array[maxi];
            array[maxi]=temp;
            siftDown(maxi);
        }
    }

    public int extractMax(){
        int r=array[1];
        array[1]=array[n];
        n--;
        siftDown(1);
        return r;
    }

    public void changePriority(int i, int value){
        int old=array[i];
        array[i]=value;
        if(old>array[i]){
            siftDown(i);
        }
        else{
            siftUp(i);
        }
    }

    public void delete(int i){
        array[i]=Integer.MAX_VALUE;
        siftUp(i);
        extractMax();
    }

    public int[] heapSort1(int[] a ){
        MaxHeap heap=new MaxHeap(a.length);
        int i=a.length-1;
        while(i>-1){
            heap.insert(a[i]);
            i--;
        }
        System.out.println(heap);
        i=a.length-1;
        while(i>-1){
            a[i]=heap.extractMax();
            i--;
        }
        return a;
    }

    private void buildheap(int [] a){
        System.arraycopy(a,0,this.array,1,a.length);
        maxSize=array.length;
        this.n=array.length;
        int i=(n/2);
        while(i>0){
            siftDown(i);
            i--;
        }
        //for(int j=0;j<array.length;j++){
            //System.out.println(array[j]);
        //}
    }

    public int [] heapSort(int [] b){
        buildheap(b);
        int i=0;
        int size=array.length;
        //System.out.println(array[8]+" array de 8");
        //System.out.println(size);
        //for(int element: array){
            //System.out.println(element);
        //}
        while(i<size-1){
            //System.out.println(size);
            int temp=array[1];
            array[1]=array[size-1];
            array[size-1]=temp;
            size--;
            siftDown(1);
        }
        return array;
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

    public static void main(String []args){
        int [] array={15,19,3,13,7,9,5,14};
        MaxHeap heap =new MaxHeap(array.length);
        int [] aaa=heap.heapSort(array);
        for(int element: aaa){
            System.out.println(element);
        }
        //heap.extractMax();
        //heap.changePriority(8,10);
        //heap.changePriority(5,29);
        //heap.changePriority(6,5);
        //heap.changePriority(9,4);
        //heap.changePriority(7,22);
        //heap.changePriority(5,45);
        //System.out.println(heap);
        //:)


    }

}
