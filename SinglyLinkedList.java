//Hi I am trying to use java convention on this

import java.util.Iterator;
import java.util.NoSuchElementException;

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
                throw new NoSuchElementException("There is no next");
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
    @Override
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
        if(head == null){
            throw new NoSuchElementException("empty");
        }
        return head.data;
    }
    public T getLast(){
        if(tail == null){
            throw new NoSuchElementException("empty");
        }
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
    public boolean contains(T data){
        Node<T> current = head;
        while(current != null){
            if(data == null && current.data == null || (data != null && data.equals(current.data))){
                return true;
            }
            current = current.next;
        }
        return false;
    }
    public int indexOf(T data){
        Node<T> current = head;
        int index = 0;
        while(current != null){
            if ((data == null && current.data == null) ||(data != null && data.equals(current.data))) {
                return index;
            }
            index++;
            current = current.next;
        }
        return -1; 
    }
    public void clear(){
        head = null;
        tail = null;
        size = 0;
    }
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        Node<T> current = head;
        while(current != null){
            sb.append(current.data);
            if(current.next != null){
                sb.append("->");
            }
            current = current.next;
        }
        return sb.toString();
    }
    @SuppressWarnings("unchecked")
    public T[] toArray(){
        Node<T> current = head;
        T[] array = (T[]) new Object [size];
        for(int i = 0; i < size; i++){
            array[i] = current.data;
            current = current.next;
        }
        return array;
        
    }
    public static void main(String[] args) {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();

        System.out.println("Empty? " + list.isEmpty() + ", size=" + list.size());
        // → Empty? true, size=0

        list.addFirst(10);             // [10]
        list.addLast(30);              // [10->30]
        list.add(1, 20);               // [10->20->30]
        list.add(3, 40);               // [10->20->30->40]
        System.out.println("After adds: " + list + ", size=" + list.size());
        // → After adds: 10->20->30->40, size=4

        System.out.println("getFirst(): " + list.getFirst());  // → 10
        System.out.println("getLast():  " + list.getLast());   // → 40
        System.out.println("get(2):     " + list.get(2));      // → 30

        System.out.println("contains(20): " + list.contains(20));  // → true
        System.out.println("indexOf(30):  " + list.indexOf(30));   // → 2
        System.out.println("contains(99): " + list.contains(99));  // → false

        System.out.println("removeFirst(): " + list.removeFirst()); // → 10, now [20->30->40]
        System.out.println("removeLast():  " + list.removeLast());  // → 40, now [20->30]
        list.addLast(50);
        list.addLast(60);  
        System.out.println("Before remove(1): " + list);            // → [20->30->50->60]
        System.out.println("remove(1):      " + list.remove(1));   // → 30, now [20->50->60]
        System.out.println("After remove:   " + list);
        // → [20->50->60]

        System.out.print("Iteration: ");
        for (Integer x : list) {
            System.out.print(x + " ");
        }
        System.out.println();  // → 20 50 60


        Object[] arr = list.toArray();
        System.out.println("toArray(): " + java.util.Arrays.toString(arr));//[20, 50, 60]

        list.clear();
        System.out.println("After clear: " + list + ", isEmpty=" + list.isEmpty() + ", size=" + list.size()); //isEmpty=true, size=0
    }
}
