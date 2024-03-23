package list;

public class GenericNode <T> {

    private T value;

    private GenericNode next;

    public GenericNode(T item){
        this.value=item;
        next=null;
    }

    public T getData(){
        return this.value;
    }

    public GenericNode getNext(){
        return this.next;
    }

    public void setValue(T item){
        this.value=item;
    }

    public void setNext(GenericNode node){
        next=node;
    }


}
