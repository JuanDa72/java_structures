package trees;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class problemA {

    public static class MinHeapGeneric<T extends Comparable<T>> {

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

    }

    public static class cliente implements Comparable<cliente> {
        int llegada;
        int prep;
        public cliente(int llegada, int prep){
            this.prep=prep;
            this.llegada=llegada;
        }

        public int getLlegada() {
            return llegada;
        }

        public int getPrep(){
            return prep;
        }

        public int compareTo(cliente cliente){
            return Integer.compare(this.prep,cliente.getPrep());
        }

    }

    public static void main(String [] args){
        Scanner sc=new Scanner(System.in);
        int nClientes=sc.nextInt();
        cliente [] clientes=new cliente[nClientes];
        MinHeapGeneric<cliente> heap=new MinHeapGeneric<cliente>(nClientes);
        for(int i=0; i<nClientes; i++){
            int llegada=sc.nextInt();
            int prep=sc.nextInt();
            cliente cliente=new cliente(llegada,prep);
            clientes[i]=cliente;
        }


        Arrays.sort(clientes, Comparator.comparingInt(cliente::getLlegada));
        //for(cliente cliente: clientes){
            //System.out.println(cliente.getLlegada());

        //}

        long time=0;
        int counter=0;
        long acc=0;
        ArrayList<Long> promedios=new ArrayList<>();
        while(acc<nClientes){
            while(counter<nClientes && clientes[counter].getLlegada()<=time){
                //System.out.println("prep"+clientes[counter].getPrep()+" llegada "+clientes[counter].getLlegada());
                heap.insert(clientes[counter]);
                counter++;
            }
            if(heap.isEmpty()){
                //System.out.println("aumento tiempo");
                time++;
            }
            else{
                cliente nCliente=heap.extractMin();
                long l=nCliente.getLlegada();
                long p=nCliente.getPrep();
                //System.out.println("l "+nCliente.getLlegada()+" p "+nCliente.getPrep());
                acc++;
                long tiempoPromedio=(time+p-l);
                //System.out.println("Tiempo promedio "+tiempoPromedio);
                promedios.add(tiempoPromedio);
                time+=p;
                //System.out.println("time "+time);
            }
        }

        long all=promedios.stream().mapToLong(Long::longValue).sum();
        //System.out.println("all"+all);
        System.out.println(all/nClientes);

    }

}


