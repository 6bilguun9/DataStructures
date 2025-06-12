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
        if (head == tail) {
            T data = head.data;
            head = tail = null;
            size--;                          // update size
            return data;
        }
        Node<T> current = head;
        while(current.next != tail){
            current = current.next;
        }
        T temp = tail.data;
        current.next = null;
        tail = current;
        size--;
        return temp;
    }
    public T getFirst(){
        return head.data;
    }
    public T getLast(){
        return tail.data;
    }
    public T get(int index){
        if (index < 0 || index >= size){
            throw new IndexOutOfBoundsException("Index " + index + " out of bounds");
        }
        Node<T> current = head;
        for(int i = 0; i < index; i++){
            current = current.next;
        }
        return current.data;
    }
    public void add(int index, T data){
        if(index < 0 || index > size){
            throw new IndexOutOfBoundsException("Index " + index + " out of bounds");
        }

        Node<T> newNode = new Node<>(data);

        if(index == 0){
            newNode.next = head;
            head = newNode;
            if(size == 0) tail = newNode;
            size++;
            return;
        }
        Node<T> current = head;

        for(int i = 0; i < index-1; i++){
            current = current.next;
        }
        newNode.next = current.next;
        current.next = newNode;

        if (newNode.next == null) tail = newNode;
        size++;
    }
    public T remove(int index){
        if (index < 0 || index >= size){
            throw new IndexOutOfBoundsException("Index " + index + " out of bounds");
        }

        T temp;
        if(index == 0){
            temp = head.data;
            head = head.next;
            if(head == null) tail = null;
            size--;
            return temp;
        }
        else {
            Node<T> current = head;
            for(int i = 0; i < index-1; i++){
                current = current.next;
            }
            temp = current.next.data;
            current.next = current.next.next;

            if(current.next == null){
                tail = current;
            }
        }

        size--;
        return temp;
    }
    public boolean contains(Object o){
        Node<T> current = head;

        if (o == null) {
            while (current != null) {
                if (current.data == null) {
                    return true;
                }
                current = current.next;
            }
        } else {
            while (current != null) {
                if (o.equals(current.data)) {
                    return true;
                }
                current = current.next;
            }
        }
        return false;
    }
    public int indexOf(Object o){
        Node<T> current = head;
        int index = 0;

        while (current != null) {
            if ((o == null && current.data == null) || (o != null && o.equals(current.data))) {
                    return index;          
            }
            current = current.next;    
            index++;
        }
        return -1;  
    }

}
