//package sets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;
import java.util.Scanner;

public class problemC {

    public static class DisjointSets {

        long[] parent;
        long[] rank;
        long size;

        public DisjointSets(long size) {
            parent = new long[(int)size]; // Se necesita un cast a int porque los arrays solo aceptan índices de tipo int
            rank = new long[(int)size];  // Lo mismo aquí
            this.size = size;
        }

        public void makeSet(long i) {
            parent[(int)i] = i;
            rank[(int)i] = 0;
        }

        public long find(long i) {
            if (parent[(int)i] != i) {
                parent[(int)i] = find(parent[(int)i]);
            }
            return parent[(int)i];
        }

        public void union(long i, long j) {
            long parent_i = find(i);
            long parent_j = find(j);
            if (parent_i == parent_j) {
                return;
            }
            if (rank[(int)parent_i] > rank[(int)parent_j]) {
                parent[(int)parent_j] = parent_i;
            } else {
                parent[(int)parent_i] = parent_j;
                if (rank[(int)parent_i] == rank[(int)parent_j]) {
                    rank[(int)parent_j] += 1;
                }
            }
        }
    }

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


    public static class road implements Comparable<road> {
        long city1;
        long city2;
        long cost;

        public road(long city1, long city2, long cost) {
            this.city1 = city1;
            this.city2 = city2;
            this.cost = cost;
        }

        @Override
        public int compareTo(road another) {
            return Long.compare(this.cost, another.cost);  // Usar Long.compare para comparar valores long
        }
    }

    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());
            long cities = Integer.parseInt(st.nextToken());
            long roads = Integer.parseInt(st.nextToken());
            MinHeapGeneric<road> heap = new MinHeapGeneric<>((int) (roads + 1));
            for (int i = 0; i < roads; i++) {
                st = new StringTokenizer(br.readLine());
                int city1 = Integer.parseInt(st.nextToken());
                int city2 = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());
                road road = new road(city1, city2, cost);
                heap.insert(road);
            }
                DisjointSets set = new DisjointSets(cities + 1);
                long totalcost = 0;
                for (int i = 1; i <= cities; i++) {
                    set.makeSet(i);
                }
                while (!heap.isEmpty()) {
                    road r = heap.extractMin();
                    if (set.find(r.city1) != set.find(r.city2)) {
                        set.union(r.city1, r.city2);
                        totalcost += r.cost;
                    }
                }

                boolean b = true;
                long one =set.find(1);
                for(int j=2; j<=cities; j++){
                    if(one!=set.find(j)){
                        b=false;
                        break;
                    }
                }

                if(b){
                    System.out.println(totalcost);
                }
                else{
                    System.out.println("IMPOSSIBLE");
                }

            } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
