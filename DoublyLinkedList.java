import java.util.Iterator;
import java.util.NoSuchElementException;

class Node<T>{
    public Node<T> next;
    public Node<T> prev;
    public T data;

    public Node(T data){
        this.data = data;
        this.prev = null;
        this.next = null;
    }
}

public class DoublyLinkedList<T> implements Iterable<T>{
    private int size;
    private Node<T> head;
    private Node<T> tail;

    public DoublyLinkedList(){
        head = null;
        tail = null;
        size = 0;
    }
    public Iterator<T> iterator() {
        return new ListIterator();
    }
    private class ListIterator implements java.util.ListIterator<T>{
        Node<T> current = head;
        Node<T> prev = null;
        int nextIndex = 0;
        @Override
        public boolean hasNext(){
            return current != null;
        }
        @Override
        public T next(){
            if(current == null) throw new NoSuchElementException("There is no next");
            T data = current.data;
            prev = current;
            current = current.next;
            nextIndex++;
            return data;
        }
        @Override
        public boolean hasPrevious(){
            return prev != null;
        }
        @Override
        public T previous(){
            if(prev == null) throw new NoSuchElementException("There is no next");
            T data = prev.data;
            current = prev;
            prev = prev.prev;
            nextIndex--;
            return data;
        }
          @Override
        public int nextIndex() {
            return nextIndex;
        }
        @Override
        public int previousIndex() {
            return nextIndex - 1;
        }
        @Override public void remove() { throw new UnsupportedOperationException(); }
        @Override public void set(T e) { throw new UnsupportedOperationException(); }
        @Override public void add(T e) { throw new UnsupportedOperationException(); }
    }

    public boolean isEmpty(){
        return head == null;
    }
    public int size(){
        return size;
    }
    private Node<T> nodeAt(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index " + index + " out of bounds");
        }
        Node<T> cursor;
        if (index < size / 2) {
            cursor = head;
            for (int i = 0; i < index; i++) {
                cursor = cursor.next;
            }
        } else {
            cursor = tail;
            for (int i = size - 1; i > index; i--) {
                cursor = cursor.prev;
            }
        }
        return cursor;
    }
    public void addFirst(T data){
        Node<T> newNode = new Node<>(data);
        if(isEmpty()){
            head = newNode;
            tail = newNode;
        }
        else{
            head.prev = newNode;
            newNode.next = head;
            head = newNode;
        }
        size++;
    }
    public void addLast(T data){
        Node <T> newNode = new Node<>(data);
        if(isEmpty()){
            head = newNode;
            tail = newNode;
        }
        else{
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
    }
    public void add(int index, T data){
        if(index < 0 || index > size){
            throw new IndexOutOfBoundsException("Index " + index + " out of bounds");
        }
        if(index == 0){ 
            addFirst(data);
            return;
        };
        if(index == size){ 
            addLast(data);
            return;
        };
        Node <T> newNode = new Node<>(data);
        Node<T> current = nodeAt(index);
        newNode.next = current;
        newNode.prev = current.prev;
        current.prev.next = newNode;
        current.prev = newNode;
        size++;
    }
    public T removeFirst(){
        if(isEmpty()) return null;
        T temp = head.data;
        if(head.next == null){
            head = null;
            tail = null;
        } 
        else{
            head = head.next;
            head.prev = null;
        }
        size--;
        return temp;
    }
    public T removeLast(){
        if(isEmpty()) return null;
        T temp = tail.data;
        if(head.next == null){
            head = null;
            tail = null;
        }
        else{
            tail = tail.prev;
            tail.next = null;
        }
        size--;
        return temp;
    }
    public T remove(int index){
        if(isEmpty()) return null;
        if(index < 0 || index >= size){
            throw new IndexOutOfBoundsException("Index " + index + " out of bounds");
        }
        T temp;
        if(size == 1){
            temp = head.data;
            head = null;
            tail = null;
            size--;
            return temp;
        }
        if(index == 0) return removeFirst();
        if(index == size-1) return removeLast();
        Node<T> current = nodeAt(index);
        temp = current.data;
        current.prev.next = current.next;
        current.next.prev = current.prev;
        size--;
        return temp;
    }
    public T get(int index){
        if(isEmpty()) return null;
        if(index < 0 || index >= size){
            throw new IndexOutOfBoundsException("Index " + index + " out of bounds");
        }
        return nodeAt(index).data;
    }
    public int getIndexOf(T data){
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
    public void clear(){
        head = null;
        tail = null;
        size = 0;
    }
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        Node<T> current = head;
         sb.append('[');
        while(current != null){
            sb.append(current.data);
            if(current.next != null){
                sb.append("⇄");
            }
            current = current.next;
        }
         sb.append(']');
        return sb.toString();
    }
    public static void main(String[] args) {
        DoublyLinkedList<Integer> dll = new DoublyLinkedList<>();
      
        dll.addFirst(10);   // [10]
        dll.addLast(30);    // [10 ⇄ 30]
        dll.add(1, 20);     // [10 ⇄ 20 ⇄ 30]
        System.out.println("After adds:    " + dll);  // [10⇄20⇄30]
  
        System.out.println("getFirst():    " + dll.getFirst());  // 10
        System.out.println("getLast():     " + dll.getLast());   // 30
        System.out.println("get(1):        " + dll.get(1));      // 20
        
        System.out.println("contains(20):  " + dll.contains(20));  // true
        System.out.println("indexOf(30):   " + dll.getIndexOf(30)); // 2
        System.out.println("contains(99):  " + dll.contains(99));  // false
       
        System.out.println("removeFirst(): " + dll.removeFirst()); // 10, now [20⇄30]
        System.out.println("removeLast():  " + dll.removeLast());  // 30, now [20]
        dll.addLast(40);  // [20⇄40]
        dll.addLast(50);  // [20⇄40⇄50]
        System.out.println("Before remove: " + dll);              // [20⇄40⇄50]
        System.out.println("remove(1):     " + dll.remove(1));    // 40, now [20⇄50]
        System.out.println("After remove:  " + dll);              // [20⇄50]
 
        System.out.print("Iteration:     ");
        for (Integer x : dll) {
            System.out.print(x + " ");
        }
        System.out.println();  // 20 50

     
        dll.clear();
        System.out.println("Cleared:       " + dll + ", isEmpty=" + dll.isEmpty());  // [], true
    }

}
