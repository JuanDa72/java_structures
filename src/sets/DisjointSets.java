package sets;

public class DisjointSets {

    int [] parent;
    int [] rank;
    int size;


    public DisjointSets (int size){
        parent=new int[size];

        rank=new int[size];

        this.size=size;
    }


    public void makeSet(int i){
        parent[i]=i;
        rank[i]=0;
    }

    public int find(int i){
        if(parent[i]!=i){
            parent[i]=find(parent[i]);
        }
        return parent[i];
    }

    public void union(int i, int j){
        int parent_i=find(i);
        int parent_j=find(j);
        if(parent_i==parent_j){
            return;
        }
        if (rank[parent_i]>rank[parent_j]){
            parent[parent_j]=parent_i;
        }
        else{
            parent[parent_i]=parent_j;
             if(rank[parent_i]==rank[parent_j]){
                 rank[parent_j]+=1;
             }
        }
    }

    public static void main(String [] args){
        DisjointSets set=new DisjointSets(44);
        set.makeSet(1);
        set.makeSet(2);
        set.makeSet(3);
        set.makeSet(4);
        set.makeSet(5);
        set.makeSet(6);
        System.out.println(set.find(1));
        set.union(2,4);
        set.union(5,2);
        set.union(3,1);
        set.union(2,3);
        System.out.println(set.find(2));
        System.out.println(set.find(5));
        System.out.println(set.find(3));
    }


}
