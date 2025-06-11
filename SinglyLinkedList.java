//Hi I am trying to use java convention on this

import java.util.Iterator;

class Node<T> {
    public Node<T> next;
    public T data;

    public Node(T data){
        this.data = data;
        this.next = null;
    }
}
 
public class SinglyLinkedList<T> implements Iterable<T>{
    private Node<T> head;
    private Node<T> tail;
    private int size;

    public SinglyLinkedList(){
        head = null;
        tail = null;
        size = 0;
    }
    
    private class ListIterator implements Iterator<T>{
        private Node<T> current = head;

        @Override
        public boolean hasNext(){
            return current != null;
        }
        @Override
        public T next(){
            if(current == null){
                return null;
            }
            T data = current.data;
            current = current.next;
            return data;
        }
        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
    public Iterator<T> iterator() {
        return new ListIterator();
    }

    public boolean isEmpty(){
        return head == null;
    }
    public int size(){
        return size;
    }
    public void addFirst(T data){
        size++;
        Node<T> newNode = new Node<>(data);
        newNode.next = head;
        head = newNode;
        if (tail == null){
            tail = newNode;
        }
    }
    public void addLast(T data){
        size++;
        Node<T> newNode = new Node<>(data);
        if(isEmpty()){
            head = newNode;
            tail = newNode;
        }
        else {
            newNode.next = null;
            tail.next = newNode;
            tail = newNode;
        }
    }
    public T removeFirst(){
        if(isEmpty()){
            return null;
        }
        T temp = head.data;
        head = head.next;
        if(head == null){
            tail = null;
        }
        size--;
        return temp;
    }
    public T removeLast(){
        if(isEmpty()){
            return null;
        }
        return null;

    }


}
