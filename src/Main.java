import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{

    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            MinHeapBinario1 edges = new MinHeapBinario1(m);

            while (m > 0) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                edges.insert(new Edge(a, b, c));
                m--;
            }
            DisjointSet1 ds = new DisjointSet1(n + 1);

            long totalCost = 0;

            while (!edges.isEmpty()) {
                Edge e = edges.extractMin();
                if (!ds.isReachable(e.a, e.b)) {
                    ds.union(e.a, e.b);
                    totalCost += e.c;
                }
            }

            boolean possible = true;
            int id = ds.find(1);

            for (int i = 2; i <= n; i++) {
                if (id != ds.find(i)) {
                    possible = false;
                    break;
                }
            }
            if (possible)
                System.out.println(totalCost);
            else
                System.out.println("IMPOSSIBLE");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class DisjointSet1 {
    private int[] parent;
    private int[] rank;

    public DisjointSet1(int size) {
        parent = new int[size];
        rank = new int[size];
        for (int i = 0; i < size; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }

    public boolean isReachable(int A, int B) {
        return find(A) == find(B);
    }

    public int find(int element) {
        if (parent[element] != element) {
            parent[element] = find(parent[element]);
        }
        return parent[element];
    }

    public void union(int set1, int set2) {
        int root1 = find(set1);
        int root2 = find(set2);

        if (root1 != root2) {
            if (rank[root1] > rank[root2]) {
                parent[root2] = root1;
            } else if (rank[root1] < rank[root2]) {
                parent[root1] = root2;
            } else {
                parent[root2] = root1;
                rank[root1]++;
            }
        }
    }
}

class Edge {
    int a;
    int b;
    int c;

    public Edge(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }
}

class MinHeapBinario1 {

    private Edge[] array;
    private int maxSize;
    private int n;

    public MinHeapBinario1(int maxSize) {
        array = new Edge[maxSize + 1];
        this.maxSize = maxSize;
        n = 0;
    }

    private int parent(int i) {
        return i / 2;
    }

    private int leftSon(int i) {
        return 2 * i;
    }

    private int rightSon(int i) {
        return 2 * i + 1;
    }

    public Edge getMin() {
        return array[1];
    }

    private void siftUp(int i) {
        while (i > 1 && array[i].c < array[parent(i)].c) {
            swap(i, parent(i));
            i = parent(i);
        }
    }

    private void resize() {
        maxSize *= 2;
        Edge[] nArray = new Edge[maxSize];
        System.arraycopy(array, 1, nArray, 1, n);
        array = nArray;
    }

    public void insert(Edge item) {
        if (n + 1 == maxSize) {
            resize();
        }
        n++;
        array[n] = item;
        siftUp(n);
    }

    private void siftDown(int i) {
        int left = leftSon(i);
        int right = rightSon(i);
        int mini = i;
        if (left <= n && array[left].c < array[mini].c) {
            mini = left;
        }
        if (right <= n && array[right].c < array[mini].c) {
            mini = right;
        }
        if (mini != i) {
            swap(i, mini);
            siftDown(mini);
        }
    }

    public Edge extractMin() {
        Edge r = array[1];
        array[1] = array[n];
        n--;
        siftDown(1);
        return r;
    }

    private void swap(int i, int j) {
        Edge temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public boolean isEmpty() {
        return n == 0;
    }
}