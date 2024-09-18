package trees;

public class MinHeapGeneric<T extends Comparable<T>> {

    T[] heapArray;
    int maxSize;
    int size;

    public MinHeapGeneric(int maxSize) {
        heapArray = (T[]) new Comparable[maxSize+1];
        this.maxSize = maxSize;
        size= 0;
    }

    private int parent(int i) {
        return i / 2;
    }

    private int leftSon(int i) {
        return 2 * i;
    }

    private int rightSon(int i) {
        return (2 * i) + 1;
    }

    public T getMin() {
        if(isEmpty()){
            return null;
        }
        return heapArray[1];  // Obtener el elemento con mayor prioridad
    }

    private void siftUp(int i) {
        while (i > 1 && heapArray[i].compareTo(heapArray[parent(i)]) < 0) {
            swap(i, parent(i));
            i = parent(i);
        }
    }

    private void siftDown(int i) {
        int left = leftSon(i);
        int right = rightSon(i);
        int smallest = i;

        if (left <= size && heapArray[left].compareTo(heapArray[smallest]) < 0) {
            smallest = left;
        }

        if (right <= size && heapArray[right].compareTo(heapArray[smallest]) < 0) {
            smallest = right;
        }

        if (smallest != i) {
            swap(i, smallest);
            siftDown(smallest);
        }
    }

    public void insert(T item) {
        if (size == maxSize) {
            resize();
        }
        size += 1;
        heapArray[size] = item;
        siftUp(size);
    }

    public T extractMin() {
        if(isEmpty()){
            return null;
        }
        T min = heapArray[1];
        heapArray[1] = heapArray[size];
        size--;
        siftDown(1);
        return min;
    }

    private void swap(int i, int j) {
        T temp = heapArray[i];
        heapArray[i] = heapArray[j];
        heapArray[j] = temp;
    }

    private void resize() {
        this.maxSize = 2 * maxSize;
        T[] newArray = (T[]) new Comparable[maxSize + 1];
        System.arraycopy(heapArray, 1, newArray, 1, size);
        this.heapArray = newArray;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public static void main(String [] args){
        MinHeapGeneric<Integer> heap=new MinHeapGeneric<>(4);
        heap.insert(8);
        heap.insert(4);
        heap.insert(1);
        heap.insert(10);;
        heap.extractMin();
        heap.insert(2);
        heap.insert(3);
        heap.extractMin();
        heap.insert(0);
        heap.insert(9);
        heap.insert(5);
        heap.extractMin();
        heap.extractMin();
        heap.extractMin();
        System.out.println(heap.extractMin());
        heap.extractMin();
        heap.extractMin();
        System.out.println(heap.extractMin());
        System.out.println(heap.extractMin());
    }


}
